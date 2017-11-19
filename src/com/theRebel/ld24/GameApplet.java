package com.theRebel.ld24;

import java.applet.Applet;
import java.awt.BorderLayout;
//import java.awt.BorderLayout;

public class GameApplet extends Applet {

	private static final long serialVersionUID = 1L;
	
	Game g = new Game();
	
	public void init() {
		
		setLayout(new BorderLayout()); 
		add(g);
	}
	
	public void start() {
		g.start();
	}
	
	public void stop() {
		g.stop();
	}
	
}




