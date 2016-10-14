package testGame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private Background bg = MainClass.getBg1();
	private Charecter robot = MainClass.getCharecter();
	private Rectangle r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rectangle();
		if (type == 5) {
			this.tileImage = MainClass.tiledirt;
		} else if (type == 8) {
			this.tileImage = MainClass.tilegrassTop;
		} else if (type == 4) {
			this.tileImage = MainClass.tilegrassLeft;
		} else if (type == 6) {
			this.tileImage = MainClass.tilegrassRight;
		} else if (type == 2) {
			this.tileImage = MainClass.tilegrassBot;
		} else {
			type = 0;
		}

	}

	public void update() {
		// if (type == 1) {
		// if (bg.getSpeedX() == 0) {
		// speedX = -1;
		// } else {
		// speedX = -2;
		// }
		// } else {
		// speedX = bg.getSpeedX() * 5;
		// }
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.setBounds(tileX, tileY, 40, 40);
		if (r.intersects(Charecter.yellowRed) && type != 0) {
			checkVerticalCollision(Charecter.rect, Charecter.rect2);
			checkSideCollision(Charecter.rect3, Charecter.rect4,
					Charecter.footleft, Charecter.footright);
		}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)) {

		}

		if (rbot.intersects(r) && type == 8) {
			if (type == 8) {
				robot.setJumped(false);
				robot.setSpeedY(0);
				robot.setCenterY(tileY - 63);
			}
		} else {
			System.out.println(type);
		}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright,
			Rectangle leftfoot, Rectangle rightfoot) {
		if (type != 5 && type != 2 && type != 0) {
			if (rleft.intersects(r)) {
				robot.setCenterX(tileX + 102);

				robot.setSpeedX(0);

			} else if (leftfoot.intersects(r)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}

			if (rright.intersects(r)) {
				robot.setCenterX(tileX - 62);

				robot.setSpeedX(0);
			}

			else if (rightfoot.intersects(r)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}
}
