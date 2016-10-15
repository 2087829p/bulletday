package testGame;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BasicSprite {
    private int x;
    private int y;
    public Rectangle shape;
    private BufferedImage sprite;
    public int height,width;
    
    public BasicSprite(int x, int y, String fn) {
        try {
            sprite = ImageIO.read(new File(fn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        height=sprite.getHeight();
        width=sprite.getWidth();
        shape = new Rectangle(x-60,y-50,sprite.getWidth(), sprite.getHeight());
        this.x = x;
        this.y = y;
    }

    public Rectangle getShape() {
        return this.shape;
    }
    
    public BufferedImage getSprite() {
    	return this.sprite;
    }

    public boolean collides(BasicSprite b) {
        return b.getShape().intersects(shape);
    }

    public void move(int x, int y,int w,int h) {
        shape.setRect( x, y,w,h);
    }
}
