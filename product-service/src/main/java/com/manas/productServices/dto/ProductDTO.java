package com.manas.productServices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class ProductDTO {
//    private String id;
//    private String name;
//    private String description;
//    private BigDecimal price;
//}

public record ProductDTO(String id, String name, String description, BigDecimal price) {
}
