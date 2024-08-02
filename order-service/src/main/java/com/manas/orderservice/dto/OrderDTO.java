package com.manas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class OrderDTO {
//    private List<OrderLineItemsDTO> orderLineItems;
//}

public record OrderDTO(Long id, String orderName, String skuCode, BigDecimal price, Integer quantity) {
}