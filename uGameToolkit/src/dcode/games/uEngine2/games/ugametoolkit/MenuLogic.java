package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.GameContainer;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ugametoolkit.insanity.MenuSanityChecker;
import dcode.games.uEngine2.games.ugametoolkit.layers.LAYER_BG;
import dcode.games.uEngine2.games.ugametoolkit.layers.LAYER_buttons;
import dcode.games.uEngine2.games.ugametoolkit.randomParts.StringProvider;
import dcode.games.uEngine2.games.ugametoolkit.uiinput.Button;
import dcode.games.uEngine2.games.ugametoolkit.uiinput.IButtonInputReceiver;

import java.awt.*;

/**
 * Created by dusakus on 09.04.15.
 */
public class MenuLogic implements ILogicTask, IButtonInputReceiver {

    public static final int RENDERMODE_LIST = 10;
    public static final int RENDERMODE_CONFIRM = 11;
    public static final int RENDERMODE_INPUT = 12;


    public GameContainer menuGC;
    public LAYER_menu lm;
    private MenuSanityChecker msc = new MenuSanityChecker();

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
                    menuGC.currentSC.layers_Background.add(new LAYER_BG("UIBG0"));
                    menuGC.currentSC.layers_Center.add(new LAYER_menu(this));
                    menuGC.currentSC.layers_Foreground.add(new LAYER_buttons());
                    LStData.buttons.registerButton(new Button(this, "UIbb",
                            new StringProvider() {
                                @Override
                                public String updateSource() {
                                    return "$%$$$$$^^^";
                                }
                            }, new Rectangle(128, 128, 10, 10), "FGENW"));
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

    @Override
    public void handleButtonPress(Button b) {
        StData.LOG.println("button pressed");
        b.setIDforT(2, 16);
    }

    @Override
    public void handleButtonRightClick(Button b) {

    }
}
