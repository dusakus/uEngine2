package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.GFX.sprites.Sprite;

import java.awt.*;

/**
 * Created by dusakus on 24.03.15.
 */
public class ballMan extends Sprite {

	float direction = 0.7F;
	//								    _         _
	int qdir = 1;     // 1 = |_  , 2 = |   , 3 =   | , 4 =  _|

	float speed = 2F;

	float X = 100F, Y = 100F;


	public void tick() {

		int rec = (int) speed + 1;

		for (int i = 0; i < rec; i++) {


			float shiftXf = (speed / rec * direction), shiftYf = (speed / rec * (1 - direction)), tmpf;

			switch (qdir) {
				case 1:
					shiftYf = -shiftYf;
					break;
				case 2:
					tmpf = shiftXf;
					shiftXf = shiftYf;
					shiftYf = tmpf;
					break;
				case 3:
					tmpf = shiftYf;
					shiftYf = shiftXf;
					shiftXf = -tmpf;
					break;
				case 4:
					shiftXf = -shiftXf;
					shiftYf = -shiftYf;
			}

			float tX = X + shiftXf;
			float tY = Y + shiftYf;


			int newX = (int) tX;
			int newY = (int) tY;

			if (newX - 5 <= 0) {
				LStData.currentStatus = 302;
				LStData.currAreaX--;
				X = 394;
				return;
			} else if (newX + 5 >= 400) {
				LStData.currentStatus = 302;
				LStData.currAreaX++;
				X = 6;
				return;
			} else if (newY - 5 <= 0) {
				LStData.currentStatus = 302;
				LStData.currAreaY--;
				Y = 234;
				return;
			} else if (newY + 5 >= 240) {
				LStData.currentStatus = 302;
				LStData.currAreaY++;
				Y = 6;
				return;
			} else {

				/*if (qdir == 1 && LStData.collisionMap.getRGB(newX, newY - 5) == -1) {
					qdir = 2;
					direction = -direction;
				}
				if (qdir == 4 && LStData.collisionMap.getRGB(newX, newY - 5) == -1) {
					qdir = 3;
					direction = -direction;
				}
				if (qdir == 2 && LStData.collisionMap.getRGB(newX, newY + 5) == -1) {
					qdir = 1;
					direction = -direction;
				}
				if (qdir == 3 && LStData.collisionMap.getRGB(newX, newY + 5) == -1) {
					qdir = 4;
					direction = -direction;
				}
				if (qdir == 1 && LStData.collisionMap.getRGB(newX + 5, newY) == -1) {
					qdir = 4;
				}
				if (qdir == 2 && LStData.collisionMap.getRGB(newX + 5, newY) == -1) {
					qdir = 3;
				}
				if (qdir == 4 && LStData.collisionMap.getRGB(newX - 5, newY) == -1) {
					qdir = 1;
				}
				if (qdir == 3 && LStData.collisionMap.getRGB(newX - 5, newY) == -1) {
					qdir = 2;
				}
				*/
				boolean col1 = LStData.collisionMap.getRGB(newX, newY - 5) == -1;
				boolean col2 = LStData.collisionMap.getRGB(newX + 5, newY) == -1;
				boolean col3 = LStData.collisionMap.getRGB(newX, newY + 5) == -1;
				boolean col4 = LStData.collisionMap.getRGB(newX - 5, newY) == -1;

				switch (qdir) {
					case 1:
						if (col1) {
							direction = 2F - direction;
						}
						if (col2) {
							direction = -2F + direction;
						}
						break;
					case 2:
						if (col2) {
							qdir++;
							direction = (1F - direction);
						}
						if (col3) {
							direction -= 2 * direction;
						}
						break;
					case 3:
						if (col3) {
							direction = 2F - direction;
						}
						if (col4) {
							direction = -2F + direction;
						}
						break;
					case 4:
						if (col4) {
							direction = 2F - direction;
						}
						if (col1) {
							direction = -2F + direction;
						}
						break;
				}

				if (qdir == 1 && LStData.bats.collideAt(newX, newY - 5) >= 0) {
					qdir = 2;
					direction = LStData.bats.collideAt(newX, newY - 5) / 100F;
				} else if (qdir == 4 && LStData.bats.collideAt(newX, newY - 5) >= 0) {
					qdir = 3;
					direction = -LStData.bats.collideAt(newX, newY - 5) / 100F;
				} else if (qdir == 2 && LStData.bats.collideAt(newX, newY + 5) >= 0) {
					qdir = 1;
					direction = LStData.bats.collideAt(newX, newY + 5) / 100F;
				} else if (qdir == 3 && LStData.bats.collideAt(newX, newY + 5) >= 0) {
					qdir = 4;
					direction = -LStData.bats.collideAt(newX, newY + 5) / 100F;
				} else if (qdir == 1 && LStData.bats.collideAt(newX + 5, newY) >= 0) {
					qdir = 4;
					direction = (LStData.bats.collideAt(newX + 5, newY) / 100F) + 1;
				} else if (qdir == 2 && LStData.bats.collideAt(newX + 5, newY) >= 0) {
					qdir = 3;
					direction = (-LStData.bats.collideAt(newX + 5, newY) / 100F) + 1;
				} else if (qdir == 4 && LStData.bats.collideAt(newX - 5, newY) >= 0) {
					qdir = 1;
					direction = LStData.bats.collideAt(newX - 5, newY) / 100F;
				} else if (qdir == 3 && LStData.bats.collideAt(newX - 5, newY) >= 0) {
					qdir = 2;
					direction = -LStData.bats.collideAt(newX - 5, newY) / 100F;

				}
				while (direction >= 1) {
					direction -= 1;
					qdir++;
				}
				if (qdir > 4) qdir = 1;
				while (direction < 0) {
					direction += 1;
					qdir--;
				}
				if (qdir < 1) qdir = 4;
			}

			X = tX;
			Y = tY;

			this.setX(newX);
			this.setY(newY);
		}

	}

	@Override
	public Image getCustomTexture() {
		return null;
	}

	@Override
	public boolean doCustomRender() {
		return true;
	}

	@Override
	public void customRender(Graphics2D G) {
		G.setColor(Color.WHITE);
		G.fillRect(x - 5, y - 5, 10, 10);
	}
}
