package com.manas.inventoryservice.controller;

import com.manas.inventoryservice.dto.InventoryDTO;
import com.manas.inventoryservice.mapper.InventoryMapper;
import com.manas.inventoryservice.model.Inventory;
import com.manas.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> placeOrder(@RequestParam List<String> skuCode) {
        List<Inventory> inventoryList = inventoryService.isInStock(skuCode);
        return InventoryMapper.MAPPER.DtoToModel(inventoryList);
    }
}
