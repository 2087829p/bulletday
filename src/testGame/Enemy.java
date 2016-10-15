package testGame;

import java.awt.Rectangle;
import java.math.*;

import framework.AudioHandler;

public class Enemy extends Entity {
	private final int PLAYER_COLLISION_DAGAME=-1;
	private final int DEFAULT_HEALTH=1;
	private final int DEFAULT_DELAY=30;
	int health;
	private int speedX;
	//private int centerX;
	//private int centerY;
	private int numOfBullets;
	private int rateOfFire;
	private int updateCount;
	private Background bg = MainClass.getBg1();
	private Character player = MainClass.getCharacter();
	private int movementSpeed;
	private double[] angle;
	int delay=DEFAULT_DELAY;
	
	public Enemy (int centerX, int centerY, int speedX, int numOfBullets, int rateOfFire, 
			int movementSpeed){
	    super(centerX, centerY, new EnemySprite(centerX,centerY));       
	    sprite.move(centerX, centerY, sprite.width, sprite.height);
	    this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		this.updateCount = 0;
    	this.angle = new double[this.numOfBullets];
    	switch(numOfBullets){
    	case 1:
    		angle[0] = 3 * Math.PI / 2;
    		break;
    	case 2:
    		angle[0] =  (Math.PI/6) + 3 * Math.PI / 2;
    		angle[1] = -(Math.PI/6) + 3 * Math.PI / 2;
    		break;
    	default:
			for(int i = 0; i < numOfBullets; i ++) {
				angle[i] = (i * 2 * Math.PI/numOfBullets) + 3 * Math.PI / 2; 
			}
    	}
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
		sprite.move(centerX, centerY, sprite.width, sprite.height);
		if(updateCount % rateOfFire == 0) {
			shoot();
			updateCount = 100 - rateOfFire;
		}
		updateCount --;
		delay--;
        if(collides(player)){
        	System.out.print("Collision");
            this.die();
            player.setHealth(PLAYER_COLLISION_DAGAME);
        } else {
        	int p = 0;
        	while(p < player.getProjectiles().size()) {
        		if(collides(player.getProjectiles().get(p))){
        			this.die();
        			player.getProjectiles().get(p).die();
        			System.out.print("Collision");
        			break;
        		}
    			else {
        			p ++;
        		}
        	}
        }
        
	}

    public void shoot() {
    	if(canFire()){
    		for(double a : angle) {    		
    		Double xSpeed = new Double(Math.cos(a) * 2 * this.movementSpeed);
    		Double ySpeed = new Double(Math.sin(a) * 2 * this.movementSpeed);
    		EnemyProjectile bullet = new EnemyProjectile(this.centerX, this.centerY
    				, this.speedX + xSpeed.intValue(), 
    				  this.speedY + ySpeed.intValue());
    		MainClass.enemy_projectiles.add(bullet);
    		delay=DEFAULT_DELAY;
    		}
    	}    	
    }
    
	public void follow() {

		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		}

		else if (Math.abs(player.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (player.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}
	public boolean canFire(){
		return delay<=0;
	}
	public void die() {
		MainClass.enemies.remove(this);
		AudioHandler.playSound("data/explodemini.wav");
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
			MainClass.addToScore();
			die();
		}
	}
}
