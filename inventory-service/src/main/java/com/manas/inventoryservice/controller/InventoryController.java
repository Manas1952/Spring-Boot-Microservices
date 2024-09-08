package com.manas.inventoryservice.controller;

import com.manas.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean placeOrder(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
