package com.theRebel.ld24;
//import com.theRebel.ld24.level.Level;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import com.theRebel.ld24.entity.mob.Dummy;
import com.theRebel.ld24.entity.mob.Player;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.SpriteSheet;
import com.theRebel.ld24.input.InputHandler;
import com.theRebel.ld24.level.Level;
import com.theRebel.ld24.level.LightTile;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public final int WIDTH = 400;
	public final int HEIGHT = WIDTH / 16 * 10;
	public final int SCALE = 2;
	public final String TITLE = "My Game";
	
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
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		level = new Level("/levels/test.png");
		new SpriteSheet("/sprites.png");
		input = new InputHandler();
		player = new Player(input);
		level.add(player);
		for(int i = 0; i < 100; i++)
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
				frame.setTitle(TITLE + " | " + updates + " ups , " + frames + " frames");
				frames = 0;
				updates = 0;
			}
		}
	}
	
	int x, y;
	public void update() {
		input.update();
		level.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		
		level.render(xScroll, yScroll, screen);
		LightTile.lightTiles.clear();
		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		//don't need these now
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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