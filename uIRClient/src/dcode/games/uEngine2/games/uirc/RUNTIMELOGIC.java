package dcode.games.uEngine2.games.uirc;

import dcode.games.uEngine2.LOGIC.ILogicTask;

/**
 * Created by dusakus on 31.03.15.
 */
public class RUNTIMELOGIC implements ILogicTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {

    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
