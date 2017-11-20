package com.theRebel.ld24.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import com.theRebel.ld24.entity.mob.Mob;
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
	private Graphics g;
	
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
	
	public void graphics(Graphics g) {
		this.g = g;
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
						col = ExColor.changeBrightness(col, Level.brightness);
						pixels[xt + yt * width] = col;
						}
					}
				}
			}
	
	public void renderMob(int xp, int yp, Mob mob, int flip, boolean night) {
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
				int col = mob.sprite.pixels[xs + ys * 16];
				if(col != 0xffffffff){
					if(mob.lightDist < 0){
						col = ExColor.changeBrightness(col, Level.brightness);
					}
					else {
						col = ExColor.changeBrightness(col, (int) (mob.lightDist * (Level.brightness * 0.01)));
					}
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
					col = ExColor.tint(col, (int)(r*intensity), (int)(g*intensity), (int)(b*intensity));
					pixels[xt + yt * width] = col;
				}
			}
		}
	}
	
	public void renderText(String text, int x, int y, int size, int style, int color) {
		int r = (color & 0xff0000) >> 16;
		int g = (color & 0xff00) >> 8;
		int b = (color & 0xff);
		Color c = new Color(r, g, b);
		Font f = new Font("Verdana", style, size);
		this.g.setColor(c);
		this.g.setFont(f);
		this.g.drawString(text, x, y);
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	
	
}
