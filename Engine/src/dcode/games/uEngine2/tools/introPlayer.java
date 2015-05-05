package dcode.games.uEngine2.tools;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.GFX.layers.ClearColorLayer;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.tools.ext.j2s.gifReader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 01.05.15.
 */
public class introPlayer {

    private gifReader frames;
    private int pY;
    private int pX;
    private double scX;
    private double scY;
    layerDraw ld;
    private int tmp;

    public void playIntro() {
        StData.LOG.println("loading intro");
        loadTextures();
        StData.LOG.println("Creating intro context");
        createContext();
        StData.LOG.println("Playing engine intro ]:V");
        playback();
        StData.LOG.println("intro done");
        clear();
    }

    private void clear() {
        StData.currentGC.currentSC.layers_Foreground.clear();
        frames = null;
        ld = null;
    }

    private void playback() {
        tmp = StData.setup.FPS;
        Shortcuts.setRenderSpeed(45);
        while (ld.running && StData.gameIsRunning) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Shortcuts.setRenderSpeed(tmp);
    }

    private void createContext() {
        int w = StData.setup.width;
        int h = StData.setup.height;
        scX = 1;
        scY = 1;
        w -= 140;
        while (w > 140) {
            scX += 1;
            scY += 1;
            w -= 140;
        }
        pX = w / 2;
        pY = (h - (int) (96 * scY)) / 2;

        ld = new layerDraw(this);
        StData.currentGC.currentSC.layers_Foreground.add(new ClearColorLayer(Color.BLACK));
        StData.currentGC.currentSC.layers_Foreground.add(ld);
    }

    private void loadTextures() {
        frames = new gifReader();
        frames.read(this.getClass().getResourceAsStream("/dcode/games/uEngine2/res/intranim.gif"));
    }

    private class layerDraw implements ILayer {

        introPlayer trgt;
        public boolean running = true;

        private int frame = 0;
        int enddelay = 6;

        public layerDraw(introPlayer i) {
            trgt = i;
        }

        @Override
        public void draw(Graphics2D G2D) {
            G2D.drawImage(trgt.frames.getFrame(frame).getScaledInstance((int) (trgt.frames.getFrameSize().getWidth() * trgt.scX), (int) (trgt.frames.getFrameSize().getHeight() * trgt.scY), BufferedImage.SCALE_REPLICATE), trgt.pX, trgt.pY, null);
            if (frame == trgt.frames.getFrameCount() - 1) {
                if (enddelay < 0) {
                    running = false;
                } else {
                    enddelay--;
                }
            } else {
                if (frame == 72) Shortcuts.setRenderSpeed(10);
                frame++;
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
