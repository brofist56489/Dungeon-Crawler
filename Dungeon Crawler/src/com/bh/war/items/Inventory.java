package com.bh.war.items;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;

public class Inventory {
	protected Item[] items;
	protected int maxItems;
	protected int width;
	protected int height;
	
	public Inventory(int maxItems, int w) {
		width = w;
		height = maxItems / w;
		this.maxItems = maxItems;
		items = new Item[maxItems];
	}
	
	public void add(Item i) {
		for(int it = 0; it < maxItems; it++) {
			if(items[it] == null || items[it] == i) {
				add(it, i);
				return;
			}
		}
	}
	
	public void add(int slot, Item i) {
		items[slot] = i;
	}
	
	public Item getItem(int x, int y) {
		return items[x + y * width];
	}
	
	public void removeItem(int x, int y) {
		items[x + y * width] = null;
	}
	
	public void renderGrid(Bitmap b, int xp, int yp) {
		int spacing = 0;
		for(int y = 0; y < height ; y++) {
			for(int x = 0; x < width; x++) {
				int xx = xp + (x + spacing) * 28;
				int yy = yp + (y + spacing) * 26;
				
				ImageManager.renderFromImage("tileMap", b, xx, yy, 14*16, 16, false);
				ImageManager.renderFromImage("tileMap", b, xx + 14, yy, 14*16 + 1, 16, false);
				ImageManager.renderFromImage("tileMap", b, xx, yy + 14, 15*16, 16, false);
				ImageManager.renderFromImage("tileMap", b, xx + 14, yy + 14, 15*16 + 1, 16, false);
				if(items[x + y * width] != null) {
					items[x + y * width].render(b, xx + 6, yy + 6);
				}
			}
		}
	}

	public void setItem(int xt, int yt, Item item) {
		items[xt + yt * width] = item;
	}
	
	public int getW() {
		return width;
	}
}
