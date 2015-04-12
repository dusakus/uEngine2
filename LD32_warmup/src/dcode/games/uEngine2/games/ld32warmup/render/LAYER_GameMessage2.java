package dcode.games.uEngine2.games.ld32warmup.render;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.GameLogic;
import dcode.games.uEngine2.games.ld32warmup.Item;

import java.awt.*;

/**
 * Created by dusakus on 12.04.15.
 */
public class LAYER_GameMessage2 implements ILayer{

    GameLogic gl;
    Item it;
    int ticksLeft;

    public LAYER_GameMessage2(Item item, int i, GameLogic gameLogic) {
        ticksLeft = i+1;
        it = item;
        gl = gameLogic;

    }

    @Override
    public void draw(Graphics2D G2D) {

        G2D.drawImage(StData.resources.grf.getScaledTexture("MSGITEM", 320, 100),40,20,null);
        G2D.drawImage(StData.resources.grf.getScaledTexture(it.itemTexture, 60, 60),280,40,null);

        G2D.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
        G2D.setColor(Color.BLACK);
        G2D.drawString("You just found",60, 60);
        G2D.drawString(it.itemName,60, 76);
        G2D.drawString(gl.message,60, 92);
        ticksLeft--;
    }

    @Override
    public boolean removeMe() {
        return ticksLeft <= 0;
    }

    @Override
    public boolean renderMe() {
        return true;
    }
}
