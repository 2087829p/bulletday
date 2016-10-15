package testGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.AudioHandler;
import testGame.MainClass.GameState;

public class Character extends Entity{
	private final int DEFAULT_PLAYER_HEALTH=3;
	final int MOVESPEED = 5;	
	int health;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingForward = false;
	private boolean movingBack = false;
	private boolean readyToFire = true;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private final int FIRE_DELAY=17;
	private int delay;
	private static Background bg1 = MainClass.getBg1();
	private static Background bg2 = MainClass.getBg2();
	

	public Character(int centerX, int centerY) {
        super(centerX, centerY, new CharacterSprite(centerX, centerY));
        this.health=DEFAULT_PLAYER_HEALTH;
        delay=0;
    }
	
	public void shoot() {
		System.out.println(delay);
		if (isReadyToFire()) {
			Projectile p = new PlayerProjectile(centerX + (sprite.getShape().width/2), 
					centerY + sprite.getShape().height , speedX, speedY - 10);
			projectiles.add(p);
			AudioHandler.playSound("data/laser12.wav");
			delay=FIRE_DELAY;
		}
	}
	
	@Override
	public void update(){
		super.update();
		int p = 0;
		while(p < projectiles.size()) {
			int start_size = projectiles.size();
			projectiles.get(p).update();
			p += 1 - start_size + projectiles.size();
		}
		delay--;
	}
	
	public void setHealth(int health){
		this.health+=health;
		if(this.health<=0){
			die();
		}
	}
	public int getHealth(){
		return health;
	}
	public void die(){
		MainClass.setGameState(GameState.Dead);
	}
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void removeProjectile(Projectile p) {
		projectiles.remove(p);
	}
	
	public void moveRight() {
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
	}
	
	public void moveForward() {
		speedY = -MOVESPEED;
	}
	
	public void moveBack() {
		speedY = MOVESPEED;
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}
	
	public void stopForward() {
		setMovingForward(false);
		stop();
	}
	
	public void stopBack() {
		setMovingBack(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}
		
		if(isMovingBack() == false && isMovingForward() == false) {
			speedY = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public boolean isReadyToFire() {
		return delay<=0;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public boolean isMovingRight() {
		return movingRight;
	}
	
	private boolean isMovingForward() {
		return movingForward;
	}
	
	private boolean isMovingBack() {
		return movingBack;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setMovingBack(boolean movingBack) {
		this.movingBack = movingBack;
	}

	public void setMovingForward(boolean movingForward) {
		this.movingForward = movingForward;		
	}

}
