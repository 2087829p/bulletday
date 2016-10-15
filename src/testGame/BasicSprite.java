package testGame;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class BasicSprite {
    private int x;
    private int y;
    private Rectangle shape;
    private BufferedImage sprite;

    public BasicSprite(int x, int y, String fn) {
        try {
            sprite = new ImageIO.read(new File(fn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        this.x = x;
        this.y = y;
    }

    public Rectangle getShape() {
        return this.shape;
    }

    public boolean collides(BasicSprite b) {
        return b.getShape().intersects(shape);
    }

    public void move(int x, int y) {
        shape.setLocation(this.x + x,this.y + y);
    }
}
