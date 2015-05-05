package dcode.games.uEngine2.games.ugametoolkit.uiinput;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.games.ugametoolkit.InHandler;
import dcode.games.uEngine2.games.ugametoolkit.LStData;

/**
 * Created by dusakus on 01.05.15.
 */
public class ButtonsTask implements ILogicTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        if (InHandler.clickedLeft) {
            LStData.buttons.LClick(InHandler.lastLclickX, InHandler.lastLclickY);
            InHandler.clickedLeft = false;
        }
        if (InHandler.clickedRight) {
            LStData.buttons.RClick(InHandler.lastRclickX, InHandler.lastRclickY);
            InHandler.clickedRight = false;
        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
