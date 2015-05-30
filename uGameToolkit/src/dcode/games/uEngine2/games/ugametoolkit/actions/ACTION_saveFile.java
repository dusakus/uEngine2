package dcode.games.uEngine2.games.ugametoolkit.actions;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.games.ugametoolkit.LStData;
import dcode.games.uEngine2.games.ugametoolkit.randomParts.SaveAction;

import java.io.File;

import static dcode.games.uEngine2.tools.Shortcuts.debug;
import static dcode.games.uEngine2.tools.Shortcuts.log;

/**
 * Created by dusakus on 23.04.15.
 */
public class ACTION_saveFile implements ILogicTask {

    private boolean finished = false;

    private int status = 1;

    private File location;

    private SaveAction action;

    public ACTION_saveFile(SaveAction sa) {
        action = sa;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        switch (status) {
            case 1:
                log("[ACT_SaveToFile] INIT save screen");
                status++;
                break;
            case 2:
                debug("[ACT_SaveToFile] checking default location");
                location = new File(LStData.defaultFile);
                status++;
                break;
        }
    }

    @Override
    public boolean doRepeat() {
        return !finished;
    }
}