package com.gb.war.items.resources;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.Font;
import com.gb.war.graphics.ImageManager;
import com.gb.war.input.MouseHandler;
import com.gb.war.items.Item;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class ResourceItem extends Item {
	private int count = 0;
	
	protected Resource resource;
	
	public ResourceItem(Resource r) {
		this(r, 1);
	}
	
	public ResourceItem(Resource r, int count) {
		this.count = count;
		resource = r;
	}
	
	public void render(Bitmap b, int xx, int yy) {
		ImageManager.renderFromImage("itemmap", b, xx, yy, resource.getTextId(), 16);
		if(count > 1) {
			int c = count;
			if(c >= 99) {
				c = 99;
			}
			Font.render(""+c, b, xx + 2, yy + 8);
		}
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		resource.interact(m, p, l);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count += count;
		if(this.count < 0) {
			this.count = 0;
		}
	}

	public Resource getResource() {
		return resource;
	}
}
