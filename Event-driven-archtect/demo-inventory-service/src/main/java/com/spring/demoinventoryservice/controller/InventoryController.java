package com.spring.demoinventoryservice.controller;

import com.spring.demoinventoryservice.dto.InventoryResponse;
import com.spring.demoinventoryservice.model.Inventory;
import com.spring.demoinventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invent")
public class InventoryController {

    @Autowired
    private InventoryService iService;


    @PostMapping("/save")
    public Inventory saveInventory(@RequestBody Inventory inventory){
        return iService.saveInventory(inventory);
    }

    @GetMapping("/")
    public List<InventoryResponse> isInStocks(@RequestParam List<String> skuCode) throws InterruptedException {
        return iService.isInStock(skuCode);
    }
}
