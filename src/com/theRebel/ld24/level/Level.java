package com.theRebel.ld24.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.level.tile.Tile;
import com.theRebel.ld24.level.tile.TorchTile;
import com.theRebel.ld24.menu.GameOverMenu;
import com.theRebel.ld24.Game;
import com.theRebel.ld24.entity.Entity;

public class Level {
	
	public int width, height;
	public int[] tiles;
	public static int brightness;
	int time = 0;
	private boolean day = false, night = false;
	public Random random = new Random();
	int[] grass;
	public int timerm = 3;
	public int timers = 0;
	public String timerString = "";
	public static boolean play = true;
	
	public List<Entity> entities = new ArrayList<Entity>();
	private List<LightTile> lightTiles = new ArrayList<LightTile>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		//generatelevel();
	}
	
	public Level(String path) {
		loadLevelFromFile(path);
		for(int i = 0; i < grass.length; i++) {
			grass[i] = random.nextInt(8);
		}
	}

	private void loadLevelFromFile(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = this.width = image.getWidth();
			int h = this.height = image.getHeight();
			tiles = new int[w * h];
			grass = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void generatelevel() {
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x + y * width] = random.nextInt(5);
			}
		}
	}
	
	public void update() {
		time++;
		if(time % 100 == 0) {
			time();
		}
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	public void time() {
		if(brightness < -240) {
			night = true;
			day = false;
		}
		if(brightness > 40) {
			night = false;
			day = true;
		}
		
		if(day) {
			brightness--;
			return;
		}
		if(night) {
			brightness++;
			return;
		}
		brightness++;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 25) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 15) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				Tile tile = getTile(x, y);
				if(tile instanceof TorchTile) lightTiles.add(new LightTile(x, y));
					tile.render(x, y, screen);
			}
		}
		
		for(int y = y0-1; y < y1+1; y++) {
			for(int x = x0 - 1; x < x1+1; x++) {
				getTile2(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
		for(int i = 0; i < lightTiles.size(); i++) {
			lightTiles.get(i).render(screen, this);
		}
		lightTiles.clear();
	}
	
	public void add(Entity e) {
		entities.add(e);
		e.init(this);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) return Tile.stone;
		if(tiles[x+y*width] == 0xffffffff || tiles[x+y*width] == 0xff7F3300 || tiles[x+y*width] == 0xffFFDE80) return Tile.grass;
		if(tiles[x+y*width] == 0xff00FFFF) return Tile.rock;
		if(tiles[x+y*width] == 0xffffffff && grass[ x + y * width] == 0) return Tile.grassGround;
		if(tiles[x+y*width] == 0xff007F0E) return Tile.flower;
		if(tiles[x+y*width] == 0xff65A2FF) return Tile.water; 
		if(tiles[x+y*width] == 0xff7F3300) return Tile.tree;
		return Tile.voidTile;
	}
	public Tile getTile2(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		if(tiles[x+y*width] == 0xff7F3300) return Tile.tree;
		if(tiles[x+y*width] == 0xffFFDE80) return Tile.torch;
		
		return Tile.voidTile;
	}

	public void updateTimer() {
		
		
		timerString = timerm + ":" + timers;
		if (timers == 0 && timerm != 0) timerString = timerm + ":" + 0 + "0";
		if (timers < 10) timerString = timerm + ":" + "0" + timers;
		if (timers <= 0 && timerm != 0) {
			timers = 60;
			timerm--;
		}

		timers--;
		if (timers <= 0 && timerm <= 0 && play) {
			play = false;
			Game.menu = new GameOverMenu(Game.input);
		}
		
	}

	public void renderTimer(Screen screen) {
		// TODO Auto-generated method stub
		if(play) screen.renderText(timerString, 700, 700, 30, 0);
	}
	
}
