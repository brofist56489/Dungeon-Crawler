package com.gb.war.graphics;

import java.util.HashMap;

public class ImageManager {
	private static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static void loadImage(String path, String name) {
		if(images.get(name) != null) {
			return;
		}
		Image i = new Image(path, name);
		images.put(name, i);
	}
	
	public static void unloadImage(String name) {
		images.remove(name);
		return;
	}
	
	public static Image getImage(String name) {
		return images.get(name);
	}
	
	public static void setColorKey(String name, int c1, int c2, int c3, int c4) {
		Image i = images.get(name);
		i.setColorKey(c1, c2, c3, c4);
	}
	
	public static void renderRotatedImage(String name, Bitmap b, int x, int y, double rad) {
		Image i = images.get(name);
		Image ri = i.rotate(rad);
		b.render(ri, x, y, 0, true);
	}
	
	public static void render(String name, Bitmap b, int x, int y, int flip) {
		render(name, b, x, y, flip, true);
	}
	
	public static void render(String name, Bitmap b, int x, int y, int flip, boolean light) {
		Image i = images.get(name);
		b.render(i, x, y, flip, light);
	}
	
	public static void render(String name, Bitmap b, int x, int y, boolean light) {
		render(name, b, x, y, 0, light);
	}
	
	public static void render(String name, Bitmap b, int x, int y) {
		render(name, b, x, y, 0);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth, int flip, boolean light) {
		Image i = images.get(name);
		b.renderFromImage(i, x, y, tileId, tileWidth, flip, light);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth) {
		renderFromImage(name, b, x, y, tileId, tileWidth, 0, true);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth, int flip) {
		renderFromImage(name, b, x, y, tileId, tileWidth, flip, true);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth, boolean light) {
		renderFromImage(name, b, x, y, tileId, tileWidth, 0, light);
	}
}
