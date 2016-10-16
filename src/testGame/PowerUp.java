package testGame;

import java.util.Random;

public class PowerUp extends Entity{
	private Character theCharacter = MainClass.getCharacter();
	Random r = new Random();
	private int typeOfPU;
	
	
	public PowerUp(int centerX, int centerY){
		super(centerX, centerY, new PowerUpSprite(centerX, centerY));
		this.setSpeedY(theCharacter.getMovespeed()/2);
		typeOfPU = r.nextInt(3);
	}

	
	public void update(){
		super.update();
		sprite.move(centerX, centerY, sprite.width, sprite.height);
		if(this.centerY > 640){
			die();
		}
		if(sprite.collides(MainClass.getCharacter().sprite)){
			System.out.println("Collided");
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
		MainClass.powerups.remove(this);
	}
}