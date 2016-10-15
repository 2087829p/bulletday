package testGame;

public class Entity {
	final int GROUND = 630;
    protected BasicSprite sprite;
    protected int centerX;
    protected int centerY;
    protected int speedX;
    protected int speedY;
    
    public Entity(int x, int y, BasicSprite sprite) {
        this.centerX = x;
        this.centerY = y;
        this.sprite = sprite;
        this.speedX = 0;
        this.speedY = 0;
    }
    
    public void update() {
        centerX += speedX;
        centerY += speedY;
        centerY=java.lang.Math.min(centerY, GROUND);
        sprite.move(speedX,speedY);

    }

    public void checkCollision() {
        //return collides(e);
    }

    public boolean collides(Entity e) {
        return e.getSprite().collides(sprite);
    }

    public BasicSprite getSprite() {
        return this.sprite;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int x){
        this.centerX = x;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int y) {
        this.centerY = y;
    }
    
    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int x) {
        this.speedX = x;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int y) {
        this.speedY = y;
    }
}
