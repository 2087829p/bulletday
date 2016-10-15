package testGame;

import java.awt.Rectangle;

public class Projectile extends Entity{
	private boolean visible;
	final int DEFAULT_DAMAGE=1;
	int damage;
	public Projectile(int startX, int startY, int sX, int sY) {
        super(startX, startY, new ProjectileSprite(startX,startY));
		speedX = sX;
		speedY = sY;
		visible = true;
		damage=DEFAULT_DAMAGE;
	}
    
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@Override
	public void update() {
		super.update();
		if(this.centerY > GameMap.BOX_HIEGHT || this.centerY < 0) {
			die();
		}
	}
	
	public void die() {
		// do something (should be overriden)
	}
}
