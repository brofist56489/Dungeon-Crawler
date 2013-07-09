package com.bh.war.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends Bitmap {

	private final int COLORKEY_1 = 0x3f3f3f;
	private final int COLORKEY_2 = 0x7f7f7f;
	private final int COLORKEY_3 = 0xafafaf;
	private final int COLORKEY_4 = 0xffffff;
	
	private final int[] preColorPixels;
	
	public BufferedImage image;
	private String name;
	
	public Image(String path, String name) {
		super(1, 1);
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] += 0x1000000;
		}
		
		preColorPixels = pixels;
		
		this.name = name;
	}
	
	public Image(int[] pixels, int w, int h) {
		super(w, h);
		this.pixels = pixels;
		this.preColorPixels = pixels;
	}
	
	public Image rotate(double radians) {
		if(radians == 0) return new Image(pixels, width, height);
		int x, y, fromX, fromY, toX, toY, transparent = 0x7f007f;
		
		double cosAngle = Math.cos(radians);
		double sinAngle = Math.sin(radians);
		
		int[] rotatedPixels = new int[width * height];
		
		for (y = 0; y < height; y++) {
			for (x = 0; x < width; x++) {
				toX = (width / 2) - x;
				toY = (height / 2) - y;
				fromX = (int)((toX * cosAngle) - (toY * sinAngle));
				fromY = (int)((toX * sinAngle) + (toY * cosAngle));
				fromX += width / 2;
				fromY += height / 2;
				
				if (fromX < 0 || fromX >= width || fromY < 0 || fromY >= height) {
					rotatedPixels[x + y * width] = transparent;
				} else {
					rotatedPixels[x + y * width] = pixels[fromX + fromY * width];
				}
			}
		}
		
		Image rotatedImage = new Image(rotatedPixels, width, height);
		return rotatedImage;
	}
	
	public String getName() {
		return name;
	}

	public void setColorKey(int c1, int c2, int c3, int c4) {
		pixels = preColorPixels;
		for(int i = 0; i < pixels.length; i++) {
			int col = pixels[i];
			if(col == COLORKEY_1) pixels[i] = c1;
			if(col == COLORKEY_2) pixels[i] = c2;
			if(col == COLORKEY_3) pixels[i] = c3;
			if(col == COLORKEY_4) pixels[i] = c4;
		}
	}
}
