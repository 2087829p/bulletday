package testGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import framework.Animation;
import framework.AudioHandler;

public class MainClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead
	}
	private boolean hasSubmited = false;
	public static ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	static GameState state = GameState.Running;
	private static final long serialVersionUID = 1L;
	private Image image, currentSprite, background, heliboy;
	private Graphics second;
	private URL base;
	private static Character spaceship;
	private Animation anim, hanim;
	private static Background bg1, bg2;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt, space_heart;
	public static List<Enemy> enemies=new ArrayList<Enemy>();
	public static List<Enemy> enemies_in_scene=new ArrayList<Enemy>();
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	public static int score = 0;
	public static ScoreBoard scoreBoard = new ScoreBoard();
	public static int multiplier = 1;
	public static ArrayList<EnemyProjectile> enemy_projectiles = new ArrayList<EnemyProjectile>();
	private Font font = new Font("TimesRoman", Font.BOLD, 30);
	public static List<PlayerProjectile> player_projectiles;
	@Override
	public void init() {
		
		splashScreen();

		setSize(480, 640);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame f = (Frame) this.getParent().getParent();
		f.setTitle("Bulletday");
		
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Image Setups

		heliboy = getImage(base, "data/heliboy.png");
		background = getImage(base, "data/background.png");
		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
		space_heart = getImage(base, "data/space_heart2.png");

		anim = new Animation();
		
		hanim = new Animation();   
		hanim.addFrame(heliboy, 100);

		currentSprite = anim.getImage();
		
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		spaceship = new Character(380, 400);
		BasicSprite characterUFO = spaceship.getSprite();
		Image characterUFOImage = characterUFO.getSprite();
		anim.addFrame(characterUFOImage, 100);
		currentSprite = anim.getImage();
		
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioHandler.playBackground("data/orbital_colossus.wav");
		Thread t = new Thread(this);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		t.start();
		// super.start();
	}

	public void splashScreen(){
		base = getDocumentBase();
		JWindow window = new JWindow();
			window.getContentPane().add(

				    new JLabel("", new ImageIcon(getImage(base, "data/billloading.gif")), SwingConstants.CENTER));
		
		window.setBounds(270,100, 800, 270);
		window.setVisible(true);
		try {
		    Thread.sleep(10000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		window.setVisible(false);
		window.dispose();
	
	}
	
	private void loadMap(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();
		for (int j = 0; j < height; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				
				if (i < line.length()) {
					char ch = line.charAt(i);
					System.out.println(line.charAt(i) + "is i ");
					Tile t = new Tile(i, j, java.lang.Character.getNumericValue(ch));
					tilearray.add(t);
				}
			}
		}

	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void run() {
		while(state == GameState.Running) {
			  if(enemies.size() == 0) {
					for(Enemy e : GenerateEnemy.group_enemy(3 + (score/100))){
						enemies.add(e);
						BasicSprite enemySprite = e.getSprite();
						Image enemyImage = enemySprite.getSprite();
						hanim.addFrame(enemyImage, 100);
					}
				}
				spaceship.update();
				currentSprite = anim.getImage();
				int projectilePointer = 0;
				while(projectilePointer < this.enemy_projectiles.size()) {
					int size = enemy_projectiles.size() + 0;
					enemy_projectiles.get(projectilePointer).update();
					projectilePointer += 1 - size + enemy_projectiles.size();
				}
				updateTiles();
				bg1.update();
				bg2.update();
				int i = 0;
				while( i < enemies.size()) {
					Enemy e = enemies.get(i);
					int size = enemies.size() + 0;
					e.update();
					i += 1 - size + enemies.size();
				}
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (spaceship.getCenterY() > 640) {
					spaceship.setCenterY(640);
				}
				if(spaceship.getCenterX() < 0){
					spaceship.setCenterX(0);
				}
				else if(spaceship.getCenterX() > 480){
					spaceship.setCenterX(480);
				}
		}
	}
	
	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	@Override
	public void paint(Graphics g) {
		if (state == GameState.Running) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		paintTiles(g);

		ArrayList<Projectile> projectiles = spaceship.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getCenterX(), p.getCenterY(), 5, 10);
		}

		ArrayList<EnemyProjectile> enemyProjectiles = enemy_projectiles;
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			Projectile p = (Projectile) enemyProjectiles.get(i);
			g.drawImage(p.getSprite().getSprite(), p.getCenterX(), p.getCenterY(), this);
		}
		
		g.drawImage(currentSprite, spaceship.getCenterX() - 61,
				spaceship.getCenterY() - 63, this);
		g.drawRect(spaceship.sprite.shape.x, spaceship.sprite.shape.y, spaceship.sprite.shape.width, spaceship.sprite.shape.height);
		for(Enemy e : enemies){
			g.drawImage(e.getSprite().getSprite(), e.getCenterX(), e.getCenterY(), this);
			g.drawRect(e.sprite.shape.x, e.sprite.shape.y, e.sprite.shape.width, e.sprite.shape.height);
		}
		Graphics2D g2 = (Graphics2D)g;
		g.setFont(font);
		g.setColor(Color.WHITE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawString(("Multiplier: " + multiplier + "     " + "Your score: " +  Integer.toString(score)), 20, 30);
		
		g2.drawString("Lives: ", 20, 70);
		for (int i = 0; i<spaceship.health;i++){			
			g.drawImage(space_heart, (100 + i*30), 50, this);
		}
		
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 200, 30);
			g.drawString("Score Board", 200, 60);
			if(!hasSubmited) {
				scoreBoard.addScore(score);
				scoreBoard.saveScores();
				hasSubmited = true;
			}
			int i = 0;
			for(Integer s : scoreBoard.getList()) {
				if(i >= scoreBoard.getList().size()) {
					break;
				}
				g.drawString(i + ": " + s.toString(), 200, 90 + 30 * i);
				i ++;
				if(i == 10) {
					break;
				}
			}
			g.drawString("Dead", WIDTH/2, 240);
		}else{
			scoreBoard.addScore(score);
			scoreBoard.saveScores();
			String top10 = scoreBoard.getN(10);
			g.drawString(top10, 320, 100);
			
		}
	}

	private void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			spaceship.moveForward();
			spaceship.setMovingForward(true);
			break;

		case KeyEvent.VK_DOWN:
			spaceship.moveBack();
			spaceship.setMovingBack(true);
			break;

		case KeyEvent.VK_LEFT:
			spaceship.moveLeft();
			spaceship.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			spaceship.moveRight();
			spaceship.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			spaceship.shoot();
			spaceship.setReadyToFire(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			spaceship.stopForward();
			break;

		case KeyEvent.VK_DOWN:
			spaceship.stopBack();
			break;

		case KeyEvent.VK_LEFT:
			spaceship.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			spaceship.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			spaceship.setReadyToFire(true);
			break;
		}
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Character getCharacter() {
		return spaceship;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			spaceship.shoot();
		}
	}

	public static void setGameState(GameState newState){
		state=newState;
		if(state==GameState.Dead){
			AudioHandler.stopBackgroundMusic();
		}
	}
	
	public static void addToScore(){
		score = score + (multiplier * 10);
		multiplier += 1;
	}
	
	public static void resetMultiplier(){
		multiplier = 1;
	}

}
