package testGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import framework.Animation;

public class MainClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	private static final long serialVersionUID = 1L;
	private Image image, currentSprite, character, character2, character3,
			characterDown, characterJumped, background, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5;
	private Graphics second;
	private URL base;
	private static Character spaceship;
	public static Heli h1, h2;
	private Animation anim, hanim;
	private static Background bg1, bg2;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame f = (Frame) this.getParent().getParent();
		f.setTitle("Game Test");
		
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Image Setups
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");

		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");

		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");

		background = getImage(base, "data/background.png");
		// tiledirt = getImage(base, "data/tiledirt.png");
		// tileocean = getImage(base, "data/tileocean.png");
		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hanim = new Animation();   
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		spaceship = new Character();
		// for (int i = 0; i < 200; i++) {
		// for (int j = 0; j < 12; j++) {
		// if (j == 11) {
		// Tile t = new Tile(i, j, 2);
		// tilearray.add(t);
		// }
		// if (j == 10) {
		// Tile t = new Tile(i, j, 1);
		// tilearray.add(t);
		// }
		// }
		// }
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		h1 = new Heli(340, 360);
		h2 = new Heli(700, 360);

		Thread t = new Thread(this);
		t.start();
		// super.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
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
		for (int j = 0; j < 12; j++) {
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
		if (state == GameState.Running) {
			while (true) {
				spaceship.update();
				if (spaceship.isJumped()) {
					currentSprite = characterJumped;
				} else if (spaceship.isJumped() == false
						&& spaceship.isDucked() == false) {
					currentSprite = anim.getImage();
				}
				ArrayList projectiles = spaceship.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible()) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
				updateTiles();
				h1.update();
				h2.update();
				bg1.update();
				bg2.update();
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (spaceship.getCenterY() > 500) {
					state = GameState.Dead;
				}
			}
		}
		// TODO Auto-generated method stub

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
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}

		g.drawImage(currentSprite, spaceship.getCenterX() - 61,
				spaceship.getCenterY() - 63, this);
		g.drawImage(hanim.getImage(), h1.getCenterX() - 48,
				h1.getCenterY() - 48, this);
		g.drawImage(hanim.getImage(), h2.getCenterX() - 48,
				h2.getCenterY() - 48, this);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(score), 740, 30);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);


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
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (spaceship.isJumped() == false) {
				spaceship.setDucked(true);
				spaceship.setSpeedX(0);
			}
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
			spaceship.jump();
			break;
		case KeyEvent.VK_CONTROL:
			if (spaceship.isDucked() == false && spaceship.isJumped() == false) {
				spaceship.shoot();
				spaceship.setReadyToFire(false);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			spaceship.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			spaceship.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			spaceship.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_CONTROL:
			spaceship.setReadyToFire(true);
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

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

}
