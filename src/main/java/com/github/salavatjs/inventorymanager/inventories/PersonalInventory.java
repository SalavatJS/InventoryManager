package com.github.salavatjs.inventorymanager.inventories;

import com.github.salavatjs.inventorymanager.inventories.items.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PersonalInventory extends BaseInventory {

    private Map<Player, Inventory> personalInventories;

    private Function<Player, String> title;

    public PersonalInventory(int size, Map<Integer, InventoryItem> items, Function<Player, String> title) {
        super(size, items);
        this.title = title;
        personalInventories = new HashMap<>();
    }

    @Override
    public void openForPlayer(Player player) {
        Inventory inventory = personalInventories.get(player);
        if (inventory == null) {
            String title = this.title.apply(player);
            inventory = Bukkit.createInventory(player, size, title);
            Inventory finalInventory = inventory;
            items.forEach((index, item) -> {
                finalInventory.setItem(index, items.get(index).onFill(this, player));
            });
            personalInventories.put(player, inventory);
        }
        player.openInventory(inventory);
    }

    @Override
    public Inventory getInventoryForPlayer(Player player) {
        Inventory inventory = personalInventories.get(player);
        return inventory;
    }

    public void removePlayerInventory(Player player) {
        personalInventories.remove(player);
    }

}
