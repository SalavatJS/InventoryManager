package com.github.salavatjs.inventorymanager.services;

import com.github.salavatjs.inventorymanager.Main;
import com.github.salavatjs.inventorymanager.inventories.BaseInventory;

import java.util.List;

public class InventoryProvider implements InventoryService {

    private Main main;

    public InventoryProvider(Main main) {
        this.main = main;
    }

    @Override
    public void addInventory(BaseInventory inventory) {
        main.getInventories().add(inventory);
    }

    @Override
    public List<BaseInventory> getInventories() {
        return main.getInventories();
    }
}
