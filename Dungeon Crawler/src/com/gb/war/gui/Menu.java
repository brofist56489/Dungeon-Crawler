package com.gb.war.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.gb.war.Game;
import com.gb.war.graphics.Screen;
import com.gb.war.input.KeyHandler.Key;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;

public class Menu {
	protected Game game;
	protected Level level;
	
	protected Menu parentMenu;
	
	protected List<MenuObject> objects = new ArrayList<MenuObject>();
	protected MenuObject selected;
	
	public Menu(Game game, Level level) {
		this.game = game;
		this.level = level;
		selected = null;
		parentMenu = null;
		
		init();
	}
	
	public void init() {
	}
	
	public Menu(Menu m) {
		this.game = m.getGame();
		this.level = m.getLevel();
		selected = null;
		parentMenu = m;
		init();
	}
	
	public void onKey(Key e) {
		if(selected != null)
			selected.onKey(e);
	}
	
	public void onMove(MouseHandler m) {
		Point mx = new Point(m.getX(), m.getY());
		for(MenuObject o : objects) {
			Rectangle or = new Rectangle(o.x, o.y, o.w, o.h);
			if(or.contains(mx)) {
				o.onHover(m);
			} else {
				o.onNotHover(m);
			}
		}
	}
	
	public void onClick(MouseHandler m) {
		if(!m.isLeftClickedButton()) return;
		findIntersection(m.getX(), m.getY());
		if(selected != null)
			selected.onClick(m);
	}
	
	public void findIntersection(int x, int y) {
		Point mx = new Point(x, y);
		for(MenuObject o : objects) {
			Rectangle or = new Rectangle(o.x, o.y, o.w, o.h);
			if(or.contains(mx)) {
				selected = o;
				return;
			}
		}
		selected = null;
	}
	
	public void tick() {
	}
	
	public void render(Screen screen) {
		for(MenuObject o : objects) {
			o.render(screen);
		}
	}

	public Game getGame() {
		return game;
	}

	public Level getLevel() {
		return level;
	}
}