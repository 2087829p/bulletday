package testGame;

import java.util.List;

public class PlayerProjectile extends Projectile{
	
	public PlayerProjectile(int startX, int startY, int sX, int sY){
		super(startX, startY, sX, sY);
	}
	@Override
	public void checkCollision(){
		List<Enemy> enemies=MainClass.enemies_in_scene;
		for(int i=0;i<enemies.size();i++){
			if(this.collides(MainClass.enemies_in_scene.get(i))){
				MainClass.enemies_in_scene.get(i).setHealth(-damage);
				die();
			}
		}
	}
	
	@Override
	public void die(){
		super.die();
		MainClass.getCharacter().removeProjectile(this);
	}

}
