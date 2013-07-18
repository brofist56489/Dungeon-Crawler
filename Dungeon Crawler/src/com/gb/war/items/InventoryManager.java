package com.gb.war.items;

import java.util.HashMap;

public class InventoryManager {
	private static HashMap<String, Inventory> inventories = new HashMap<String, Inventory>();
	
	public static Inventory getInv(String key) {
		return inventories.get(key);
	}

	public static void addInv(String key) {
		addInv(key, 25, 5);
	}
	
	public static void addInv(String key, int w, int h) {
		inventories.put(key, new Inventory(w, h));
	}
	
	public static void removeInv(String key) {
		inventories.remove(key);
	}
	
	public static Item getItem(String inv, int x, int y) {
		return inventories.get(inv).getItem(x, y);
	}
	
	public static void addItem(String inv, Item i, int slot) {
		inventories.get(inv).add(slot, i);
	}
	
	public static void addItem(String inv, Item i) {
		addItem(inv, i, -1);
	}
	
	public static void removeItem(String inv, int x, int y) {
		inventories.get(inv).removeItem(x, y);
	}
	
	public static void setItem(String inv, int x, int y, Item i) {
		inventories.get(inv).setItem(x, y, i);
	}
}
