package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.LOGIC.ILogicTask;

/**
 * Created by dusakus on 01.04.15.
 */
public class BaseLogic implements ILogicTask {
    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void perform() {

    }

    @Override
    public boolean doRepeat() {
        return false;
    }
}
