package com.github.salavatjs.inventorymanager.listeners;

import com.github.salavatjs.inventorymanager.Main;
import com.github.salavatjs.inventorymanager.inventories.PersonalInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;


public class InventoryListener implements Listener {

    private Main main;

    public InventoryListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        main.getInventories().forEach(inventory -> {
            if (inventory.getInventoryForPlayer(player) == event.getInventory()) {
                inventory.onClick(player, event.getRawSlot());
                player.sendMessage(event.getSlot() + " " + event.getRawSlot());
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        main.getInventories().forEach(inventory -> {
            Inventory realInventory = inventory.getInventoryForPlayer(player);
            if (realInventory == event.getInventory()) {
                if (!inventory.isShouldClose()) player.openInventory(realInventory);
            }
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        main.getInventories().forEach(inventory -> {
            if (inventory instanceof PersonalInventory personalInventory) personalInventory.removePlayerInventory(event.getPlayer());
        });
    }

}
