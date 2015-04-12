package dcode.games.uEngine2.games.ld32warmup.render;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.GameLogic;

import java.awt.*;

/**
 * Created by dusakus on 12.04.15.
 */
public class LAYER_GameMessage1 implements ILayer {
    GameLogic gl;
    String texBID;
    int ticksLeft;

    public LAYER_GameMessage1(String type, int i, GameLogic igl) {
        ticksLeft = i+1;
        texBID = "MSG"+type;
        gl = igl;
    }

    @Override
    public void draw(Graphics2D G2D) {
        G2D.drawImage(StData.resources.grf.getScaledTexture(texBID, 320, 100),40,20,null);
        G2D.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
        G2D.setColor(Color.BLACK);
        G2D.drawString(gl.message,60, 80);
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
