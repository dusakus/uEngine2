package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.GFX.ILayer;

import java.awt.*;

/**
 * Created by dusakus on 10.02.15.
 */
public class LAYER_Loading implements ILayer {
    int blkcount = 1;

    int targetStatus;

    public LAYER_Loading(int target) {
        targetStatus = target;
    }

    @Override
    public void draw(Graphics2D G2D) {
        G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        G2D.setColor(Color.WHITE);
        G2D.fillRect(0, 0, 520, 300);
        G2D.setColor(Color.DARK_GRAY);
        G2D.fillRect(20, 260, 480, 20);
        G2D.setColor(Color.GREEN);
        G2D.drawString("Current Mode: " + LStData.currentMode, 120, 246);
        G2D.drawString("Current Status: " + LStData.currentStatus + " / " + targetStatus, 120, 256);

        G2D.setColor(Color.green);
        G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        G2D.drawString("LOADING", 24, 256);

        G2D.setColor(Color.green);
        for (int i = 0; i < blkcount; i++) {
            G2D.fillRect(22 + i * 14, 262, 12, 16);
        }
        blkcount++;
        if (blkcount > 34) {
            blkcount = 1;
        }

    }

    @Override
    public boolean removeMe() {
        return LStData.currentStatus == targetStatus;
    }

    @Override
    public boolean renderMe() {
        return true;
    }
}
