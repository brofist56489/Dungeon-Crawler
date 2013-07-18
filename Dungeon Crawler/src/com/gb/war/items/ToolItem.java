package com.gb.war.items;



public class ToolItem extends Item {
	public static final int MAX_LEVEL = 5;
	
	protected int level;
	
	/**
	 * Creates a new ToolItem, such as a spear or wand.
	 * @param level The ranking of awesomeness, or tier, of a ToolItem
	 * @author Giant Behemoth
	 */
	
	public ToolItem(int level) {
		this.level = (level > MAX_LEVEL) ? MAX_LEVEL : level;
	}
}
