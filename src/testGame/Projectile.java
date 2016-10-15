package testGame;

import java.awt.Rectangle;

public class Projectile extends Entity{

	private int  speedX, speedY;
	private boolean visible;

	public Projectile(int startX, int startY, int sX, int sY) {
        super(x, y, new ProjectileSprite(startX,startY));
		speedX = sX;
		speedY = sY;
		visible = true;
	}

	public void update() {
		x += speedX;
		y += speedY;
		r.setBounds(x, y, 10, 5);
		if (x > 800 || x < 0 || y > 480 || y < 0) {
			visible = false;
			r = null;
		}

	}
    
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
