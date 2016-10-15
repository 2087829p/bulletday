package testGame;

import java.awt.Rectangle;
import java.math.*;

public class Enemy extends Entity {
	int health;
	private int speedX;
	private int centerX;
	private int centerY;
	private int numOfBullets;
	private int rateOfFire;
	private int updateCount;
	private Background bg = MainClass.getBg1();
	private Character enemy = MainClass.getCharacter();
	private int movementSpeed;
	private double[] angle;
	
	public Enemy (int centerX, int centerY, int speedX, int numOfBullets, int rateOfFire, 
			int movementSpeed){
	    super(centerX, centerY, new EnemySprite(centerX,centerY));       
		this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		this.updateCount = 0;
    	this.angle = new double[this.numOfBullets];
    	switch(numOfBullets){
    	case 1:
    		angle[0] = 0;
    		break;
    	case 2:
    		angle[0] =  30;
    		angle[1] = -30;
    		break;
    	default:
			for(int i = 0; i < numOfBullets; i ++) {
				angle[i] = i * 360.0/numOfBullets; 
			}
    	}
	}
	
	// Behavioral Methods
    @Override
	public void update() {
        super.update();
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		speedX = bg.getSpeedX() * 5;
		if(updateCount % rateOfFire == 0) {
			shoot();
			updateCount = rateOfFire;
		}
		updateCount --;
        if(collides(enemy)){
            // DO SOMETHING
        }
	}

    public void shoot() {
    	for(double a : angle) {
    		Double xSpeed = new Double(Math.cos(a) * 2 * this.movementSpeed);
    		Double ySpeed = new Double(Math.sin(a) * 2 * this.movementSpeed);
    		EnemyProjectile bullet = new EnemyProjectile(this.centerX, this.centerY
    				, this.speedX + xSpeed.intValue(), 
    				  this.speedY + ySpeed.intValue());
    		MainClass.projectiles.add(bullet);
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

}
