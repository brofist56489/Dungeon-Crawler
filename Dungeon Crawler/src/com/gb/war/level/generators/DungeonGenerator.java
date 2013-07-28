package com.gb.war.level.generators;

import java.awt.Point;
import java.util.Random;

import com.gb.war.items.InventoryManager;
import com.gb.war.items.RandomItem;
import com.gb.war.level.Level;
import com.gb.war.level.tiles.Tile;

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
		for (int yy = 0; yy < h; yy++) {
			if ((yy + y) < 0 || (yy + y) >= level.getHeight())
				continue;

			for (int xx = 0; xx < w; xx++) {
				if ((xx + x) < 0 || (xx + x) >= level.getWidth())
					continue;

				if (xx == 0 || yy == 0 || xx == w - 1 || yy == h - 1) {
					level.setTile(xx + x, yy + y, Tile.DUNGEON_WALL);
					level.setSubTexture(xx + x, yy + y, 2);
				} else {
					level.setTile(xx + x, yy + y, Tile.DUNGEON_BACK);
					level.setSubTexture(xx + x, yy + y, 1);
				}
			}
		}

		generateChests();

		Random ran = new Random();
		int side = ran.nextInt(4);
		int xp;
		int yp;
		switch (side) {
		default:
		case 0:
			xp = w / 2;
			yp = 0;
			break;
		case 1:
			xp = w - 1;
			yp = h / 2;
			break;
		case 2:
			xp = w / 2;
			yp = h - 1;
			break;
		case 3:
			xp = 0;
			yp = h / 2;
			break;
		}
		level.setTile(xp + x, yp + y, Tile.DOOR);
		level.setSubTexture(xp + x, yp + y, Tile.DUNGEON_BACK.getId());
		level.setData(xp + x, yp + y, 3 - side);
//		if (side % 2 == 0) {
//			level.setData(xp + x, yp + y, 0);
//		} else {
//			level.setData(xp + x, yp + y, 2);
//		}
	}

	private void generateChests() {
		Random rand = new Random();
		Point[] chests = new Point[rand.nextInt(5) + 1];
		for (int i = 0; i < chests.length; i++) {
			boolean a = rand.nextBoolean();
			chests[i] = new Point();
			chests[i].x = ((a) ? rand.nextInt(w - 2) + 1 : ((rand.nextBoolean()) ? 1 : w - 2));
			chests[i].y = ((a) ? ((rand.nextBoolean()) ? 1 : h - 2) : (rand.nextInt(h - 2) + 1));

			if (level.getTile(chests[i].x + x, chests[i].y + y) != Tile.DUNGEON_BACK)
				i--;
			else {
				level.generateChest(chests[i].x + x, chests[i].y + y);
				int r = rand.nextInt(5);
				for(int j = 0; j < r; j++)
					InventoryManager.addItem("CHEST_" + (chests[i].x + x) + "_" + (chests[i].y + y), RandomItem.genRanItem());
			}
			
		}
	}
}
