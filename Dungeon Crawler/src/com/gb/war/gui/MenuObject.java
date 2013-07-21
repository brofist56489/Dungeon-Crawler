package com.gb.war.gui;

import com.gb.war.graphics.Bitmap;
import com.gb.war.input.KeyHandler.Key;
import com.gb.war.input.MouseHandler;

public abstract class MenuObject {
	protected int x;
	protected int y;
	protected int w;
	protected int h;

	protected Menu menu;

	public MenuObject(Menu m, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.setMenu(m);
	}

	public abstract void onKey(Key e);

	public abstract void onClick(MouseHandler m);

	public abstract void onHover(MouseHandler m);
	
	public abstract void onNotHover(MouseHandler m);

	public abstract void render(Bitmap b);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	

}