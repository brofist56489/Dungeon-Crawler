package com.bh.war.gui;

import com.bh.war.Game;
import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.Screen;
import com.bh.war.input.KeyHandler;
import com.bh.war.input.MouseHandler;
import com.bh.war.items.Inventory;
import com.bh.war.items.Item;
import com.bh.war.level.Level;
import com.bh.war.level.entities.Player;

public class InventoryMenu extends Menu {
	
	private MouseHandler mouse;
	private KeyHandler keys;
	
	private Item mouseItem;
	
	private Inventory inventory;
	
	public InventoryMenu(Game game, Level level, Inventory i) {
		super(game, level);
		inventory = i;
		mouse = game.mouse;
		keys = game.keys;
	}
	
	public InventoryMenu(Menu m, Inventory i) {
		super(m);
		mouse = game.mouse;
		keys = game.keys;
		inventory = i;
	}
	
	public void tick() {
		if(keys.inventory.isClicked()) {
			if(mouseItem != null) {
				inventory.add(mouseItem);
			}
			game.setMenu(null);
		}
		if(mouse.isLeftClickedButton()) {
			int xx = mouse.getxPos() - 53;
			int yy = mouse.getyPos() - 22;
			int xt = xx / 28;
			int yt = yy / 26;
			if(xt > 5 || yt > 5 || yt < 0 || xt < 0) return;
			if(mouseItem == null) {
				mouseItem = inventory.getItem(xt, yt);
				inventory.removeItem(xt, yt);
			} else {
				if(inventory.getItem(xt, yt) == null) {
					inventory.setItem(xt, yt, mouseItem);
					mouseItem = null;
				}
			}
		}
	}
	
	public void render(Bitmap screen) {
		Player player = game.player;
		((Screen)screen).setOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2);
		
		if(screen.lightingEnabled) {
			level.preRender(screen);
		}
		
		level.renderBackground(screen, (int)player.getX() + player.getW() / 2, (int)player.getY() + player.getH() / 2);
		level.renderSprites(screen);
		
		game.renderInterface();
		screen.fade(100);
		
		((Screen)screen).clearOffset();
		
		player.inventory.renderGrid(screen, 53, 22);
		if(mouseItem != null) {
			mouseItem.render(screen, mouse.getxPos() - 8, mouse.getyPos() - 8);
		}
	}
}
