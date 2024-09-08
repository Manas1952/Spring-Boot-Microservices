package com.manas.productServices.service;

import com.manas.productServices.dto.ProductDTO;
import com.manas.productServices.mapper.ProductMapper;
import com.manas.productServices.model.Product;
import com.manas.productServices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.MAPPER.DtoToModel(productDTO);
        product = productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return ProductMapper.MAPPER.modelToDto(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.MAPPER.modelsToDtos(products);
    }
}
