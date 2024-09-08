package com.manas.inventoryservice.repository;

import com.manas.inventoryservice.model.Inventory;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Observed
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public List<Inventory> findBySkuCodeIn(List<String> skuCode);

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
