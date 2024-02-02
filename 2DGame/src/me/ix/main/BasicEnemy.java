package me.ix.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	private int enemyWidth = 0;
	private int enemyHeight = 0;
	
	private Color trailColor;

	private Color enemyColor;
	
	private int minSpeed;
	private int maxSpeed;
	
	public BasicEnemy(int x, int y, ID id, int width, int height, int minspeed, int maxspeed, Color color, Color trailcolor, Handler handler) {
		super(x, y, id);
		
		this.enemyWidth = width;
		this.enemyHeight = height;
		this.enemyColor = color;
		this.trailColor = trailcolor;
		this.minSpeed = minspeed;
		this.maxSpeed = maxspeed;
		
		this.handler = handler;
		
		velX = (int) (Math.random() * (this.maxSpeed - this.minSpeed)) + this.minSpeed;
		velY = (int) (Math.random() * (this.maxSpeed - this.minSpeed)) + this.minSpeed;
	}

	public void tick() {

		
		
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50)
			velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 25)
			velX *= -1;
		
		handler.addObject(new Trail(x+5, y+5, ID.Trail, trailColor, enemyWidth/2, enemyHeight/2, 0.09f, handler));
	}

	public void render(Graphics g) {
		g.setColor(this.enemyColor);
		g.fillRect(x, y, enemyWidth, enemyHeight);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, enemyWidth, enemyHeight);
	}

}
