package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dusakus on 24.03.15.
 */
class BatMan implements ILayer {


	public static BatPack[][] areas;

	public BatMan() {
		areas = new BatPack[33][33];
		for (int i = 0; i < 33; i++) {
			for (int j = 0; j < 33; j++) {
				areas[i][j] = new BatPack();
			}
		}
	}

	@Override
	public void draw(Graphics2D G2D) {
		areas[LStData.currAreaX][LStData.currAreaY].draw(G2D);
	}

	@Override
	public boolean removeMe() {
		return false;
	}

	@Override
	public boolean renderMe() {
		return (LStData.currentMode == LStData.MODE_GAME_PLAY) && (LStData.currentStatus == 301);
	}

	public void tick() {
		areas[LStData.currAreaX][LStData.currAreaY].tick();
	}

	public int collideAt(int x, int y) {
		return areas[LStData.currAreaX][LStData.currAreaY].checkCollision(x, y);
	}

}

class BatPack {
	ArrayList<Bat> horiz;
	ArrayList<Bat> verti;

	public BatPack() {
		horiz = new ArrayList<>();
		verti = new ArrayList<>();
	}

	public void addBat(Bat bat) {
		if (bat.facing < 10) {
			verti.add(bat);
		} else {
			horiz.add(bat);
		}
	}

	public void draw(Graphics2D G2D) {
		for (Bat b : horiz) {
			G2D.setColor(b.color);
			G2D.fillRect(b.coordX, b.coordY, b.width, 12);
		}

		for (Bat b : verti) {
			G2D.setColor(b.color);
			G2D.fillRect(b.coordX, b.coordY, 12, b.width);
		}
	}

	public void tick() {
		if (InHandler.inst.isKeyUp()) {
			for (Bat b : verti) {
				if (LStData.collisionMap.getRGB(b.coordX + 4, b.coordY - 1) != -1) {
					b.coordY--;
				}
			}
		}
		if (InHandler.inst.isKeyDown()) {
			for (Bat b : verti) {
				if (LStData.collisionMap.getRGB(b.coordX + 4, b.coordY + b.width) != -1) {
					b.coordY++;
				}
			}
		}
		if (InHandler.inst.isKeyLeft()) {
			for (Bat b : horiz) {
				if (LStData.collisionMap.getRGB(b.coordX - 1, b.coordY + 4) != -1) {
					b.coordX--;
				}
			}
		}
		if (InHandler.inst.isKeyRight()) {
			for (Bat b : horiz) {
				if (LStData.collisionMap.getRGB(b.coordX + b.width, b.coordY + 4) != -1) {
					b.coordX++;
				}
			}
		}
	}

	public int checkCollision(int x, int y) {

		for (Bat b : verti) {
			if (b.facing == 2 && b.coordX - 1 == x && b.coordY <= y && (b.coordY + b.width) >= y) {
				y -= b.coordY;
				StData.LOG.println("ypos: " + y + " , effect:  " + (((b.coordY - y) / (b.width / 2)) * 100));

				return selectCEffect(y, b.width);
			}
			if (b.facing == 1 && b.coordX + 13 == x && b.coordY <= y && (b.coordY + b.width) >= y) {
				y -= b.coordY;
				StData.LOG.println("ypos: " + y + " , effect:  " + (((b.coordY - y)) / (b.width / 2) * 100));

				return selectCEffect(y, b.width);
			}
		}
		for (Bat b : horiz) {
			if (b.facing == 11 && b.coordY + 13 == y && b.coordX >= x && (b.coordX + b.width) <= x) {
				x -= b.coordX;
				StData.LOG.println("ypos: " + x + " , effect:  " + (((b.coordX - x)) / (b.width / 2) * 100));
				return selectCEffect(y, b.width);
			}
			if (b.facing == 12 && b.coordY - 1 == y && b.coordX >= x && (b.coordX + b.width) <= x) {
				x -= b.coordX;
				StData.LOG.println("ypos: " + x + " , effect:  " + (((b.coordX - x)) / (b.width / 2) * 100));
				return selectCEffect(y, b.width);
			}
		}


		return -1;
	}

	int selectCEffect(int cposition, int olenght) {
		if (cposition < olenght / 5) return 25;
		if (cposition < 2 * olenght / 5) return 60;
		if (cposition < 3 * olenght / 5) return 100;
		if (cposition < 4 * olenght / 5) return 140;
		if (cposition < olenght) return 175;
		return 100;
	}
}

class Bat {

	public static final int FACING_RIGHT = 1;
	public static final int FACING_LEFT = 2;
	public static final int FACING_UP = 11;
	public static final int FACING_DOWN = 12;
	public Color color = Color.LIGHT_GRAY;
	int width = 64;
	int facing = -1;
	int coordX = -100;
	int coordY = -100;

	public Bat(int w, int fac, int cX, int cY) {
		width = w;
		facing = fac;
		coordX = cX;
		coordY = cY;
	}
}