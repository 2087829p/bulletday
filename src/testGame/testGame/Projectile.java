package testGame;

import java.awt.Rectangle;

public class Projectile {

	private int x, y, speedX, speedY;
	private boolean visible;

	private Rectangle r;

	public Projectile(int startX, int startY, int sX, int sY) {
		x = startX;
		y = startY;
		speedX = sX;
		speedY = sY;
		visible = true;
		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		x += speedX;
		r.setBounds(x, y, 10, 5);
		if (x > 800 || x < 0 || y > 480 || y < 0) {
			visible = false;
			r = null;
		}

	}

	private void checkCollision() {
		if (r.intersects(MainClass.h1.r)) {
			visible = false;
			//MainClass.score += 1;
			if (MainClass.h1.health > 0) {
				MainClass.h1.health -= 1;
			}
			if (MainClass.h1.health == 0) {
				MainClass.h1.setCenterX(-100);
				MainClass.score += 5;

			}
		}

		if (r.intersects(MainClass.h2.r)) {
			visible = false;
			//MainClass.score += 1;
			if (MainClass.h2.health > 0) {
				MainClass.h2.health -= 1;
			}
			if (MainClass.h2.health == 0) {
				MainClass.h2.setCenterX(-100);
				MainClass.score += 5;

			}

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
