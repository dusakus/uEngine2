package dcode.games.uEngine2.games.uirc;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;

/**
 * Created by dusakus on 01.04.15.
 */
public class INITLOGIC implements ILogicTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {


        if (LStData.connected) {
            StData.logicTasks.registerBasic(new RUNTIMELOGIC());
            StData.generalBGT.HPTasks.add(new NetworkReader());
            StData.currentGC.currentSC.layers_Foreground.add(new LAYER_main());
        }

    }

    @Override
    public boolean doRepeat() {
        return !LStData.connected;
    }
}
