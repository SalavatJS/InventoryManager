package com.github.salavatjs.inventorymanager.inventories;

import com.github.salavatjs.inventorymanager.inventories.items.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StaticInventory extends BaseInventory {

    private Inventory inventory;

    private String title;

    public StaticInventory(int size, Map<Integer, InventoryItem> items, String title) {
        super(size, items);
        this.title = title;
        inventory = Bukkit.createInventory(null, size, title);
        items.forEach((index, item) -> {
            inventory.setItem(index, items.get(index).onFill(this, null));
        });
    }

    @Override
    public void openForPlayer(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public Inventory getInventoryForPlayer(Player player) {
        return inventory;
    }

}
