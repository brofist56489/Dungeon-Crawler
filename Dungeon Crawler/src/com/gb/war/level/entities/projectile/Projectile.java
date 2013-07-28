package com.gb.war.level.entities.projectile;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.DamageIndicator;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.EntityKindness;
import com.gb.war.level.entities.Mob;

public abstract class Projectile extends Mob {

	protected int life = 300;
	protected double rangle;
	
	protected int damage = 0;
	
	public Projectile(int x, int y, float ax, float ay, Level l, String entityType) {
		super(x, y, l, entityType);
		this.ax = ax;
		this.ay = ay;
		getRad();
		setKindness(EntityKindness.FRIENDLY);
	}
	
	protected void getRad() {
		float nx = x + ax;
		float ny = y + ay;
		
		rangle = Math.PI - Math.atan2(ny - y, nx - x);
	}
	
	protected void tickLife() {
		life -= 1;
		if (life <= 0) {
			alive = false;
			return;
		}
	}
	
	public void tick() {
		tickLife();
		for(Entity e : level.getEntities()) {
			if (e.getRect().intersects(getRect()) && e.getKindness() != getKindness() && !(e instanceof Projectile)) {
				e.setHealth(-damage);
				alive = false;
				DamageIndicator i = new DamageIndicator((int)e.getX(), (int)e.getY() - 12, level, "" + damage);
				level.addEntity(i);
				break;
			}
		}
	}
	
	protected void setDamage(int d) {
		damage = d;
	}
	
	public void render(Bitmap b) {
		ImageManager.renderRotatedImage(entityType, b, (int)x - xo, (int)y - yo, rangle);
	}
}