package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.GFX.sprites.Sprite;
import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class Player extends Sprite {

    public int animID = 0;


    @Override
    public Image getCustomTexture() {
        return StData.resources.grf.getTexture("pla" + animID);
    }

    @Override
    public boolean doCustomRender() {
        return false;
    }

    @Override
    public void customRender(Graphics2D G) {

    }

    public void requestTextures() {
    }
}
