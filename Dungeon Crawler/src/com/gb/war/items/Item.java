package com.gb.war.items;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;


public class Item {
	
	private String NAME = "";
	
	public static void init() {
		ImageManager.loadImage("/textures/itemmap.png", "itemmap");
	}
	
	protected int textId;
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
	}
	
	public void onMiddleClick(MouseHandler m, Level l, Player p) {
	}
	
	public void onRightClick(MouseHandler m, Level l, Player p) {
	}
	
	public void onPickUp(Player p, Level l) {
		p.inventory.add(this);
	}

	public void render(Bitmap b, int xx, int yy) {
		ImageManager.renderFromImage("itemmap", b, xx, yy, textId, 16, false);
	}
	
	public void setName(String name) {
		this.NAME = name;
	}

	public String getName() {
		return this.NAME;
	}
}
