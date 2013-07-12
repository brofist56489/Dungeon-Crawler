package com.bh.war.level.generators;

import java.util.Random;

import com.bh.war.level.Level;
import com.bh.war.level.tiles.Tile;

public class DungeonGenerator extends LevelGenerator {

	private int w;
	private int h;
	
	public DungeonGenerator(Level l, int x, int y, int w, int h) {
		super(l);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void generate() {
		for(int yy=0; yy < h; yy++) {
			if((yy+y) < 0 || (yy + y) >= level.getHeight()) continue;
			
			for(int xx=0; xx < w; xx++) {
				if((xx+x) < 0 || (xx + x) >= level.getWidth()) continue;
				
				if(xx==0||yy==0||xx==w-1||yy==h-1) {
					level.setTile(xx+x, yy+y, Tile.DUNGEON_WALL);
					level.setSubTexture(xx+x, yy+y, 2);
				} else {
					level.setTile(xx+x, yy+y, Tile.DUNGEON_BACK);
					level.setSubTexture(xx+x, yy+y, 1);
				}
			}
		}
		
		Random r = new Random();
		int side = r.nextInt(4);
		int xp;
		int yp;
		switch(side) {
		default:
		case 0:
			xp = w / 2;
			yp = 0;
			break;
		case 1:
			xp = w-1;
			yp = h/2;
			break;
		case 2:
			xp = w / 2;
			yp = h-1;
			break;
		case 3:
			xp = 0;
			yp = h/2;
			break;
		}
		for(int i=0; i < 3; i++) {
			if(side%2==0) {
				level.setTile(xp+x+1-i,yp,Tile.DUNGEON_BACK);
				level.setSubTexture(xp+x+1-i, yp, 1);
			} else {
				level.setTile(xp,yp+y+1-i,Tile.DUNGEON_BACK);
				level.setSubTexture(xp, yp+y+1-i, 1);
			}
		}
	}
}
