package com.gb.war.items.resources;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class Resource {

	private int textId;
	private String name;
	public Resource(String name, int tId) {
		this.name = name;
		this.textId = tId;
	}
	
	public int getTextId() {
		return textId;
	}

	public String getName() {
		return name;
	}
	
	public boolean interact(MouseHandler m, Player p, Level l) {
		return false;
	}
	
}
