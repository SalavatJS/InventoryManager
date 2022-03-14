package com.github.salavatjs.inventorymanager;

import com.github.salavatjs.inventorymanager.inventories.BaseInventory;
import com.github.salavatjs.inventorymanager.inventories.PersonalInventory;
import com.github.salavatjs.inventorymanager.inventories.items.InventoryItem;
import com.github.salavatjs.inventorymanager.inventories.items.SimpleInventoryItem;
import com.github.salavatjs.inventorymanager.listeners.InventoryListener;
import com.github.salavatjs.inventorymanager.services.InventoryProvider;
import com.github.salavatjs.inventorymanager.services.InventoryService;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    private Listener listener;

    private List<BaseInventory> inventories;

    private InventoryService service;

    @Override
    public void onEnable() {
        listener = new InventoryListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
        inventories = new ArrayList<>();
        service = new InventoryProvider(this);
        getServer().getServicesManager().register(InventoryService.class, service, this, ServicePriority.High);
    }

    public List<BaseInventory> getInventories() {
        return inventories;
    }

}
