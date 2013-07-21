package com.gb.war.level.entities.projectile;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;

public class PenguinProjectile extends Projectile {

	public PenguinProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "ENTITY_PENGUIN");
		setKindness(EntityKindness.FRIENDLY);
		xo = 3;
		yo = 4;
		w = 11;
		h = 8;
	}
	
	public void tick() {
		super.tick();
		
	}
	
	public void preRender(Bitmap b) {
		b.addLightSource((int)x, (int)y, 20, 50);
	}
	
	public void render(Bitmap b) {
		ImageManager.renderFromImage(entityType, b, (int)x, (int)y, 2, 16);
	}
}
