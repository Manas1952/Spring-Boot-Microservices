package com.manas.productServices.repository;

import com.manas.productServices.model.Product;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.mongodb.repository.MongoRepository;

@Observed
public interface ProductRepository extends MongoRepository<Product, String> {
}
