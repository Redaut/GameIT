package com.theRebel.ld24.level.tile;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;

public class TreeTile extends Tile {

	public TreeTile(Sprite sprite) {
		super(sprite);
	}
	

	
	public boolean solid() {
		return true;
	}

}
