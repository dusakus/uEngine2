package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.tools.Shortcuts;

/**
 * Created by dusakus on 4/29/15.
 */
public class LoadBanners implements ILoadMe {
    @Override
    public void loadDys() throws Exception {
        Shortcuts.requestTexture("BONUS/headerMain.png", "BANNERhm");
    }

    @Override
    public boolean checkFinished() {
        return Shortcuts.isTexAviable("BANNERhm");
    }
}
