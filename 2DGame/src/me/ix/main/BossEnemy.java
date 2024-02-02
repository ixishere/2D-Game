package me.ix.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends GameObject {

	private Handler handler;
	
	private int enemyWidth = 0;
	private int enemyHeight = 0;
	
	private Color trailColor;

	private Color enemyColor;
	
	private int minSpeed;
	private int maxSpeed;
	
	private int timer = 100;
	
	private boolean[] stage = new boolean[10];
	
	public BossEnemy(int x, int y, ID id, int width, int height, int minspeed, int maxspeed, Color color, Color trailcolor, Handler handler) {
		super(x, y, id);
		
		this.enemyWidth = width;
		this.enemyHeight = height;
		this.enemyColor = color;
		this.trailColor = trailcolor;
		this.minSpeed = minspeed;
		this.maxSpeed = maxspeed;
		
		this.handler = handler;
		
		for(int i = 0; i < stage.length; i++) {
			stage[i] = false;
			System.out.println("Stage " + i);
		}
			
		stage[0] = true;
		
		if(stage[9] == true) System.out.println("Didnt work");
		
		velX = 0;
		velY = 2;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		// Initiate Boss Battle
		if(stage[0]) {
			if(timer <= 0) {
				velY = 0;
				stage[0] = false;
				stage[1] = true;
				timer = 10;
				velX = 15;
			}else {
				timer--;
			}	
		}
		if(stage[1]) {
			if(timer <= 0) {
				stage[1] = false;
				stage[2] = true;
				timer = 10;
				velX = -15;
			}else {
				timer--;
			}	
		}
		if(stage[2]) {
			if(timer <= 0) {
				velX = 0;
				stage[2] = false;
				stage[3] = true;
				timer = 10;
			}else {
				timer--;
			}	
		}
		
		
		handler.addObject(new Trail(x, y, ID.Trail, trailColor, enemyWidth, enemyHeight, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(this.enemyColor);
		g.fillRect(x, y, enemyWidth, enemyHeight);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, enemyWidth, enemyHeight);
	}

}
