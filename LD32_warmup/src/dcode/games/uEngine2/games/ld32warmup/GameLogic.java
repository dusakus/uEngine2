package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.LOGIC.ILogicTask;

/**
 * Created by dusakus on 10.04.15.
 */
public class GameLogic implements ILogicTask {
    @Override
    public boolean isReady() {
        return LStData.currentMode == LStData.MODE_GAME_PLAY;
    }

    @Override
    public void perform() {

    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
