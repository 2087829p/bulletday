package testGame;

import java.util.Random;

public class PowerUp extends Entity{
	private int centerX;
	private int centerY;
	private int speedY;
	private Character theCharacter = MainClass.getCharacter();
	Random r = new Random();
	private int typeOfPU;
	
	
	public PowerUp(int centerX, int centerY){
		super(centerX, centerY, new PowerUpSprite(centerX, centerY));
		this.setSpeedY(5);
		typeOfPU = r.nextInt(3);
	}
	
	
	public void update(){
		super.update();
		if(collides(theCharacter)){
			switch(typeOfPU){
			case 0: //theCharacter.incDamage();
					break;
			case 1: theCharacter.incProjNum();
					break;
			case 2: theCharacter.incSpeed();
					break;
			}
			die();
		}
		
	}
	
	public void die(){
		
	}
}