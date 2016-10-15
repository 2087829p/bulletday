package testGame;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BasicSprite {
    private int x;
    private int y;
    public Rectangle shape;
    private Random rng;
    private BufferedImage sprite;
    private ArrayList<BufferedImage> enemyProjectileImages = new ArrayList<BufferedImage>();
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

    public BasicSprite(int x2, int y2, String[] enemyProjectileSprites) {
    	this(x2, y2, enemyProjectileSprites[new Random().nextInt(enemyProjectileSprites.length)]);
    	rng = new Random();
    	for(String dataRef : enemyProjectileSprites){
			try {
				System.out.println(dataRef);
	            BufferedImage tempSprite = ImageIO.read(new File(dataRef));
	            enemyProjectileImages.add(tempSprite);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
        height=enemyProjectileImages.get(0).getHeight();
        width=enemyProjectileImages.get(0).getHeight();
        shape = new Rectangle(x-60,y-50,height, width);
        this.x = x;
        this.y = y;
	}

	public Rectangle getShape() {
        return this.shape;
    }
    
    public BufferedImage getSprite() {
    	return this.sprite;
    }
    
    public BufferedImage getEnemyProjectileImage() {
    	rng = new Random();
    	System.out.println(this.enemyProjectileImages.size());
    	return this.enemyProjectileImages.get(rng.nextInt(2));
    }

    public boolean collides(BasicSprite b) {
        return b.getShape().intersects(shape);
    }

    public void move(int x, int y,int w,int h) {
        shape.setRect( x, y,w,h);
    }
}
