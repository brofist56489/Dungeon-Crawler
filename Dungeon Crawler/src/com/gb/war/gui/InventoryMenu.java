package com.gb.war.gui;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import com.gb.war.Game;
import com.gb.war.graphics.Font;
import com.gb.war.graphics.Screen;
import com.gb.war.input.KeyHandler;
import com.gb.war.input.MouseHandler;
import com.gb.war.items.Inventory;
import com.gb.war.items.Item;
import com.gb.war.items.resources.ResourceItem;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class InventoryMenu extends Menu {
	
	private MouseHandler mouse;
	private KeyHandler keys;
	
	private Item mouseItem;
	
	private HashMap<String, Inventory> inventories;
	
	private Inventory inventory;
	private String selectedInvName;
	
	public InventoryMenu(Game game, Level level) {
		super(game, level);
		mouse = game.mouse;
		keys = game.keys;
		inventories = new HashMap<String, Inventory>();
	}
	
	public void addInventory(String name, Inventory i) {
		if(inventories.isEmpty()) {
			inventory = i;
			selectedInvName = name;
		}
		inventories.put(name, i);
	}
	
	public void tick() {
		level.tick();
		if(keys.isKeyPressed(KeyEvent.VK_E)) {
			if(mouseItem != null) {
				inventory.add(mouseItem);
			}
			game.setMenu(null);
		}
		if(mouse.isLeftClickedButton()) {
			int xx = mouse.getxPos();
			int yy = mouse.getyPos();
			
			if(xx < 53 && yy > 48) {
				int ys = (int)((yy - 48) / 26);
				if(ys < inventories.size()) {
					selectedInvName = (String) inventories.keySet().toArray()[ys];
					inventory = inventories.get(selectedInvName);
				}
				return;
			}
			
			xx -= 53;
			yy -= 22;
			int xt = xx / 28;
			int yt = yy / 26;
			if(xt >= inventory.getW() || yt >= inventory.getW() || yt < 0 || xt < 0) return;
			if(mouseItem == null) {
				mouseItem = inventory.getItem(xt, yt);
				inventory.removeItem(xt, yt);
			} else {
				if(inventory.getItem(xt, yt) == null) {
					inventory.setItem(xt, yt, mouseItem);
					mouseItem = null; 
				} else {
					Item tmpItem = mouseItem;
					mouseItem = inventory.getItem(xt, yt);
					inventory.setItem(xt, yt, tmpItem);
				}
			}
		}
	}
	
	public void render(Screen screen) {
		Player player = game.player;
		level.applyOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2, screen);
		
		if(screen.lightingEnabled) {
			level.preRender(screen);
		}
		
		level.renderBackground(screen, (int)player.getX() + player.getW() / 2, (int)player.getY() + player.getH() / 2);
		level.renderSprites(screen);
		
		game.renderInterface();
		screen.fade(100);
		
		screen.clearOffset();
		
		int i = 0;
		for(String s : inventories.keySet()) {
			if(s == selectedInvName) {
				screen.renderRect(0, 52+i*26, 60, 12, 0x7f7f7f, false);
			}
			Font.render(s, screen, 0, 53 + i * 26);
			i++;
		}
		
		inventory.renderGrid(screen, 60, 22);
		if(mouseItem != null) {
			mouseItem.render(screen, mouse.getxPos() - 8, mouse.getyPos() - 8);
			String name = "";
			if(mouseItem instanceof ResourceItem) {
				name = ((ResourceItem)mouseItem).getResource().getName();
			} else {
				name = mouseItem.getName();
			}
			Font.render(name, screen, mouse.getxPos() - (name.length() * 5), mouse.getyPos() - 20);
		}
		
		super.render(screen);
	}
}
