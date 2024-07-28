package com.manas.inventoryservice.service;

import com.manas.inventoryservice.model.Inventory;
import com.manas.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<Inventory> isInStock(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes);
    }
}
