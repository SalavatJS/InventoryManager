package com.github.salavatjs.inventorymanager.inventories;

import com.github.salavatjs.inventorymanager.inventories.items.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseInventory {

    protected List<BaseInventory> subInventories;

    protected BaseInventory previous, next, parent;

    protected Map<Integer, InventoryItem> items;

    protected boolean shouldClose;

    protected int size;

    public BaseInventory(int size, Map<Integer, InventoryItem> items) {
        subInventories = new ArrayList<>();
        this.items = items;
        this.size = size;
        shouldClose = true;
    }

    public abstract void openForPlayer(Player player);

    public abstract Inventory getInventoryForPlayer(Player player);

    public void onClick(Player player, int slot) {
        InventoryItem inventoryItem = items.get(slot);
        if (inventoryItem != null) inventoryItem.onClick(player);
    }

    public boolean isShouldClose() {
        return shouldClose;
    }

    public void setShouldClose(boolean shouldClose) {
        this.shouldClose = shouldClose;
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public List<BaseInventory> getSubInventories() {
        return subInventories;
    }

    public void addSubInventory(BaseInventory subInventory) {
        subInventories.add(subInventory);
    }

    public void removeSubInventories() {
        subInventories.clear();
    }

    public BaseInventory getParent() {
        return parent;
    }

    public void setParent(BaseInventory parent) {
        this.parent = parent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BaseInventory getPrevious() {
        return previous;
    }

    public void setPrevious(BaseInventory previous) {
        this.previous = previous;
    }

    public BaseInventory getNext() {
        return next;
    }

    public void setNext(BaseInventory next) {
        this.next = next;
    }
}
