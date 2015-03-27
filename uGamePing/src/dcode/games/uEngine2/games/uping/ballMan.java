package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.GFX.sprites.Sprite;

import java.awt.*;

/**
 * Created by dusakus on 24.03.15.
 */
public class ballMan extends Sprite {

    float direction = 0.7F;
    //								    _         _

    boolean mirrorX = false, mirrorY = false;

    int qdir = 1;     // 1 = |_  , 2 = |   , 3 =   | , 4 =  _|

    float speed = 2F;

    float X = 100F, Y = 100F;
    private int collisionLock = 10;


    public void tick() {

        int rec = (int) speed + 1;

        for (int i = 0; i < rec; i++) {


            float shiftXf = (speed / rec * direction), shiftYf = (speed / rec * (1 - direction)), tmpf;

            //use mirrors

            if (mirrorX) shiftXf = -shiftXf;
            if (mirrorY) shiftYf = -shiftYf;


            /*
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
*/
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

                if (col1 || col3) mirrorY = !mirrorY;
                if (col2 || col4) mirrorX = !mirrorX;


                int Bcol1 = LStData.bats.collideAt(newX + 5, newY);   // ->
                int Bcol2 = LStData.bats.collideAt(newX, newY + 5);   // V
                int Bcol3 = LStData.bats.collideAt(newX - 5, newY);   // <-
                int Bcol4 = LStData.bats.collideAt(newX, newY - 5);   // /\

                if (collisionLock <= 0) {
                    if (Bcol1 > 0) {
                        mirrorX = !mirrorX;
                        if (Bcol1 < 50) {
                            //mirrorY = !mirrorY;
                            direction = Bcol1 * 0.01F;
                            collisionLock = 10;

                        }
                        if (Bcol1 > 50) {
                            //mirrorY = !mirrorY;
                            direction = (Bcol1 - 100) * 0.01F;
                            collisionLock = 10;

                        }
                    }
                    if (Bcol2 > 0) {
                        mirrorY = !mirrorY;
                        if (Bcol2 < 50) {
                            //mirrorX = !mirrorX;
                            direction = Bcol2 * 0.01F;
                            collisionLock = 10;

                        }
                        if (Bcol2 > 50) {
                            //mirrorX = !mirrorX;
                            direction = (Bcol2 - 100) * 0.01F;
                            collisionLock = 10;

                        }
                    }
                    if (Bcol3 > 0) {
                        mirrorX = !mirrorX;
                        if (Bcol3 < 50) {
                            //mirrorY = !mirrorY;
                            direction = Bcol3 * 0.01F;
                            collisionLock = 10;

                        }
                        if (Bcol3 > 50) {
                            //mirrorY = !mirrorY;
                            direction = (Bcol3 - 100) * 0.01F;
                            collisionLock = 10;

                        }
                    }

                    if (Bcol4 > 0) {
                        mirrorY = !mirrorY;
                        if (Bcol4 < 50) {
                            //mirrorX = !mirrorX;
                            direction = Bcol4 * 0.01F;
                            collisionLock = 10;

                        }
                        if (Bcol4 > 50) {
                            //mirrorX = !mirrorX;
                            direction = (Bcol4 - 100) * 0.01F;
                            collisionLock = 10;

                        }
                    }
                } else {
                    collisionLock--;
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
        G.fillRect(x - 3, y - 5, 6, 10);
        G.fillRect(x - 5, y - 3, 10, 6);
    }
}
