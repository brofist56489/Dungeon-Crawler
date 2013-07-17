package com.gb.war.level.entities;

import com.gb.war.Game;
import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.input.KeyHandler;
import com.gb.war.input.MouseHandler;
import com.gb.war.items.BowItem;
import com.gb.war.items.CrossbowItem;
import com.gb.war.items.FireWandItem;
import com.gb.war.items.IceWandItem;
import com.gb.war.items.Inventory;
import com.gb.war.items.LightningWandItem;
import com.gb.war.items.SpearItem;
import com.gb.war.items.ThrowingKnifeItem;
import com.gb.war.level.Level;

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
		inventory.add(new ThrowingKnifeItem(5));
		inventory.add(new SpearItem(5));
		inventory.add(new LightningWandItem(5));
		inventory.add(new FireWandItem(5));
		inventory.add(new IceWandItem(5));
		inventory.add(new BowItem(5));
		inventory.add(new CrossbowItem(5));
	}
	
	public void tick() {
		ax = ay = 0;
		
		if(stamina < MAX_STAMINA && Game.tickCount % 4 == 0) {
			stamina += 1;
		}
		
		if(mana < MAX_MANA && Game.tickCount % 4 == 0) {
			mana  += 1;
		}
		
		if(Game.instance.menu != null) return;
		
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
			if(inventory.getItem(0, 0) != null) {
				inventory.getItem(0, 0).onLeftClick(mouse, level, this);
			}
		}
		
		if(mouse.isRightButton()) {
			int mx = mouse.getxPos() >> 4;
			int my = mouse.getyPos() >> 4;
			if(mx >= 0 && my >= 0)
				if (!level.getTile(mx, my).interact(this, level, mx, my))
					if(inventory.getItem(0, 0) != null)
						inventory.getItem(0, 0).onRightClick(mouse, level, this);
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
