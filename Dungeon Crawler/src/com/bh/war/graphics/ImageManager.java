package com.bh.war.graphics;

import java.util.ArrayList;
import java.util.List;

public class ImageManager {
	private static List<Image> images = new ArrayList<Image>();
	
	public static void loadImage(String path, String name) {
		Image i = new Image(path, name);
		images.add(i);
	}
	
	public static void unloadImage(String name) {
		Image i = getImageByName(name);
		images.remove(i);
		return;
	}
	
	public static Image getImageByName(String name) {
		for (Image i : images) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	public static void setColorKey(String name, int c1, int c2, int c3, int c4) {
		Image i = getImageByName(name);
		i.setColorKey(c1, c2, c3, c4);
	}
	
	public static void renderRotatedImage(String name, Bitmap b, int x, int y, double rad) {
		Image i = getImageByName(name);
		Image ri = i.rotate(rad);
		b.render(ri, x, y, 0);
	}
	
	public static void render(String name, Bitmap b, int x, int y, int flip) {
		Image i = getImageByName(name);
		b.render(i, x, y, flip);
	}
	
	public static void render(String name, Bitmap b, int x, int y) {
		render(name, b, x, y, 0);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth, int flip) {
		Image i = getImageByName(name);
		b.renderFromImage(i, x, y, tileId, tileWidth, flip);
	}
	
	public static void renderFromImage(String name, Bitmap b, int x, int y, int tileId, int tileWidth) {
		renderFromImage(name, b, x, y, tileId, tileWidth, 0);
	}
}
