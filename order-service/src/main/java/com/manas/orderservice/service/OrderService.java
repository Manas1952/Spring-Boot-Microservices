package com.manas.orderservice.service;

import com.manas.orderservice.dto.InventoryDTO;
import com.manas.orderservice.dto.OrderDTO;
import com.manas.orderservice.mapper.OrderMapper;
import com.manas.orderservice.model.Order;
import com.manas.orderservice.model.OrderLineItems;
import com.manas.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.MAPPER.DtoToModel(orderDTO);
        order.setOrderNumber(UUID.randomUUID().toString());

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
        try {
            List<InventoryDTO> inventoryDTOS = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<InventoryDTO>>() {})
                    .block();

            boolean isInStock = inventoryDTOS.stream().allMatch(InventoryDTO::isInStock);
            if (isInStock) {
                orderRepository.save(order);
            } else {
                throw new IllegalAccessException("Product is not in stock, please try again later!");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
