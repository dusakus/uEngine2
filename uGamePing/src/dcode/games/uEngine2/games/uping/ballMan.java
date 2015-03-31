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

    float speed = 20F;

    float X = 100F, Y = 100F;
    private int collisionLock = 10;
    private int colldelay = 4;


    public void tick() {

        int rec = (int) speed + 1;

        for (int i = 0; i < rec; i++) {


            float shiftXf = (speed / rec * direction), shiftYf = (speed / rec * (1 - direction)), tmpf;

            //use mirrors

            if (mirrorX) shiftXf = -shiftXf;
            if (mirrorY) shiftYf = -shiftYf;

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

                boolean col1 = LStData.collisionMap.getRGB(newX, newY - 5) == -1;
                boolean col2 = LStData.collisionMap.getRGB(newX + 5, newY) == -1;
                boolean col3 = LStData.collisionMap.getRGB(newX, newY + 5) == -1;
                boolean col4 = LStData.collisionMap.getRGB(newX - 5, newY) == -1;

                if (colldelay <= 0) {
                    if (col1 || col3) {
                        mirrorY = !mirrorY;
                        colldelay = 30;
                    }
                    if (col2 || col4) {
                        mirrorX = !mirrorX;
                        colldelay = 30;
                    }
                } else {
                    colldelay--;
                }

                boolean Bcol1 = LStData.bats.collideAt(newX + 5, newY, Bat.FACING_LEFT);      // ->
                boolean Bcol2 = LStData.bats.collideAt(newX, newY + 5, Bat.FACING_UP);        // \/
                boolean Bcol3 = LStData.bats.collideAt(newX - 5, newY, Bat.FACING_RIGHT);     // <-
                boolean Bcol4 = LStData.bats.collideAt(newX, newY - 5, Bat.FACING_DOWN);      // /\

                if (collisionLock <= 0) {

                    if (Bcol1 || Bcol2 || Bcol3 || Bcol4) {
                        LStData.bats.collisionEffect(this);
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
        if (LStData.gameworld.moving) {
            G.fillRect(x + LStData.gameworld.shiftByX - 3, y + LStData.gameworld.shiftByY - 5, 6, 10);
            G.fillRect(x + LStData.gameworld.shiftByX - 5, y + LStData.gameworld.shiftByY - 3, 10, 6);
        }
    }
}
