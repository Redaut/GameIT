package com.theRebel.ld24.graphics;

import java.util.Random;

import com.theRebel.ld24.level.Level;
import com.theRebel.ld24.level.tile.Tile;
import com.theRebel.ld24.level.tile.TorchTile;

public class Screen {
	
	public int height, width;
	public int[] pixels;
	private int xOffset, yOffset;
	
	final int MAP_SIZE = 64;
	final int MAP_SIZE_MASK = MAP_SIZE - 1;
	final int TILE_SIZE = 16;
	final Random random = new Random();
	int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	public Screen(int width, int height) {
		this.height = height;
		this.width = width;
		pixels = new int[width * height];
		
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
				xp -= xOffset;
				yp -= yOffset;
				for(int y = 0; y < tile.sprite.size; y++){
					int yt = y + yp;
					for(int x = 0; x < tile.sprite.size; x++){
						int xt = x + xp;
						if(xt < -tile.sprite.size || xt >= width || yt < -tile.sprite.size || yt >= height) break;
						if(xt < 0) xt = 0;
						if(yt < 0) yt = 0;
						int col = tile.sprite.pixels[x + y * tile.sprite.size];
						if(col != 0xffffffff ){
							if(!(tile instanceof TorchTile))
						col = Color.changeBrightness(col, Level.brightness);
						pixels[xt + yt * width] = col;
						}
					}
				}
			}
	
	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 16; y++){
			int yt = y + yp;
			int ys = y;
			if(flip == 2 || flip == 3)
				ys = 15 - y;
			for(int x = 0; x < 16; x++){
				int xt = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3)
					xs = 15 - x;
				if(xt < -15 || xt >= width || yt < -16 || yt >= height) break;
				if(xt < 0) xt = 0;
				if(yt < 0) yt = 0;
				int col = sprite.pixels[xs + ys * 16];
				if(col != 0xffffffff){
					col = Color.changeBrightness(col, Level.brightness);
					pixels[xt + yt * width] = col;
				}
			}
		}
		
	}
	
	public void renderLight(int xp, int yp, Tile tile, int r, int g, int b, double intensity) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 16; y++){
			int yt = y + yp;
			int ys = y;
				ys = 15 - y;
			for(int x = 0; x < 16; x++){
				int xt = x + xp;
				int xs = x;
					xs = 15 - x;
				if(xt < -15 || xt >= width || yt < -16 || yt >= height) break;
				if(xt < 0) xt = 0;
				if(yt < 0) yt = 0;
				int col = tile.sprite.pixels[xs + ys * 16];
				if(col != 0xffffffff){
					col = Color.tint(col, (int)(r*intensity), (int)(g*intensity), (int)(b*intensity));
					pixels[xt + yt * width] = col;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	
	
}
