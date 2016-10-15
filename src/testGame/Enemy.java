package testGame;

import java.awt.Rectangle;

public class Enemy {
	int health;
	private int speedX;
	private int centerX;
	private int centerY;
	private int numOfBullets;
	private int rateOfFire;
	private Background bg = MainClass.getBg1();
	private Character enemy = MainClass.getCharacter();
	public Rectangle r = new Rectangle(0, 0, 0, 0);
	private int movementSpeed;
	
	public void Enemy (int centerX, int centerY, int speedX, int numOfBullets, int rateOfFire, 
			int movementSpeed){
	 
		this.centerX = centerX;
		this.centerY = centerY;
		this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		
	}
	
	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		speedX = bg.getSpeedX() * 5;
		r.setBounds(centerX - 25, centerY - 25, 50, 60);

		if (r.intersects(Character.yellowRed)) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (r.intersects(Character.rect) || r.intersects(Character.rect2)
				|| r.intersects(Character.rect3)
				|| r.intersects(Character.rect4)) {
			System.out.println("collision");

		}
	}

	public void follow() {

		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		}

		else if (Math.abs(enemy.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (enemy.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

}
