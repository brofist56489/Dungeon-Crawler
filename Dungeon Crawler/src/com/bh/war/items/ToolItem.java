package com.bh.war.items;

public class ToolItem extends Item {
	public static final int MAX_LEVEL = 5;
	
	protected int level;
	public ToolItem(int level) {
		this.level = (level > MAX_LEVEL) ? MAX_LEVEL : level;
	}
}
