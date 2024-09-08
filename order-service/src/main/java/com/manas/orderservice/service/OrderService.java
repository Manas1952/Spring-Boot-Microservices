package com.manas.orderservice.service;

import com.manas.orderservice.client.InventoryClient;
import com.manas.orderservice.dto.OrderDTO;
import com.manas.orderservice.event.OrderPlacedEvent;
import com.manas.orderservice.mapper.OrderMapper;
import com.manas.orderservice.model.Order;
import com.manas.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderDTO orderDTO) {
        boolean isInStock = inventoryClient.isInStock(orderDTO.skuCode(), orderDTO.quantity());

        if (isInStock) {
            Order order = OrderMapper.MAPPER.DtoToModel(orderDTO);
            order.setOrderNumber(UUID.randomUUID().toString());
            orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setEmail(orderDTO.userDetails().email());
            orderPlacedEvent.setFirstName(orderDTO.userDetails().firstName());
            orderPlacedEvent.setLastName(orderDTO.userDetails().lastName());
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());

            log.info("Sending OrderPlacedEvent {} to Kafka topic order-placed.", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("Sent OrderPlacedEvent {} to Kafka topic order-placed.", orderPlacedEvent);
        } else {
            throw new RuntimeException("Product with SkuCode " + orderDTO.skuCode() + " is not in stock, please try again later!");
        }
    }
}
