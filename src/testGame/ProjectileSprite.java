package testGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ProjectileSprite extends BasicSprite {
    private static final String[] enemyProjectileSprites = {"data/ball1.PNG", "data/ball2.PNG", "data/ball3.PNG",
    		"data/ball4.PNG", "data/ball5.PNG"};    
    public ProjectileSprite(int x, int y) {
        super(x,y, enemyProjectileSprites);
    }
    
}
