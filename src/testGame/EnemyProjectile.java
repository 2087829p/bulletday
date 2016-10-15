package testGame;

public class EnemyProjectile extends Projectile{
	
	public EnemyProjectile(int startX, int startY, int sX, int sY){
		super(startX, startY, sX, sY);
	}
	@Override
	public void checkCollision(){
		if(collides(MainClass.getCharacter())){
			MainClass.getCharacter().setHealth(-1);
			die();
		}
	}
	@Override
	public void update() {
		this.centerY+=8;
		this.sprite.shape.setBounds(this.centerX,this.centerY,10,5);
		if(this.centerY > GameMap.BOX_HIEGHT || this.centerY < 0) {
			die();
		}
		this.checkCollision();
	}
	
	@Override
	public void die() {
		super.die();
		MainClass.enemy_projectiles.remove(this);
	}
}
