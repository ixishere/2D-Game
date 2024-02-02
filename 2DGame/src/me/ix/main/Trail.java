package me.ix.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private Handler handler;
	
	private float alpha = 1;
	private Color trailColor;
	
	private int trailWidth = 0;
	private int trailHeight = 0;
	private float trailLife = 0;
	
	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);

		this.trailWidth = width;
		this.trailHeight = height;
		this.trailLife = life;
		this.trailColor = color;
		
		this.handler = handler;
	}

	public void tick() {
		if(alpha > trailLife) {
			alpha -= trailLife - 0.0001;
		}else {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(this.trailColor);
		g.fillRect(x, y, trailWidth, trailHeight);
		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}

}
