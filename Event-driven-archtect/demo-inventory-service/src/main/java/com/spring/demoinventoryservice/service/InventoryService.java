package com.spring.demoinventoryservice.service;

import com.spring.demoinventoryservice.dto.InventoryResponse;
import com.spring.demoinventoryservice.model.Inventory;
import com.spring.demoinventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException {
        log.info("wait start");
        Thread.sleep(10000);
        log.info("wait stop");

        return inventoryRepo.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0).build()).collect(Collectors.toList());
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }
}
