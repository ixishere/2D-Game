package me.ix.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7262741170277293815L;
	
	public static final int WIDTH = 700, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	private HUD hud;
	
	private Spawn spawn;
	
	public static int fps = 0;
	
	public static int spawnRate = 6;
	
	double tickRate = 60;

	public static boolean hurtTimerTrue;
	public static long timeOfHurt = 0;
	
	
	public Game() {
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		handler.addObject(new Player(WIDTH/2 -32, (HEIGHT/4 * 3) -32, ID.Player, 32, 32, handler));
		
		Random r = new Random();
		
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.Enemy, 16, 16, 4, 6, Color.blue, Color.green, handler));
		//handler.addObject(new TracerEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.TracerEnemy, 16, 16, 6, 11, Color.pink, Color.magenta, handler));
		
		
		handler.addObject(new BossEnemy((WIDTH/2) - 48, -120, ID.BossEnemy, 64, 64, 6, 11, Color.pink, Color.red, handler));
		
		
		new Window(WIDTH, HEIGHT, spawnRate + " Seconds, GO!", this);
		
		hud = new HUD();
		spawn = new Spawn(handler, hud);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double ns = 1000000000;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / (ns / tickRate);
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public void halfSpeed() {
		tickRate = 30;
	}
	
	public void normalSpeed() {
		tickRate = 60;
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawn.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(this.hurtTimerTrue) {
			g.setColor(Color.red);
		}else {
			g.setColor(Color.black);
		}
		if(System.currentTimeMillis() - this.timeOfHurt > 1) {
			this.hurtTimerTrue = false;
		}
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static int getFPS() {
		return fps;
	}
	
    public static void main(String[] args) {
        new Game();
    }
}
