package testGame;

import java.util.Random;

public class EnemySprite extends BasicSprite{
    public static final String[] FN = {"data/enemy.PNG", "data/enemy2.PNG", "data/enemy3.PNG", 
    		"data/enemy4.PNG", "data/enemy5.PNG", "data/enemy6.PNG"};
    static Random randomSprite = new Random();
    
    public EnemySprite(int x, int y) {
        super(x,y, FN[randomSprite.nextInt(FN.length)]);
    }
}
