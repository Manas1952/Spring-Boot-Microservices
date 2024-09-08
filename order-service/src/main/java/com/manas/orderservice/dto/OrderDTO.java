package com.manas.orderservice.dto;

import java.math.BigDecimal;

public record OrderDTO(Long id, String orderName, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {
    public record UserDetails(String email, String firstName, String lastName) {
    }
}
