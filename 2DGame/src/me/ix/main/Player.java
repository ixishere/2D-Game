package me.ix.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {

	private int playerWidth = 0;
	private int playerHeight = 0;
	
	Handler handler;
	
	public Player(int x, int y, ID id, int width, int height, Handler handler) {
		super(x, y, id);

		this.playerWidth = width;
		this.playerHeight = height;
		
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - (playerWidth + 8)); // 8 is fine tuning pixels
		y = Game.clamp(y, 0, Game.HEIGHT - (playerHeight + 28)); // 28 is fine tuning pixels
		
		collision();
	}

	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Enemy || tempObject.getID() == ID.TracerEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.BossEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) { // COLLISION
					HUD.HEALTH -= 2;
					
					Game.hurtTimerTrue = true;
					Game.timeOfHurt = System.currentTimeMillis();
					
				}
			}
		}
	}
	
	public void render(Graphics g) {
		/* METHOD TO DRAW THE BOUNDING BOX.. NOT NEEDED
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		*/
		
		g.setColor(Color.white);
		g.fillRect(x, y, playerWidth, playerHeight);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, playerWidth, playerHeight);
	}
}
