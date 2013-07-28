package com.gb.war.items;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.items.resources.Resource;
import com.gb.war.items.resources.ResourceItem;

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
	
	public boolean contains(Resource r) {
		boolean success = false;
		for(int i = 0; i < maxItems; i++) {
			if(items[i] != null && items[i] instanceof ResourceItem) {
				if(((ResourceItem)items[i]).getResource() == r) {
					success = true;
				}
			}
		}
		return success;
	}
	
	public void removeResource(Resource r, int count) {
		for(int i = 0; i < maxItems; i++) {
			if(items[i] != null && items[i] instanceof ResourceItem) {
				if(((ResourceItem)items[i]).getResource() != r) continue;
				((ResourceItem)items[i]).setCount(-count);
				if(((ResourceItem)items[i]).getCount() == 0) {
					removeItem(i);
				}
			}
		}
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
		if(slot == -1) {
			add(i);
			return;
		}
		if(items[slot] != null && items[slot] instanceof ResourceItem)
			((ResourceItem)items[slot]).setCount(((ResourceItem)items[slot]).getCount() + ((ResourceItem)i).getCount());
		else
			items[slot] = i;
	}
	
	public Item getItem(int x, int y) {
		return items[x + y * width];
	}
	
	public void removeItem(int x, int y) {
		removeItem(x + y * width);
	}
	
	public void removeItem(int index) {
		items[index] = null;
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
