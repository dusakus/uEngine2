package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ugametoolkit.insanity.RuntimeSanityChecker;
import dcode.games.uEngine2.games.ugametoolkit.thingstoload.ListofLists;

import java.io.File;

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
                LStData.currentStatus++;
                break;
            case 1:
                if (!new File(LStData.defaultFile).exists()) new File(LStData.defaultFile).mkdir();
                LStData.currentStatus = 9;
                break;
            case 9:
                LOG.println("[INIT] ...");
                LStData.currentStatus = 21;
                break;

            case 21:
                LOG.println("started content loader");
                ListofLists.instance = new ListofLists();
                ListofLists.instance.fillList();
                LStData.currentStatus = 22;
                break;
            case 22:
                if (ListofLists.instance.performNext()) LStData.currentStatus = 23;
                break;
            case 23:
                if (ListofLists.instance.isFinished()) LStData.currentStatus = 25;
                break;
            case 25:
                LOG.println("finished loading content");
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
