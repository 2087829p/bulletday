package testGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.math.*;

import framework.AudioHandler;
import java.util.List;
import java.awt.geom.Point2D;

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
	List<Point> path;
	int at=0;
	public Enemy (int centerX, int centerY, int speedX, int numOfBullets, int rateOfFire, 
			int movementSpeed,List<Point> path){
	    super(centerX, centerY, new EnemySprite(centerX,centerY));       
	    sprite.move(centerX, centerY, sprite.width, sprite.height);
	    this.numOfBullets = numOfBullets;
		this.rateOfFire = rateOfFire;
		this.movementSpeed = movementSpeed;
		this.updateCount = 0;
		this.path=path;
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
		//follow();		
		Point currloc=new Point(this.centerX,this.centerY);
		Point destination=path.get(at);
		if(currloc.x==destination.x && currloc.y==destination.y){
			at++;			
			at=(at>=path.size())?at%path.size():at;			
		}		
		//centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		//speedX = bg.getSpeedX() * 5;
		moveTo(path.get(at),speedX);
		sprite.move(centerX, centerY, sprite.width, sprite.height);
		if(centerY + this.sprite.height > 640){
        	die();
        }
		if(centerX < 0){
			centerX = 0;
		}
		else if(centerX+this.sprite.width > 480){
			centerX = 480 - this.sprite.width;
		}
		
		if(updateCount % rateOfFire == 0) {
			shoot();
			updateCount = 100 - rateOfFire;
		}
		updateCount --;
		delay--;
    }
    private void moveTo(Point p,int speed){
    	int xchange=(int) (speed*Math.signum(p.x-centerX));
    	int ychange=(int) (speed*Math.signum(p.y-centerY));
    	centerX+=xchange;
    	centerY+=ychange;
    }
    
    public void shoot() {
    	if(canFire()){
    		for(double a : angle) {    		
    		Double xSpeed = new Double(Math.cos(a) * 2 * this.movementSpeed);
    		EnemyProjectile bullet = new EnemyProjectile(this.centerX+(sprite.width/2), 
    				this.centerY+(sprite.height-5), this.speedX + xSpeed.intValue(), 2);
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
		//AudioHandler.playSound("data/explodemini.wav");
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
