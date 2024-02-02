package me.ix.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	
	private int greenValue = 100;
	
	private int timeElapsed = 0;
	private int level = 1;
	
	public static long creationTime = System.currentTimeMillis();
	
	public int getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        return (int)((nowMillis - creationTime) / 1000);
    }
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH * 2;

		this.timeElapsed = getAgeInSeconds();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + Game.getFPS(), 15, Game.HEIGHT - 45);
		
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
		g.fillRect(15, 15, HEALTH * 2, 32);
		
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.black);
		g.drawString(HEALTH+"%", 93, 38);
		
		g.setColor(Color.white);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Time: " + timeElapsed, 15, 110);
	}

	public void resetTime() {
		creationTime = System.currentTimeMillis();
		setTime(0);
	}
	
	public int getTime() {
		return timeElapsed;
	}

	public void setTime(int time) {
		this.timeElapsed = time;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
