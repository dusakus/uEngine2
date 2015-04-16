package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBitmapFont;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ugametoolkit.insanity.RuntimeSanityChecker;

import static dcode.games.uEngine2.StData.LOG;

/**
 * Created by dusakus on 01.04.15.
 */
public class BaseLogic implements ILogicTask {

    private RuntimeSanityChecker rsc = new RuntimeSanityChecker();


    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        rsc.sanityCheck();

        switch (LStData.currentMode) {
            case LStData.MODE_INIT:
                tickInit();
                if (LStData.currentStatus == 301) {
                    LStData.currentMode = LStData.MODE_MENU_MAIN;
                    LStData.currentStatus = 1;
                }
                break;

            default:
                rsc.sanityCheck();
                LStData.currentMode = LStData.MODE_MENU_MAIN;
                break;

        }

    }

    private void tickInit() {
        switch (LStData.currentStatus) {
            case 0:
                StData.currentGC = LStData.globalGameContainer;
                LOG.println("[INIT] Quickstarting loading screen");
                StData.currentGC.currentSC.layers_Foreground.add(new LAYER_Loading(101));
                LStData.currentStatus = 9;
                break;
            case 9:
                LOG.println("[INIT] Loading font...");
                StData.threadManager.BGT.addTask(new LoadBitmapFont("FONT/pixel_7_9.png", "FGENB", 7, 9, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?#*)(][><,.:;-+_=&~^$@\"/\\"));
                StData.threadManager.BGT.addTask(new LoadBitmapFont("FONT/pixel_7_9_WHITE.png", "FGENW", 7, 9, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?#*)(][><,.:;-+_=&~^$@\"/\\"));
                LStData.currentStatus = 51;
                break;

            case 51:
                StData.logicTasks.registerBasic(new MenuLogic());
                LStData.currentStatus = 209;
                break;

            case 209:
                LStData.currentStatus = 301;
                break;

            case 301:
                LOG.println("[INIT] initialization finished, entering menu");
                LStData.currentStatus = 1;
                LStData.currentMode = LStData.MODE_MENU_MAIN;
                break;

        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
