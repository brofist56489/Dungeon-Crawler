package com.gb.war.level.entities.projectile;


import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;

public class RockProjectile extends Projectile {

	private double rad;
	
	public RockProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_ROCK");
		xo = 4;
		yo = 4;
		w = 8;
		h = 8;
		rad = 0;
		setKindness(EntityKindness.FRIENDLY);
	}
	
	public void tick() {
		if(super.move()) {
			alive = false;
		}
		super.tick();
		rad -= Math.PI / 8;
		if(rad < 0) {
			rad += Math.PI * 2;
		}
	}
	
	public void render(Bitmap b) {
		ImageManager.renderRotatedImage(entityType, b, (int)x, (int)y, rad);
	}
}