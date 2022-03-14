package com.github.salavatjs.inventorymanager.inventories.items;

import com.github.salavatjs.inventorymanager.inventories.BaseInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface InventoryItem {

    ItemStack onFill(BaseInventory inventory, Player player);

    void onClick(Player player);

}
