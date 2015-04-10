package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelList;

import static dcode.games.uEngine2.StData.LOG;
import static dcode.games.uEngine2.StData.currentGC;
import static dcode.games.uEngine2.games.ld32warmup.LStData.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class MainLogic implements ILogicTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        switch (currentMode) {
            case MODE_GAME_PLAY:

                break;
            case MODE_MENU_MAIN:

                break;
            case MODE_INIT:
                switch (currentStatus) {
                    case 0:
                        LOG.println("[INIT] Logic Entrypoint reached");
                        LOG.println("[INIT] Quickstarting loading screen");
                        currentGC.currentSC.layers_Overlay.add(new LAYER_Loading(101));
                        currentStatus++;
                        break;
                    case 1:
                        LOG.println("[INIT] Creating Logic objects");
                        currentGC.currentLT.registerBasic(new MenuLogic());
                        currentGC.currentLT.registerBasic(new GameLogic());
                        currentStatus += 9;
                        break;
                    case 10:
                        LOG.println("[INIT] requesting texture preloading");
                        //TODO: preload textures
                        currentStatus++;
                        break;
                    case 11:
                        LOG.println("[INIT] creating level table");
                        LevelList.fillList();
                        currentStatus += 9;
                        break;
                    case 20:
                        LOG.println("[INIT] creating game objects");

                        currentStatus = 50;
                        break;
                    case 50:
                        LOG.println("[INIT] done, entering menu...");
                        currentMode = MODE_MENU_MAIN;
                        currentStatus = 1;
                        break;
                    default:
                        if (currentStatus < 0 || currentStatus > 50) {
                            LOG.println("[INIT] critical exception occured in state engine", "E5");
                        }
                        currentStatus--;
                }
                break;
            case MODE_SHUTDOWN:
                StData.gameIsRunning = false;
                break;
            default:
                LOG.println("[MGL] critical exception occured in state engine", "E5");
                currentMode = 10;
                currentStatus = 0;
        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
