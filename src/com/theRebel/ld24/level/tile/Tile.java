package com.theRebel.ld24.level.tile;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.tile.GrassGroundTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grassGround = new GrassGroundTile(Sprite.grassGround);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile tree = new TreeTile(Sprite.tree);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile torch = new TorchTile(Sprite.torch);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return false;
	}
	
}
