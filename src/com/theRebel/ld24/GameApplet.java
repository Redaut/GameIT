package com.theRebel.ld24;

import java.applet.Applet;

public class GameApplet extends Applet {

	private static final long serialVersionUID = 1L;
	
	Game game = new Game();
	
	public void init() {
		setLayout(null);
	}
	
	public void start() {
		game.stop();
	}
	
	public void stop() {
		game.stop();
	}
}
