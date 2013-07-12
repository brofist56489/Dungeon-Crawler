package com.bh.war.items;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.input.MouseHandler;
import com.bh.war.level.Level;
import com.bh.war.level.entities.Player;


public class Item {
	
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
	}

	public void render(Bitmap b, int xx, int yy) {
		ImageManager.renderFromImage("itemmap", b, xx, yy, textId, 16, false);
	}
}
