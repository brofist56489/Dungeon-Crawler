package com.bh.war.level.entities;

import java.util.Random;

import com.bh.war.Game;
import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.input.KeyHandler;
import com.bh.war.input.MouseHandler;
import com.bh.war.items.FireWandItem;
import com.bh.war.items.Inventory;
import com.bh.war.level.Level;

public class Player extends Mob {
	
	private KeyHandler key;
	private MouseHandler mouse;
	
	public Inventory inventory;
	
	public Player(int x, int y, Level l, KeyHandler k, MouseHandler m) {
		super(x, y, l, "ENTITY_PLAYER");
		key = k;
		mouse = m;
		w = 16;
		h = 16;
		
		ANIMATION_FRAME_MAX = 2;
		ANIMATION_DELAY_MAX = 10;
		
		health = MAX_HEALTH = 100;
		mana = MAX_MANA = 100;
		stamina = MAX_STAMINA = 100;
		
		inventory = new Inventory(25, 5);
		Random r = new Random();
		for(int i = 0; i < 25; i++) {
			if(r.nextInt(5)==0)
				inventory.add(new FireWandItem(100));
		}
	}
	
	public void tick() {
		ax = ay = 0;
		if(key.right.isPressed()) {
			dir = 2;
			ax += 2;
		}
		if(key.left.isPressed()) {
			dir = 3;
			ax -= 2;
		}
		if(key.up.isPressed()) {
			dir = 0;
			ay -= 2;
		}
		if(key.down.isPressed()) {
			dir = 1;
			ay += 2;
		}
		if(key.sprint.isPressed() && stamina > 0) {
			ax *= 2;
			ay *= 2;
			if(ax != 0 || ay != 0)
				stamina -= 2;
		}
		super.tick();
		
		if(mouse.isLeftButton()) {
			if(inventory.getItem(0, 0) != null)
				inventory.getItem(0, 0).onLeftClick(mouse, level, this);
		}
		
		if(mouse.isRightButton()) {
			if(inventory.getItem(0, 0) != null)
				inventory.getItem(0, 0).onRightClick(mouse, level, this);
		}
		
		if(stamina < MAX_STAMINA && Game.tickCount % 4 == 0) {
			stamina += 1;
		}
		
		if(mana < MAX_MANA && Game.tickCount % 4 == 0) {
			mana  += 1;
		}
	}
	
	public void render(Bitmap b) {
		int xx = (int)x;
		int yy = (int)y;
		if(dir == 0) {
			if(animation_frame == 0) {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 3, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 2, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 11, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 10, 8, 1);
			} else {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 2, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 3, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 10, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 11, 8);
			}
			return;
		}
		if(dir == 1) {
			if(animation_frame == 0) {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 1, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 0, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 9, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 8, 8, 1);
			} else {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 0, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 1, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 8, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 9, 8);
			}
			return;
		}
		if(dir == 2) {
			if(animation_frame == 0) {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 4, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 5, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 12, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 13, 8);
			} else {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 6, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 7, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 14, 8);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 15, 8);
			}
			return;
		}
		if(dir == 3) {
			if(animation_frame == 0) {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 5, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 4, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 13, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 12, 8, 1);
			} else {
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy, 7, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy, 6, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx, yy + 8, 15, 8, 1);
				ImageManager.renderFromImage("ENTITY_PLAYER", b, xx + 8, yy + 8, 14, 8, 1);
			}
			return;
		}
	}
}
