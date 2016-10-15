package testGame;

public class Entity {
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
        sprite.move(speedX,speedY);

    }

    public boolean checkCollision(Entity e) {
        return collides(e);
    }

    public boolean collides(Entity e) {
        return e.getSprite().collides(sprite);
    }

    public BasicSprite getSprite() {
        return this.sprite;
    }
}
