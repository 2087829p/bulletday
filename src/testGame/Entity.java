package testGame;

public class Entity {
    Protected BasicSprite sprite;
    Protected int x;
    Protected int y;
    public Entity(int x, int y, BasicSprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public boolean collides(Entity e){
        return e.getSprite().collides(sprite);
    }

    public BasicSprite getSprite() {
        return this.sprite;
    }
}
