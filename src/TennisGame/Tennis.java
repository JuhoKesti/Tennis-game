package TennisGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Basic tennis game create with help from Youtube tutorial
// Tutorial can be found here: https://www.youtube.com/watch?v=xIqeK2hzx1I
// Credit to Krohn - Education
public class Tennis extends Applet implements Runnable, KeyListener{
	final int WIDTH = 700, HEIGHT= 500;
	Thread thread;
	HumanPaddle p1;
	AiPaddle p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	
	public void init () {
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		b1 = new Ball();
		p2 = new AiPaddle(2, b1);
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint (Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over!",350, 250);
		}
		else {
		p1.draw(gfx);
		b1.draw(gfx);
		p2.draw(gfx);
		}
		
		if (!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Tennis", 340, 100);
			gfx.drawString("Press Enter to begin...", 310, 130);
			gfx.drawString("Press ESC to exit.", 320, 160);
		}
		
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;) {
			if(gameStarted) {
			p1.move();
			p2.move();
			b1.move();
			b1.checkPaddleCollision(p1, p2);
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
	}
}
