package com.gb.war.graphics;

public abstract class Bitmap {

	private final int BIT_MIRROR_X = 0x01;
	private final int BIT_MIRROR_Y = 0x02;

	public int xOff = 0;
	public int yOff = 0;

	public int[] pixelLighting;

	public int[] pixels;
	protected int width;
	protected int height;

	public boolean lightingEnabled;

	public Bitmap(int w, int h) {
		this.width = w;
		this.height = h;
		this.pixels = new int[w * h];
		this.pixelLighting = new int[w * h];
		lightingEnabled = false;
	}

	public void clearLighting(int br) {
		if (!lightingEnabled)
			return;
		for (int i = 0; i < pixelLighting.length; i++) {
			pixelLighting[i] = br;
		}
	}

	public void fill(int c) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = c;
		}
	}
	
	public void render(Bitmap b, int x, int y, int flip, boolean light) {
		render(b.pixels, x, y, b.getWidth(), b.getHeight(), flip, light);
	}

	public void render(int[] pixs, int xp, int yp, int w, int h, int flip, boolean light) {
		xp -= xOff;
		yp -= yOff;

		boolean flipx = (flip & BIT_MIRROR_X) == BIT_MIRROR_X;
		boolean flipy = (flip & BIT_MIRROR_Y) == BIT_MIRROR_Y;

		for (int y = 0; y < h; y++) {
			if ((y + yp) < 0 || (y + yp) >= height)
				continue;
			int ys = y;
			if (flipy)
				ys = (h - 1) - y;
			for (int x = 0; x < w; x++) {
				if ((x + xp) < 0 || (x + xp) >= width)
					continue;
				int xs = x;
				if (flipx)
					xs = (w - 1) - x;

				int c = pixs[xs + ys * w];
				if (c == 0x7f007f)
					continue;

				if (lightingEnabled && light) {
					c = applyLighting(c, x + xp, y + yp);
				}
				pixels[(x + xp) + (y + yp) * width] = c;
			}
		}
	}

	public void renderFromImage(Image i, int xp, int yp, int tileId, int tileWidth, int flip) {
		renderFromImage(i, xp, yp, tileId, tileWidth, flip, true);
	}

	public void renderFromImage(Image i, int xp, int yp, int tileId, int tileWidth, int flip, boolean light) {
		xp -= xOff;
		yp -= yOff;

		boolean flipx = (flip & BIT_MIRROR_X) == BIT_MIRROR_X;
		boolean flipy = (flip & BIT_MIRROR_Y) == BIT_MIRROR_Y;

		int tw = i.getWidth() / tileWidth;
		int xt = tileId % tw;
		int yt = tileId / tw;
		int tileOffset = xt * tileWidth + yt * tileWidth * i.width;

		for (int y = 0; y < tileWidth; y++) {
			if ((y + yp) < 0 || (y + yp) >= height)
				continue;
			int ys = y;
			if (flipy)
				ys = (tileWidth - 1) - y;

			for (int x = 0; x < tileWidth; x++) {
				if ((x + xp) < 0 || (x + xp) >= width)
					continue;
				int xs = x;
				if (flipx)
					xs = (tileWidth - 1) - x;

				int c = i.pixels[xs + (ys * i.width) + tileOffset];
				if (c == 0x7f007f)
					continue;
				if (lightingEnabled && light) {
					c = applyLighting(c, x + xp, y + yp);
				}
				pixels[(x + xp) + (y + yp) * width] = c;
			}
		}
	}
	
	public void renderCircle(int x, int y, int r, int c) {
		renderCircle(x, y, r, c, true);
	}

	public void renderCircle(int x, int y, int r, int c, boolean light) {
		x -= xOff;
		y -= yOff;

		for (int yy = -r; yy < r; yy++) {
			if ((yy + y) < 0 || (yy + y) >= height)
				continue;
			for (int xx = -r; xx < r; xx++) {
				if ((xx + x) < 0 || (xx + x) >= width)
					continue;
				int d = (xx * xx) + (yy * yy);
				if (d >= r * r)
					continue;

				if (lightingEnabled && light) {
					c = applyLighting(c, xx + x, yy + y);
				}

				pixels[(xx + x) + (yy + y) * width] = c;
			}
		}
	}

	public void renderRect(int x, int y, int w, int h, int c) {
		renderRect(x, y, w, h, c, true);
	}

	public void renderRect(int x, int y, int w, int h, int c, boolean light) {
		x -= xOff;
		y -= yOff;

		for (int yy = 0; yy < h; yy++) {
			if ((yy + y) < 0 || (yy + y) >= height)
				continue;
			for (int xx = 0; xx < w; xx++) {
				if ((xx + x) < 0 || (xx + x) >= width)
					continue;

				int col = c;
				if (lightingEnabled && light) {
					col = applyLighting(col, xx + x, yy + y);
				}
				pixels[(xx+x) + (yy+y) * width] = col;
			}
		}
	}
	
	public void fade(int br) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = applyLighting(pixels[i], br);
		}
	}

	protected int applyLighting(int c, int x, int y) {
		if (!lightingEnabled)
			return c;

		int br = pixelLighting[x + y * width];
		return applyLighting(c, br);
	}

	protected int applyLighting(int c, int br) {
		if (br > 255)
			return c;
		int r = (c >> 16) & 0xff;
		int g = (c >> 8) & 0xff;
		int b = (c) & 0xff;

		r = r * br / 255;
		g = g * br / 255;
		b = b * br / 255;

		c = r << 16 | g << 8 | b;
		return c;
	}

	public void addLightSource(int x, int y, int r, int br) {
		x -= xOff;
		y -= yOff;
		for (int yy = -r; yy < r; yy++) {
			if ((yy + y) < 0 || (yy + y) >= height)
				continue;
			for (int xx = -r; xx < r; xx++) {
				if ((xx + x) < 0 || (xx + x) >= width)
					continue;

				int d = (xx * xx) + (yy * yy);
				if (d > r * r)
					continue;

				int i = (xx + x) + (yy + y) * width;
				pixelLighting[i] += br - d * br / (r * r);
				if (pixelLighting[i] >= 255) {
					pixelLighting[i] = 255;
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
