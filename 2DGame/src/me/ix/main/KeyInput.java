package me.ix.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	private boolean[] KeyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		KeyDown[0] = false;
		KeyDown[1] = false;
		KeyDown[2] = false;
		KeyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player) {	
				
				switch(key) {
				  case KeyEvent.VK_W:
					tempObject.setVelY(-5);
					KeyDown[0] = true;
				    break;
				  case KeyEvent.VK_S:
					tempObject.setVelY(5);
					KeyDown[1] = true;
				    break;
				  case KeyEvent.VK_A:
					tempObject.setVelX(-5);
					KeyDown[2] = true;
				    break;
				  case KeyEvent.VK_D:
					tempObject.setVelX(5);
					KeyDown[3] = true;
				    break;
				}
				
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player) {	
				
				switch(key) {
				  case KeyEvent.VK_W:
					  KeyDown[0] = false;
				    break;
				  case KeyEvent.VK_S:
					  KeyDown[1] = false;
				    break;
				  case KeyEvent.VK_A:
					  KeyDown[2] = false;
				    break;
				  case KeyEvent.VK_D:
					  KeyDown[3] = false;
				    break;
				  case KeyEvent.VK_ESCAPE:
					System.exit(1);
				    break;
				}
				
				// Vertical Movement
				
				if(!KeyDown[0] && !KeyDown[1]) {
					tempObject.setVelY(0);
				}
				
				// Horizontal Movement
				
				if(!KeyDown[2] && !KeyDown[3]) {
					tempObject.setVelX(0);
				}
				
			}
		}
	}
	
}
