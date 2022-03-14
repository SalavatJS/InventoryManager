package com.github.salavatjs.inventorymanager.inventories.items;

import com.github.salavatjs.inventorymanager.inventories.BaseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SimpleInventoryItem implements InventoryItem {

    private Material type;

    private Function<Player, String> title;

    private Function<Player, List<String>> description;

    private Consumer<Player> onClickAction;

    public SimpleInventoryItem(Material type, Function<Player, String> title, Function<Player, List<String>> description, Consumer<Player> onClickAction) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.onClickAction = onClickAction;
    }

    public SimpleInventoryItem(Material type, Function<Player, String> title, Function<Player, List<String>> description) {
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public SimpleInventoryItem(Material type, Function<Player, String> title) {
        this.type = type;
        this.title = title;
    }

    @Override
    public ItemStack onFill(BaseInventory inventory, Player player) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        String title = this.title.apply(player);
        meta.setDisplayName(title);
        if (description != null) {
            List<String> lore = description.apply(player);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onClick(Player player) {
        if (onClickAction != null) onClickAction.accept(player);
    }

    public Consumer<Player> getOnClickAction() {
        return onClickAction;
    }

    public void setOnClickAction(Consumer<Player> onClickAction) {
        this.onClickAction = onClickAction;
    }
}
