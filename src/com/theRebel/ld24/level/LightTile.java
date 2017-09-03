package com.theRebel.ld24.level;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.level.tile.Tile;

public class LightTile {
	
	public int x, y;
	private Tile tile;
	
	public LightTile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Screen screen, Level level) {
		for(int yy = -3; yy <= 3; yy++) {
			for(int xx = -3; xx <= 3; xx++) {
				for(int i = 0; i < 4; i++) {
					int xt = ((x << 4) + (i%2*2-1) *8) >> 4;
					int yt = ((y << 4) + (i/2*2-1) *8) >> 4;
					tile = level.getTile(xt+xx, yt+yy);
				}
				int xTile = x*16 + xx*16;
				int yTile = y*16 + yy*16;
				double intensity = 1.0;
				screen.renderLight(xTile, yTile, tile, 50, 50, 50, intensity);
			}
		}
	}
	
}
