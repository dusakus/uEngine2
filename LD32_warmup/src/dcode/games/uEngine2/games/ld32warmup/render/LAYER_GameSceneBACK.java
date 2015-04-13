package dcode.games.uEngine2.games.ld32warmup.render;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.LStData;
import dcode.games.uEngine2.games.ld32warmup.Player;
import dcode.games.uEngine2.tools.numbarTools;

import java.awt.*;

/**
 * Created by dusakus on 11.04.15.
 */
public class LAYER_GameSceneBACK implements ILayer {
    @Override
    public void draw(Graphics2D G2D) {
        if(LStData.currentStatus == 101 || LStData.currentStatus > 2000){
            G2D.drawImage(StData.resources.grf.getPartTexture("RB"+LStData.GL.currentLevel,
                    numbarTools.clamp(((Player)StData.currentGC.currentSC.sprites[2]).inRoomX - 200, 0, LStData.roomWidth-400),
                    numbarTools.clamp(((Player)StData.currentGC.currentSC.sprites[2]).inRoomY - 150, 0, LStData.roomHeight-300),
                    400,300
            ),0,0,null);
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
