package testGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Character {
	final int MOVESPEED = 5;
	final int GROUND = 382;

	private int centerX = 100;
	private int centerY = 377;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingForward = false;
	private boolean movingBack = false;
	private boolean readyToFire = true;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private static Background bg1 = MainClass.getBg1();
	private static Background bg2 = MainClass.getBg2();

	private int speedX = 0;
	private int speedY = 0;
	
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	public static Rectangle footleft = new Rectangle(0,0,0,0);
	public static Rectangle footright = new Rectangle(0,0,0,0);
	
	
	public void update() {
	
		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-speedX / 5);
			bg2.setSpeedX(-speedX / 5);
		}

		// Updates Y Position
		centerY += speedY;	

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		rect.setRect(centerX - 34, centerY - 63	, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);
		rect3.setRect(rect.getX() - 26, rect.getY()+32, 26, 20);
		rect4.setRect(rect.getX() + 68, rect.getY()+32, 26, 20);
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
		footleft.setRect(centerX - 50, centerY + 20, 50, 15);
		footright.setRect(centerX, centerY + 20, 50, 15);
	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new PlayerProjectile(centerX + 50, centerY - 25, 0, -5);
			projectiles.add(p);
		}
	}

	public ArrayList getProjectiles() {
		return projectiles;
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
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
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
