package dcode.games.uEngine2.games.ugametoolkit.layers;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.tools.Shortcuts;

import java.awt.*;

/**
 * Created by dusakus on 01.05.15.
 */
public class LAYER_BG implements ILayer {

    Image bgimg;
    String tid;

    public LAYER_BG(String bgid) {
        bgimg = Shortcuts.getTexture(bgid);
        tid = bgid;
    }

    @Override
    public void draw(Graphics2D G2D) {
        for (int i = 0; i < StData.setup.width / 16 + 1; i++) {
            for (int j = 0; j < StData.setup.height / 16 + 1; j++) {
                G2D.drawImage(bgimg, i * 16, j * 16, null);
                //G2D.drawImage(Shortcuts.getTexture(tid), i*16, j*16, null);
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
