package com.manas.productServices.dto;

import java.math.BigDecimal;

public record ProductDTO(String id, String name, String description, BigDecimal price) {
}
