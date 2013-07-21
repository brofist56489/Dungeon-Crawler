package com.gb.war.level.entities;

import java.awt.Rectangle;

import com.gb.war.level.Level;
import com.gb.war.level.tiles.Tile;

public class Mob extends Entity {

	public Mob(int x, int y, Level l, String entityType) {
		super(x, y, l, entityType);
	}

	public void tick() {
		if(ax!=0 || ay!=0)
			move();
		super.tick();
	}
	
	protected boolean move() {
		boolean xcoll = checkCollision(ax, 0);
		boolean ycoll = checkCollision(0, ay);
		if(!xcoll) {
			x += ax;
		}
		if(!ycoll) {
			y += ay;
		}
		if(!xcoll || !ycoll) {
			animation_delay -= 1;
			if(animation_delay <= 0) {
				animation_delay = ANIMATION_DELAY_MAX;
				animation_frame += 1;
				if(animation_frame >= ANIMATION_FRAME_MAX) {
					animation_frame = 0;
				}
			}
		}
		return xcoll | ycoll;
	}
	
	protected boolean checkCollision(float ax, float ay) {
		float nx = (x + xo) + ax;
		float ny = (y + yo) + ay;
		int cx = (int)nx >> 4;
		int cy = (int)ny >> 4;
		
		Rectangle srect = new Rectangle((int)nx, (int)ny, w, h);
		
		for(int sy = cy - 2; sy < cy + 3; sy++) {
			for (int sx = cx - 2; sx < cx + 3; sx++) {
				
				Tile t = level.getTile(sx, sy);
				if(!t.isSolid()) continue;
				if(srect.intersects(t.getCollBox(sx, sy, level))) return true;
			}
		}
		
		return false;
	}
}
