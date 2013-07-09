package com.bh.war.level.entities.projectile;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.level.Level;
import com.bh.war.level.entities.Mob;

public abstract class Projectile extends Mob {

	protected int life = 300;
	protected double rangle;
	
	public Projectile(int x, int y, float ax, float ay, Level l, String entityType) {
		super(x, y, l, entityType);
		this.ax = ax;
		this.ay = ay;
		getRad();
	}
	
	protected void getRad() {
		float nx = x + ax;
		float ny = y + ay;
		
		rangle = Math.PI - Math.atan2(ny - y, nx - x);
	}
	
	public void tick() {
		life -= 1;
		if (life <= 0) {
			alive = false;
			return;
		}
		if(super.move()) {
			alive = false;
		}
	}
	
	public void render(Bitmap b) {
		ImageManager.renderRotatedImage(entityType, b, (int)x - xo, (int)y - yo, rangle);
	}
}