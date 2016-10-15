package testGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import testGame.MainClass.GameState;

public class Character extends Entity{
	private final int DEFAULT_PLAYER_HEALTH=3;
	private int movespeed = 5;	
	int health;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingForward = false;
	private boolean movingBack = false;
	private boolean readyToFire = true;
	private static int damage = 1;
	private static int projNum = 1;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	//private static Background bg1 = MainClass.getBg1();
	//private static Background bg2 = MainClass.getBg2();
	public Character(int centerX, int centerY) {
        super(centerX, centerY, new CharacterSprite(centerX, centerY));
        this.health=DEFAULT_PLAYER_HEALTH;
    }
	
	public void shoot() {
		if (readyToFire) {
			Projectile p = new PlayerProjectile(centerX + (sprite.getShape().width/2), 
					centerY + sprite.getShape().height , speedX, speedY + 10);
			projectiles.add(p);
		}
	}
	
	@Override
	public void update(){
		super.update();
		for(Projectile p : projectiles) {
			p.update();
		}
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
	public ArrayList getProjectiles() {
		return projectiles;
	}

	public void removeProjectile(Projectile p) {
		projectiles.remove(p);
	}
	
	public void moveRight() {
		speedX = movespeed;
	}

	public void moveLeft() {
		speedX = -movespeed;
	}
	
	public void moveForward() {
		speedY = -movespeed;
	}
	
	public void moveBack() {
		speedY = movespeed;
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
		return readyToFire;
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
	
	public int getDamage(){
		return damage;
	}
	
	public void incDamage(){
		damage++;
	}
	
	public int getProjNum(){
		return projNum;
	}
	
	public void incProjNum(){
		if(projNum < 5){
			projNum = projNum + 2;
		}
	}
	
	public int getSpeed(){
		return movespeed;
	}
	
	public void incSpeed(){
		if(movespeed < 10){
			movespeed++;
		}
	}

}
