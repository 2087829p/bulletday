package testGame;

import java.util.Random;
import java.Math;

public class GenerateEnemy {
    public static Random rng;
    
    public GenerateEnemy() {
        if(rng == null) {
            rng = new Random();
        }
    }

    public GenerateEnemy(long seed) {
        if (rng == null)
            rng = new Random(seed);
        else
            rng.setSeed(seed);
    }

    private static int generate_num(int n) {
        return rng.nextInt(n) + 1;
    }
    
    // Generate a group of enemies
    public static Enemy[] group_enemy(int difficulty) {
        if(rng == null) {}
        int count = rng.nextInt(Math.min(difficulty, 6));
        int power = difficulty/count;
        int bullets = generate_num(power);
        power -= bullets;
        if(power < 0)
            power = 1;
        int speed = generate_num(power);
        power -= speed;
        if(power < 0)
            power = 1;
        int bullet_rate = generate_num(power);
        power -= bullet_rate;
        if(power < 0)
            power = 1;
        int path = 0;
        if (power > 1){
            speed += power / 3;
            power -= power/3;
            bullet_rate += power / 2;
            power -= power / 2;
            bullets += power;
            path = generate_num(4) - 1; // TODO Change to amount of paths
        }
        Enemy[] ret = new Enemy[count];
        
        for(int i = 0; i < count; i ++) {
            
            Enemy[i] = new Enemy(); // TODO set up constructor
        }
    }

    // Generates a single enemy
    public Enemy single_enemy(int difficulty) {
        int power = difficulty;
        int bullets = generate_num(power);
        power -= bullets;
        if(power < 0)
            power = 1;
        int bullet_speed = generate_num(power);
        power -= bullets;
        if(power < 0)
            power = 1;
        int speed = power;
        return new Enemy(); // TODO set up constructor
    }
}
