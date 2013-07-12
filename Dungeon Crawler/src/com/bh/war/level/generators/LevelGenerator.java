package com.bh.war.level.generators;

import com.bh.war.level.Level;

public abstract class LevelGenerator {
	protected Level level;
	protected int x = 0;
	protected int y = 0;
	
	public LevelGenerator(Level l) {
		level = l;
	}
	
	public abstract void generate();
}
