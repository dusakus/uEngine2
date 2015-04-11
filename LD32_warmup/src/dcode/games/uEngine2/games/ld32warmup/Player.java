package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.GFX.sprites.Sprite;
import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class Player extends Sprite {

    public int animID = 1;
    public int direction = 0;

    private int lastX = 0, lastY = 0;
    public int targetX = 0, targetY = 0;


    @Override
    public Image getCustomTexture() {
        if (lastX != x || lastY != y) {
            animID++;
            if (lastX > x) direction = 0;
            if (lastX < x) direction = 1;
            if (animID > 4) animID = 1;
            lastX = x;
            lastY = y;
        }
        return StData.resources.grf.getTexture("pla" + (animID + (direction * 4)));
    }

    @Override
    public boolean doCustomRender() {
        return false;
    }

    @Override
    public void customRender(Graphics2D G) {

    }

    public void requestTextures() {
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL0.png", "pla1"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL1.png", "pla2"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL2.png", "pla3"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL3.png", "pla4"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR0.png", "pla5"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR1.png", "pla6"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR2.png", "pla7"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR3.png", "pla8"));
    }

    boolean delayY = false;

    public void checkMove(ScreenContent sc) {

        sc.sprites_middle[0] = 2;

        if (targetX > x) x++;
        if (targetX < x) x--;
        if (delayY) {
            delayY = false;
        } else {
            delayY = true;
            if (targetY > y) y++;
            if (targetY < y) y--;
        }
    }
}
