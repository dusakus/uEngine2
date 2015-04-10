package dcode.games.uEngine2.games.uirc;

import dcode.games.uEngine2.GFX.ILayer;

import java.awt.*;

/**
 * Created by dusakus on 31.03.15.
 */
public class LAYER_main implements ILayer {
    @Override
    public void draw(Graphics2D G2D) {

        G2D.setColor(Color.BLACK);
        G2D.fillRect(0, 0, 400, 240);

        G2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 8));
        G2D.setColor(Color.DARK_GRAY);
        G2D.drawString("> " + LStData.currentInput.toString(), 8, 222);
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
