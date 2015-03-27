package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.tools.numbarTools;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dusakus on 24.03.15.
 * <p/>
 * NaNaNaNAnANanananananan BATMAN
 */
class BatMan implements ILayer {


    public static BatPack[][] areas;
    public static int coords[] = new int[256];

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

    public boolean collideAt(int x, int y, int dir) {
        return areas[LStData.currAreaX][LStData.currAreaY].checkCollision(x, y, dir);
    }

    public void collisionEffect(ballMan ballMan) {
        areas[LStData.currAreaX][LStData.currAreaY].setCollisionEffect(ballMan);
    }
}

class BatPack {
    ArrayList<Bat> horiz;
    ArrayList<Bat> verti;

    private int lastCollisionDir;
    private boolean lastCollisionXmirr;
    private boolean lastCollisionYmirr;

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

    public boolean checkCollision(int x, int y, int dir) {

        if (dir == Bat.FACING_RIGHT) {
            for (Bat b : verti) {
                if (b.facing == Bat.FACING_RIGHT && b.coordX + 12 == x && b.coordY < y && b.coordY + b.width > y) {
                    lastCollisionXmirr = false;
                    if (b.coordY + (b.width / 2) > y) {
                        lastCollisionYmirr = true;
                    } else {
                        lastCollisionYmirr = false;
                    }
                    lastCollisionDir = 100 - selectCEffect((y - b.coordY) - (b.width / 2), b.width / 2);
                    return true;
                }
            }
        } else if (dir == Bat.FACING_LEFT) {
            for (Bat b : verti) {
                if (b.facing == Bat.FACING_LEFT && b.coordX == x && b.coordY < y && b.coordY + b.width > y) {
                    lastCollisionXmirr = true;
                    if (b.coordY + (b.width / 2) > y) {
                        lastCollisionYmirr = false;
                    } else {
                        lastCollisionYmirr = true;
                    }
                    lastCollisionDir = 100 - selectCEffect((y - b.coordY) - (b.width / 2), b.width / 2);
                    return true;
                }
            }
        } else if (dir == Bat.FACING_DOWN) {
            for (Bat b : horiz) {
                if (b.facing == Bat.FACING_DOWN && b.coordY + 12 == y && b.coordX < x && b.coordX + b.width > x) {
                    lastCollisionYmirr = false;
                    if (b.coordX + (b.width / 2) > x) {
                        lastCollisionXmirr = true;
                    } else {
                        lastCollisionXmirr = false;
                    }
                    lastCollisionDir = selectCEffect((x - b.coordX) - (b.width / 2), b.width / 2);
                    return true;
                }
            }
        } else if (dir == Bat.FACING_UP) {
            for (Bat b : horiz) {
                if (b.facing == Bat.FACING_UP && b.coordY == y && b.coordX < x && b.coordX + b.width > x) {
                    lastCollisionYmirr = true;
                    if (b.coordX + (b.width / 2) > x) {
                        lastCollisionXmirr = true;
                    } else {
                        lastCollisionXmirr = false;
                    }
                    lastCollisionDir = selectCEffect((x - b.coordX) - (b.width / 2), b.width / 2);
                    return true;
                }
            }
        }

        return false;
    }

    int selectCEffect(int cposition, int olenght) {
        int tdir = numbarTools.mod(cposition);
        if (tdir < olenght / 5) return 10;
        if (cposition < 2 * olenght / 5) return 25;
        if (cposition < 3 * olenght / 5) return 50;
        if (cposition < 4 * olenght / 5) return 75;
        if (cposition < olenght) return 90;
        return 95;
    }

    public void setCollisionEffect(ballMan ball) {
        ball.mirrorX = lastCollisionXmirr;
        ball.mirrorY = lastCollisionYmirr;
        ball.direction = lastCollisionDir / 100F;
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