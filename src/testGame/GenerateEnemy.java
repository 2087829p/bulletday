package testGame;

import java.util.Random;
import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class GenerateEnemy {
    public static Random rng=new Random();
    
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
        int count = rng.nextInt(Math.min(difficulty, 6))+1;
        int power = difficulty/count;
        int bullets = generate_num(Math.min(power, 5));
        power=Math.max(1, power-bullets);
        int speed = generate_num(Math.min(power, 8));
        power=Math.max(1, power-speed);
        int bullet_rate = generate_num(power);
        power=Math.max(1, power-bullet_rate);
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
        double x_spacing = GameMap.BOX_WIDTH;
        x_spacing /= count + 1;
        for(int i = 0; i < count; i ++) {
            double x = 0.5 * x_spacing;
            x += x_spacing * i; 
            x=Math.min(GameMap.BOX_WIDTH-30, x);
            ret[i] = new Enemy((int)x, 100, speed, bullets, bullet_rate, speed,gen_path(new Point((int) x,100)));
        }
        return ret;
    }
    public static List<Point> gen_path(Point p){
    	List<Point> path=new ArrayList<Point>();
    	path.add(new Point(MainClass.getCharacter().centerX,MainClass.getCharacter().centerY));
    	int num_points=rng.nextInt(5)+1;
    	for(int i=0;i<num_points;i++){
    		path.add(new Point(Math.max(rng.nextInt(GameMap.BOX_WIDTH-40),20),Math.max(rng.nextInt(GameMap.BOX_HIEGHT-60),30)));
    	}
    	path.add(p);
    	return path;
    }
    // Generates a single enemy
    public Enemy single_enemy(int difficulty) {
        int power = difficulty;
        int bullets = generate_num(power);
        power -= bullets;
        if(power < 0)
            power = 1;
        int bullet_rate = generate_num(power);
        power -= bullets;
        if(power < 0)
            power = 1;
        int speed = power;
        double x = rng.nextDouble() * (GameMap.BOX_WIDTH / 2) + (GameMap.BOX_WIDTH / 4);
        Double X = new Double(x);
        return new Enemy(X.intValue(), 0, speed, bullets, bullet_rate, speed,gen_path(new Point(X.intValue(),100)));
    }
}
