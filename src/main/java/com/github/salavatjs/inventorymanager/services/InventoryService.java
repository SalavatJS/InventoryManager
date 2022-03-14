package com.github.salavatjs.inventorymanager.services;

import com.github.salavatjs.inventorymanager.inventories.BaseInventory;

import java.util.List;

public interface InventoryService {

    void addInventory(BaseInventory inventory);

    List<BaseInventory> getInventories();

}
