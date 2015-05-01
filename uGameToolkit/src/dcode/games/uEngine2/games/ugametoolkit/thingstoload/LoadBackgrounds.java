package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.tools.Shortcuts;

/**
 * Created by dusakus on 4/29/15.
 */
public class LoadBackgrounds implements ILoadMe {
    @Override
    public void loadDys() throws Exception {
        for (int i = 0; i < 6; i++) {
            Shortcuts.registerOneTimeBGTask(new LoadBasicTexture("GENERAL_UI/BG/BG-0" + i+".png", "UIBG"+i),true);
        }
    }

    @Override
    public boolean checkFinished() {
        return Shortcuts.isTexAviable("UIBG5");
    }
}
