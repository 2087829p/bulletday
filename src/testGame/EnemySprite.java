package testGame;

import java.util.Random;

public class EnemySprite extends BasicSprite{
    public static final String[] FN = {"data/heliboy.png"};
    static Random randomSprite = new Random();
    
    public EnemySprite(int x, int y) {
        super(x,y, FN[randomSprite.nextInt(1)]);
    }
}
