package testGame;

import java.awt.Rectangle;

public class Projectile extends Entity{
	private boolean visible;

	public Projectile(int startX, int startY, int sX, int sY) {
        super(startX, startY, new ProjectileSprite(startX,startY));
		speedX = sX;
		speedY = sY;
		visible = true;
	}
    
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
