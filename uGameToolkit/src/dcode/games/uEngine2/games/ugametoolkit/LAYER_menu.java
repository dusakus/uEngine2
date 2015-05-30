package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * Created by dusakus on 09.04.15.
 */
public class LAYER_menu implements ILayer {

    MenuLogic ml;

    public LAYER_menu(MenuLogic menuLogic) {
        ml = menuLogic;
    }

    @Override
    public void draw(Graphics2D G2D) {
        switch (ml.getRenderMode()) {
            case MenuLogic.RENDERMODE_LIST:
                for (int i = 0; i < ml.getListLength(); i++) {
                    G2D.drawImage(StData.resources.grf.getString("FGENW", ml.getListEntry(i)), 10, 20 * i + 10, null);
                }
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
