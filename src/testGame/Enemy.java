package testGame;

import java.awt.Rectangle;

public class Enemy extends Entity {
	private final int PLAYER_COLLISION_DAGAME=-1;
	private final int DEFAULT_HEALTH=1;
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
		this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		this.health=DEFAULT_HEALTH;
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
            this.die();
            enemy.setHealth(PLAYER_COLLISION_DAGAME);
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

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	public void setHealth(int amount){
		health+=amount;
		if(health<=0){
			die();
		}
	}
}
