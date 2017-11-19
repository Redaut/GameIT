package com.theRebel.ld24.level.tile;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;

public class WaterTile extends Tile {

	public WaterTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public boolean solid() {
		return true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
