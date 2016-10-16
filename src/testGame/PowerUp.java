package testGame;

import java.util.ArrayList;
import java.util.Random;

import framework.AudioHandler;

public class PowerUp extends Entity{
	private Character theCharacter = MainClass.getCharacter();
	Random r = new Random();
	private ArrayList<String> powerupsActive;
	private int typeOfPU;
	
	
	public PowerUp(int centerX, int centerY){
		super(centerX, centerY, new PowerUpSprite(centerX, centerY));
		this.setSpeedY(theCharacter.getMovespeed()/2);
		typeOfPU = r.nextInt(3);
	}

	
	public void update(){
		super.update();
		sprite.move(centerX, centerY, sprite.width, sprite.height);
		if(this.centerY > GameMap.BOX_HIEGHT-30){
			die();
		}
		if(sprite.collides(MainClass.getCharacter().sprite)){
			switch(typeOfPU){
			case 0: theCharacter.incHealth();
					MainClass.addToActivePowerups("Increased health by 1!");
					break;
			case 1: theCharacter.incProjNum();
					MainClass.addToActivePowerups("Increased Number of Projectiles!");
					break;
			case 2: theCharacter.incSpeed();
					MainClass.addToActivePowerups("Increased speed!");
					break;
			}
			
			AudioHandler.playSound("data/flagreturn.wav");	
			die();
		}
		
	}
	
	public ArrayList<String> getActivePowers(){
		return powerupsActive;
	}
	public void clearPowerUps(){
		powerupsActive.clear();
	}
	public void die(){
		MainClass.powerups.remove(this);
	}
}