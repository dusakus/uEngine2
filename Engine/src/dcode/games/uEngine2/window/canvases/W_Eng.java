package dcode.games.uEngine2.window.canvases;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.GFX.eui.SCU;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.window.Canvas;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 13.05.15.
 *
 * default engine utility size: 4:3  320x240 X2
 */
public class W_Eng extends Canvas {

    private SCU utillayer;

    public W_Eng() {
        setMaximumSize(new Dimension(640, 480));
        setPreferredSize(new Dimension(640, 480));
        StData.NextFrame = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);

        utillayer = new SCU();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }

        Graphics2D g = StData.NextFrame.createGraphics();
        utillayer.draw(g);
        g.dispose();

        Graphics grf = bs.getDrawGraphics();
        grf.drawImage(StData.NextFrame.getScaledInstance(640, 480, Image.SCALE_REPLICATE), 0, 0, null);
        bs.show();

        //this.repaint();
        this.requestFocusInWindow();
    }
}
