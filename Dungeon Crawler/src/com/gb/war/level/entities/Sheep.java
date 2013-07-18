package com.gb.war.level.entities;

import java.util.Random;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class Sheep extends Mob {

	public Sheep(int x, int y, Level l) {
		super(x, y, l, "ENTITY_SHEEP");
		
		w = 16;
		h = 16;
		
		ANIMATION_DELAY_MAX = 10;
		ANIMATION_FRAME_MAX = 2;
	}
	
	public void tick() {
		Random r = new Random();
		if(r.nextInt(100) == 0)
			dir = r.nextInt(4);
		updateDir(1);
		super.move();
	}
	
	public void render(Bitmap b) {
		if(dir == 0) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 0, 16, ((animation_frame == 0) ? 1 : 0));
		} else if (dir == 1) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 1, 16, ((animation_frame == 0) ? 1 : 0));
		} else if (dir == 2) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 2 + ((animation_frame == 0) ? 1 : 0), 16);
		} else if (dir == 3) {
			ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 2 + ((animation_frame == 0) ? 1 : 0), 16, 1);
		}
	}
}
