package testGame;

import java.awt.Rectangle;

public class Enemy extends Entity {
	int health;
	private int speedX;
	private int centerX;
	private int centerY;
	private int numOfBullets;
	private int rateOfFire;
	private Background bg = MainClass.getBg1();
	private Character enemy = MainClass.getCharacter();
	private int movementSpeed;
	
	public Enemy (int centerX, int centerY, int speedX, int numOfBullets, int rateOfFire, 
			int movementSpeed){
	    super(centerX, centerY, new EnemySprite(centerX,centerY));       
		this.centerX = centerX;
		this.centerY = centerY;
		this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		
	}
	
	// Behavioral Methods
    @Override
	public void update() {
        super.update();
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		speedX = bg.getSpeedX() * 5;
        if(collides(enemy)){
            // DO SOMETHING
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
