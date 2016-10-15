package testGame;

import java.awt.Rectangle;

public class Projectile extends Entity{
	private boolean visible;

	public Projectile(int startX, int startY, int sX, int sY) {
        super(startX, startY, new ProjectileSprite(startX,startY));
		speedX = sX;
		speedY = sY;
		visible = true;
	}
    
	public int getX() {
		return centerX;
	}

	public int getY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.centerX = x;
	}

	public void setY(int y) {
		this.centerY = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
