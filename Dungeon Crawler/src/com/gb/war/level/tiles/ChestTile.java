package com.gb.war.level.tiles;

import com.gb.war.Game;
import com.gb.war.gui.InventoryMenu;
import com.gb.war.input.MouseHandler;
import com.gb.war.items.InventoryManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class ChestTile extends Tile {

	public ChestTile(int id) {
		super(id, true, 5);
	}
	
	public boolean interact(MouseHandler m, Player p, Level l, int x, int y) {
		int xx = x * 16;
		int yy = y * 16;
		if(Math.sqrt((p.getX() - xx) * (p.getX() - xx) + (p.getY() - yy) * (p.getY() - yy)) >= 18) return false;
		InventoryMenu e = new InventoryMenu(Game.instance, l);
		e.addInventory("Chest", InventoryManager.getInv("CHEST_"+x+"_"+y));
		e.addInventory("Player", p.inventory);
		Game.instance.setMenu(e);
		return true;
	}
}
