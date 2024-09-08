package com.manas.productServices.mapper;

import com.manas.productServices.dto.ProductDTO;
import com.manas.productServices.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    List<ProductDTO> modelsToDtos(List<Product> products);
    Product DtoToModel(ProductDTO productDTO);
    ProductDTO modelToDto(Product product);
}
