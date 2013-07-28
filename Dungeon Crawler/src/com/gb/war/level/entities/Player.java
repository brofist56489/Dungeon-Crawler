package com.gb.war.level.entities;

import java.awt.event.KeyEvent;

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
import com.gb.war.items.resources.Resource;
import com.gb.war.items.resources.ResourceItem;
import com.gb.war.level.Level;

public class Player extends Mob {
	
	private KeyHandler key;
	private MouseHandler mouse;
	
	public Inventory inventory;
	
	public Player(int x, int y, Level l, KeyHandler k, MouseHandler m) {
		super(x, y, l, "ENTITY_PLAYER");
		key = k;
		mouse = m;
		w = 14;
		h = 14;
		xo = 2;
		yo = 2;
		
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
		inventory.add(new ResourceItem(Resource.health_potion, 5));
		
		inventory.add(new ResourceItem(Resource.arrow, 67));
		inventory.add(new ResourceItem(Resource.rock, 10));
		
		setKindness(EntityKindness.FRIENDLY);
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
		
		if(key.isKeyDown(KeyEvent.VK_D)) {
			dir = 2;
			ax += 2;
		}
		if(key.isKeyDown(KeyEvent.VK_A)) {
			dir = 3;
			ax -= 2;
		}
		if(key.isKeyDown(KeyEvent.VK_W)) {
			dir = 0;
			ay -= 2;
		}
		if(key.isKeyDown(KeyEvent.VK_S)) {
			dir = 1;
			ay += 2;
		}
		if(key.isKeyDown(KeyEvent.VK_SHIFT) && stamina > 0) {
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
				if (!level.getTile(mx, my).interact(mouse, this, level, mx, my))
					if(inventory.getItem(0, 0) != null)
						inventory.getItem(0, 0).onRightClick(mouse, level, this);
		}
	}
	
	public void render(Bitmap b) {
		if(dir == 0) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 1, 16, ((animation_frame == 0) ? 1 : 0));
		} else if (dir == 1) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 0, 16, ((animation_frame == 0) ? 1 : 0));
		} else if (dir == 2) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 2 + ((animation_frame == 0) ? 1 : 0), 16);
		} else if (dir == 3) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 2 + ((animation_frame == 0) ? 1 : 0), 16, 1);
		}
	}
}
