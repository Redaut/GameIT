package com.theRebel.ld24;
//import com.theRebel.ld24.level.Level;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import com.theRebel.ld24.entity.Interface;
import com.theRebel.ld24.entity.Population;
import com.theRebel.ld24.entity.mob.Dummy;
import com.theRebel.ld24.entity.mob.Player;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.SpriteSheet;
import com.theRebel.ld24.input.InputHandler;
import com.theRebel.ld24.level.Level;
import com.theRebel.ld24.level.LightTile;
import com.theRebel.ld24.menu.MainMenu;
import com.theRebel.ld24.menu.Menu;
import com.theRebel.ld24.menu.PlayMenu;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public final int WIDTH = 400;
	public final int HEIGHT = WIDTH / 16 * 10;
	public final int SCALE = 2;
	public final String TITLE = "My Gasddddddddddddddddddddddddssssssssme";
	
	private boolean running = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private JFrame frame;
	
	//screen is imported through the import keyword and now declared
	private Screen screen;
	private Level level;
	private InputHandler input;
	private Player player;
	public static Menu menu;
	private Population pop ;
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		level = new Level("/levels/test.png");
		new SpriteSheet("/sprites.png");
		input = new InputHandler();
		menu = new MainMenu(input);
		//menu = new PlayMenu(input);
		player = new Player(input);
		//menulevel.add(player);
		for(int i = 0; i < 30; i++)
			level.add(new Dummy());
		addKeyListener(input);
	}

	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this, "GameThread");
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			while(System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(updates + " ups , " + frames + " frames");
				//frame.setTitle(TITLE + " | " + updates + " ups , " + frames + " frames");
				frames = 0;
				updates = 0;
			}
		}
	}
	
	int x, y;
	public void update() {
		input.update();
		if(menu != null) menu.update();
		if(pop != null) pop.update();
	
		level.update();

		
		if(PlayMenu.biome == 0) {
			level = new Level("/levels/forest.png");
			level.add(player);
			player.x = 60*16;
			player.y = 30*16;
			pop = new Population(level);
			PlayMenu.biome = -1;
		}
		
		if(PlayMenu.biome == 1) {
			level = new Level("/levels/ocean.png");
			level.add(player);
			player.x = 45*16;
			player.y = 27*16;
			pop = new Population(level);
			PlayMenu.biome = -1;
		}

		
		if(PlayMenu.biome == 2) {
			level = new Level("/levels/grass.png");
			level.add(player);
			player.x = 60*16;
			player.y = 30*16;
			pop = new Population(level);
			PlayMenu.biome = -1;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		screen.graphics(g);
		screen.clear();
		
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		
		level.render(xScroll, yScroll, screen);
		LightTile.lightTiles.clear();
		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		
		//don't need these now
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if(menu != null) menu.render(screen);
		if(pop != null) pop.render(screen);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame = new JFrame();
		game.frame.setResizable(false);
		game.frame.setTitle(game.TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		
		game.start();	
	}
}