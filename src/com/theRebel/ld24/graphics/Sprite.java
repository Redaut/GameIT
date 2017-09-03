package com.theRebel.ld24.graphics;

public class Sprite {

	public static final int SIZE = 16;
	public int size;
	public int x, y;
	public int[] pixels;

	
	public static Sprite grassGround = new Sprite(0, 0, 16);
	public static Sprite stone = new Sprite(16, 0, 16);
	public static Sprite grass = new Sprite(32, 0, 16);
	public static Sprite flower = new Sprite(48, 0, 16);
	public static Sprite rock = new Sprite(64, 0, 16);
	public static Sprite tree = new Sprite(80, 0, 32);
	public static Sprite torch = new Sprite(0, 16, 16);
	
	public static Sprite player0 = new Sprite(0, 96, 16);
	public static Sprite player1 = new Sprite(16, 96, 16);
	public static Sprite player2 = new Sprite(32, 96, 16);
	public static Sprite player3 = new Sprite(48, 96, 16);
	public static Sprite player4 = new Sprite(64, 96, 16);
	public static Sprite player5 = new Sprite(80, 96, 16);
	
	public static Sprite voidSprite = new Sprite(0);
	
	
	public Sprite(int x, int y, int size) {
		this.size = size;
		pixels = new int[size * size];
		this.x = x;
		this.y = y;
		create(size);
	}
	
	public Sprite(int col) {
		pixels = new int[SIZE * SIZE];
		create(16, col);
	}

	public void create(int size) {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				pixels[x + y * size] = SpriteSheet.pixels[(x+this.x) + (y+this.y) * 256];
			}
		}
	}
	
public void create(int size, int col) {
	for(int y = 0; y < size; y++) {
		for(int x = 0; x < size; x++) {
			pixels[x+y * size] = col;
		}
	}
	}
	
}
