package com.theRebel.ld24.level.tile;

import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.tile.Tile;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
	}


	
	public boolean solid() {
		return true;
	}
	
}
