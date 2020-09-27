package TennisGame;

import java.awt.Color;
import java.awt.Graphics;

public class AiPaddle implements Paddle{
	double y, yVel;
	boolean upAccel, downAccel;
	final double GRAVITY = 0.94;
	int player, x;
	Ball b1;

	public AiPaddle (int player, Ball b) {
		upAccel = false; downAccel = false;
		b1 = b;
		y = 210; yVel = 0;
		if(player == 1)
			x = 20;
		else
			x = 660;
		
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}


	public void move() {
		// TODO Auto-generated method stub
	
		y = b1.getY() - 40;
		
		if (y < 0)
			y = 0;
		if (y > 420)
			y = 420;
	}

	
	public int getY() {
		// TODO Auto-generated method stub
		return (int)y;
	}

}
