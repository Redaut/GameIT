package com.theRebel.ld24.level.tile;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.tile.Tile;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
	
}
