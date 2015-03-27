package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 24.03.15.
 */
public class LAYERs_GameScene {

    public Area[][] areas = new Area[33][33];
    public int shiftByX = 0, shiftByY = 0;
    public boolean moving = false;
    int lastAreaX = 3, lastAreaY = 2;
    private int mStep = 0;
    private LayerAnimation tmpanim;


    public Area createArea(String str, int i, int i1, LAYERs_GameScene worldMan) {
        return new Area(str, i, i1, worldMan);
    }

    public void update() {
        if (!moving) {
            areas[lastAreaX][lastAreaY].disable();

            int tes = 1;
            if (lastAreaX > LStData.currAreaX) tes = Bat.FACING_LEFT;
            if (lastAreaX < LStData.currAreaX) tes = Bat.FACING_RIGHT;
            if (lastAreaY > LStData.currAreaY) tes = Bat.FACING_UP;
            if (lastAreaY < LStData.currAreaY) tes = Bat.FACING_DOWN;

            moving = true;
            tmpanim = new LayerAnimation("lv1", lastAreaX, lastAreaY, this, tes);
            StData.currentGC.currentSC.layers_Overlay.add(tmpanim);

            StData.LOG.println("Moving to Area [" + LStData.currAreaX + "," + LStData.currAreaY + "]");
        } else {
            shiftByX = (mStep * 8 * (lastAreaX - LStData.currAreaX));
            shiftByY = ((int) (mStep * 4.8F * (lastAreaY - LStData.currAreaY)));
        }
        mStep++;
        if (mStep == 50) {
            StData.LOG.println("Moving done [" + LStData.currAreaX + "," + LStData.currAreaY + "]");
            lastAreaX = LStData.currAreaX;
            lastAreaY = LStData.currAreaY;
            areas[lastAreaX][lastAreaY].enable();
            LStData.currentStatus = 301;
            shiftByX = 0;
            shiftByY = 0;
            mStep = 0;
            StData.currentGC.currentSC.layers_Overlay.remove(tmpanim);
            moving = false;
        }
    }

    public BufferedImage getDataImage() {
        return (BufferedImage) StData.resources.grf.getPartTexture(areas[3][2].areaID + "D", (LStData.currAreaX - 1) * 400, (LStData.currAreaY - 1) * 240, 400, 240);
    }


    public class Area extends AreaDataContainer {

        public boolean old;
        public int moveDir = -1;   // 1:  /\   2:  >  3:  \/   4:  <
        private LayerBottom lb;
        private LayerTop lt;
        private LayerData ls;

        public Area(String aID, int acX, int acY, LAYERs_GameScene gsc) {
            super(aID, acX, acY, gsc);
            lb = new LayerBottom(aID, acX, acY, gsc);
            lt = new LayerTop(aID, acX, acY, gsc);
            ls = new LayerData(aID, acX, acY, gsc);
        }

        public void disable() {
            StData.currentGC.currentSC.layers_Background.remove(lb);
            StData.currentGC.currentSC.layers_Foreground.remove(lt);
            StData.currentGC.currentSC.layers_Overlay.remove(ls);
        }

        public void enable() {
            old = false;
            StData.currentGC.currentSC.layers_Background.add(lb);
            StData.currentGC.currentSC.layers_Foreground.add(lt);
            StData.currentGC.currentSC.layers_Overlay.add(ls);
        }

        void updateDir() {
            if (LStData.currAreaX > areaCoordX) moveDir = 4;
            if (LStData.currAreaX < areaCoordX) moveDir = 2;
            if (LStData.currAreaY > areaCoordY) moveDir = 1;
            if (LStData.currAreaY < areaCoordY) moveDir = 3;
        }
    }

    private class AreaDataContainer {

        String areaID = "ARmain";
        int areaCoordX = 0;
        int areaCoordY = 0;
        LAYERs_GameScene gameScene;

        public AreaDataContainer(String aID, int acX, int acY, LAYERs_GameScene gsc) {
            areaID = aID;
            areaCoordX = acX;
            areaCoordY = acY;
            gameScene = gsc;
        }

        public Image getTexture(String id) {
            return StData.resources.grf.getPartTexture(id, (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 400, 240);
        }

    }

    private class LayerBottom extends AreaDataContainer implements ILayer {


        public LayerBottom(String aID, int acX, int acY, LAYERs_GameScene gsc) {
            super(aID, acX, acY, gsc);
        }

        @Override
        public void draw(Graphics2D G2D) {
            G2D.drawImage(getTexture(areaID + "B"), 0, 0, null);
        }

        @Override
        public boolean removeMe() {
            return false;
        }

        @Override
        public boolean renderMe() {
            return LStData.currentMode == LStData.MODE_GAME_PLAY;
        }
    }

    private class LayerTop extends AreaDataContainer implements ILayer {

        public LayerTop(String aID, int acX, int acY, LAYERs_GameScene gsc) {
            super(aID, acX, acY, gsc);
        }

        @Override
        public void draw(Graphics2D G2D) {
            G2D.drawImage(getTexture(areaID + "T"), 0, 0, null);

        }

        @Override
        public boolean removeMe() {
            return false;
        }

        @Override
        public boolean renderMe() {
            return LStData.currentMode == LStData.MODE_GAME_PLAY;
        }
    }

    private class LayerData extends AreaDataContainer implements ILayer {

        public LayerData(String aID, int acX, int acY, LAYERs_GameScene gsc) {
            super(aID, acX, acY, gsc);
        }

        @Override
        public void draw(Graphics2D G2D) {
            //G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "D", 400, 240), 0, 0, null);

        }

        @Override
        public boolean removeMe() {
            return false;
        }

        @Override
        public boolean renderMe() {
            return LStData.enableCollisionOverlay;
        }
    }

    private class LayerAnimation extends AreaDataContainer implements ILayer {

        private int extension;

        public LayerAnimation(String aID, int acX, int acY, LAYERs_GameScene gsc, int extendSide) {
            super(aID, acX, acY, gsc);
            extension = extendSide;
        }

        @Override
        public void draw(Graphics2D G2D) {
            Image td;

            StData.LOG.println("Animating...  direction is " + extension + " and current shX: " + shiftByX + " shY: " + shiftByY);

            switch (extension) {
                case Bat.FACING_UP:
                    td = StData.resources.grf.getPartTexture(areaID + "B", (areaCoordX - 1) * 400, (areaCoordY - 2) * 240, 400, 480);
                    G2D.drawImage(td, shiftByX, shiftByY - 240, null);
                    td = StData.resources.grf.getPartTexture(areaID + "T", (areaCoordX - 1) * 400, (areaCoordY - 2) * 240, 400, 480);
                    G2D.drawImage(td, shiftByX, shiftByY - 240, null);
                    break;
                case Bat.FACING_DOWN:
                    td = StData.resources.grf.getPartTexture(areaID + "B", (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 400, 480);
                    G2D.drawImage(td, shiftByX, shiftByY, null);
                    td = StData.resources.grf.getPartTexture(areaID + "T", (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 400, 480);
                    G2D.drawImage(td, shiftByX, shiftByY, null);
                    break;
                case Bat.FACING_LEFT:
                    td = StData.resources.grf.getPartTexture(areaID + "B", (areaCoordX - 2) * 400, (areaCoordY - 1) * 240, 800, 240);
                    G2D.drawImage(td, shiftByX - 400, shiftByY, null);
                    td = StData.resources.grf.getPartTexture(areaID + "T", (areaCoordX - 2) * 400, (areaCoordY - 1) * 240, 800, 240);
                    G2D.drawImage(td, shiftByX - 400, shiftByY, null);
                    break;
                case Bat.FACING_RIGHT:
                    td = StData.resources.grf.getPartTexture(areaID + "B", (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 800, 240);
                    G2D.drawImage(td, shiftByX, shiftByY, null);
                    td = StData.resources.grf.getPartTexture(areaID + "T", (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 800, 240);
                    G2D.drawImage(td, shiftByX, shiftByY, null);
                    break;
            }


        }

        @Override
        public boolean removeMe() {
            return false;
        }

        @Override
        public boolean renderMe() {
            return true;
        }
    }


}
