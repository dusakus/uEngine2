package dcode.games.uEngine2.games.ugametoolkit;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import dcode.games.uEngine2.GFX.layers.ClearColorLayer;
import dcode.games.uEngine2.GameContainer;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ugametoolkit.insanity.MenuSanityChecker;

import java.awt.*;

/**
 * Created by dusakus on 09.04.15.
 */
public class MenuLogic implements ILogicTask {

    public static final int RENDERMODE_LIST = 10;
    public static final int RENDERMODE_CONFIRM = 11;
    public static final int RENDERMODE_INPUT = 12;


    public GameContainer menuGC;
    private MenuSanityChecker msc = new MenuSanityChecker();
    public LAYER_menu lm;

    @Override
    public boolean isReady() {
        return LStData.currentMode == LStData.MODE_MENU_MAIN || LStData.currentMode == LStData.MODE_MENU_INFO;
    }

    @Override
    public void perform() {
        msc.sanityCheck(this, lm);
        if (LStData.currentMode == LStData.MODE_MENU_MAIN) {
            switch (LStData.currentStatus) {
                case 1:
                    StData.LOG.println("[MENU] Entry point");
                    if (menuGC == null) {
                        LStData.currentStatus = 1501;
                    } else {
                        LStData.currentStatus = 10;
                    }
                    break;
                case 10:
                    StData.currentGC = menuGC;
                    LStData.currentStatus = 101;
                    break;
                case 101:
                    tickInput();
                    break;

                case 1501:
                    StData.LOG.println("[MENU] (first time, will initialize)");
                    StData.LOG.println("[MENU] Creating menuGC...");
                    LStData.currentStatus++;
                    break;
                case 1502:
                    menuGC = new GameContainer();
                    LStData.currentStatus++;
                    break;
                case 1503:
                    menuGC.currentSC.layers_Background.add(new ClearColorLayer(Color.WHITE));
                    menuGC.currentSC.layers_Center.add(new LAYER_menu(this));
                    LStData.currentStatus = 1;
            }
        } else {

        }
    }

    public int getRenderMode() {
        return RENDERMODE_LIST;
    }

    public int getListLength() {
        return 4;
    }

    public String getListEntry(int id) {
        return "Entry " + id;
    }


    private void tickInput() {

    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
