package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.GFX.ILayer;

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

    }

    @Override
    public boolean removeMe() {
        return false;
    }

    @Override
    public boolean renderMe() {
        return false;
    }
}
