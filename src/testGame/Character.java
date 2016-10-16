package testGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.AudioHandler;
import testGame.MainClass.GameState;

public class Character extends Entity{
	private final int DEFAULT_PLAYER_HEALTH=10;
	private int MOVESPEED = 5;	
	int health;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingForward = false;
	private boolean movingBack = false;
	private boolean readyToFire = true;
	private int noProjectiles = 1;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private final int FIRE_DELAY=17;
	private int delay;
	private static Background bg1 = MainClass.getBg1();
	

	public Character(int centerX, int centerY) {
        super(centerX, centerY, new CharacterSprite(centerX, centerY));
        this.health=DEFAULT_PLAYER_HEALTH;
        delay=0;
    }
	
	public void shoot() {
		System.out.println(delay);
		if (isReadyToFire()) {
            if(noProjectiles == 1) {
    			Projectile p = new PlayerProjectile(centerX, 
					centerY - sprite.getShape().height , speedX, -15);
			    projectiles.add(p);
            } else {
                Projectile[] p = new PlayerProjectile[noProjectiles];
                for(int i = 0; i < noProjectiles; i ++) {
                    int mid = noProjectiles/2;
                    projectiles.add(new PlayerProjectile(centerX, centerY - sprite.getShape().height,
                    speedX + ((mid - i) % mid )- mid, -15)); // Gives some spread 
                }
            }
		    AudioHandler.playSound("data/laser12.wav");
		    delay=FIRE_DELAY;
		}
	}
	
	public void incSpeed() {
		this.MOVESPEED = Math.min(10, MOVESPEED+2);
	}
	
	public void incProjNum() {
		this.noProjectiles = Math.min(16, noProjectiles + 1);
	}
	
	public void incHealth(){
		this.health = Math.min(10, health+1);
	}
	
	@Override
	public void update(){
		super.update();
		int p = 0;
		this.sprite.move(centerX-60, centerY-65, sprite.width, sprite.height);
		while(p < projectiles.size()) {
			int start_size = projectiles.size();
			projectiles.get(p).update();
			p += 1 - start_size + projectiles.size();
		}
		delay--;
	}
	
	public void setHealth(int health){
		if(health<0){
			MainClass.resetMultiplier();
		}
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
	
	public int getMovespeed(){
		return MOVESPEED;
	}

}
