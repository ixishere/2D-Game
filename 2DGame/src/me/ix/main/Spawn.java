package me.ix.main;

import java.awt.Color;
import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		
		if(hud.getTime() == Game.spawnRate) {
			hud.resetTime();
			hud.setLevel(hud.getLevel() + 1);
			
			/*
			if(hud.getLevel() % 5 == 0) {	
				System.out.println("FastBoi Spawned");
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, 16, 16, 8, 11, Color.orange, Color.red, handler));
			}
			else if(hud.getLevel() % 7 == 0) {
				System.out.println("TracerBoi Spawned");
				handler.addObject(new TracerEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.TracerEnemy, 16, 16, 8, 11, Color.PINK, Color.magenta, handler));
			}
			else {
				System.out.println("SlowBoi Spawned");
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, 16, 16, -6, 6, Color.blue, Color.green, handler));
			}*/
			
			
		}	
		
	}
	
}
