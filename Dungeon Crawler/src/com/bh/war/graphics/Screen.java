package com.bh.war.graphics;

public class Screen extends Bitmap {
	
	public Screen(int w, int h) {
		super(w, h);
	}
	
	public void setOffset(float x, float y) {
		xOff = (int)x - (width / 2);
		yOff = (int)y - (height / 2);
	}
	
	public void clearOffset() {
		xOff = yOff = 0;
	}
	
	public int[] setPixels(int[] p) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p[x + y * width] = pixels[x + y * width];
			}
		}
		return p;
	}
}
