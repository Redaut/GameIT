package com.theRebel.ld24.graphics;

public class Color {
	
	public static int changeBrightness(int col, int amount) {
		int r = (col & 0xff0000) >> 16;
		int g = (col & 0xff00) >> 8;
		int b = (col & 0xff);
		if(amount > 0) amount = 0;
		if(amount < -110) amount = -110;
		
		r += amount;
		g += amount;
		b += amount;
		
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		
		return r << 16 | g << 8 | b;
	}
	
	public static int tint(int col, int d, int e, int f) {
		int red = (col & 0xff0000) >> 16;
		int green = (col & 0xff00) >> 8;
		int blue = (col & 0xff);
		
		red += d;
		green += e;
		blue += f;
		
		if(red < 0) d = 0;
		if(green < 0) e = 0;
		if(blue < 0) f = 0;
		if(red > 255) d = 255;
		if(green > 255) e = 255;
		if(blue > 255) f = 255;
		
		return red << 16 | green << 8 | blue;
	}
	
}
