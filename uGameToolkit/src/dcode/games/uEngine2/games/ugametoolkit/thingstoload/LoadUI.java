package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.tools.Shortcuts;

/**
 * Created by dusakus on 4/29/15.
 */
public class LoadUI implements ILoadMe{
    @Override
    public void loadDys() throws Exception {
        for (int i = 0; i < 3; i++) {
            Shortcuts.requestTexture("GENERAL_UI/buttonBlue_"+i+".png", "UIbb"+i);
        }

        Shortcuts.requestTexture("GENERAL_UI/viewportBounds_0.png", "UIva");
        Shortcuts.requestTexture("GENERAL_UI/viewportBounds_1.png", "UIvb");
        Shortcuts.requestTexture("GENERAL_UI/viewportBounds_2.png", "UIvc");

    }

    @Override
    public boolean checkFinished() {
        return Shortcuts.isTexAviable("UIvc");
    }
}
