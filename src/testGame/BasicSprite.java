package testGame;

package java.awt.Rectangle;
package java.io.File;
package java.io.IOException;
package java.awt.image.BufferedImage;

public class BasicSprite {
    private int x;
    private int y;
    private Rectangle shape;
    private BuffreredImage sprite;

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
}
