package dcode.games.uEngine2.games.ugametoolkit.layers;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.games.ugametoolkit.LStData;
import dcode.games.uEngine2.games.ugametoolkit.uiinput.Button;

import java.awt.*;

/**
 * Created by dusakus on 01.05.15.
 */
public class LAYER_buttons implements ILayer {
    @Override
    public void draw(Graphics2D G2D) {
        for (Button b : LStData.buttons.getList()) {
            if (b.canRender()) {
                b.draw(G2D);
            }
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
