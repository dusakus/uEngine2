package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelList;

import static dcode.games.uEngine2.games.ld32warmup.LStData.*;
import static dcode.games.uEngine2.StData.*;


/**
 * Created by dusakus on 10.04.15.
 */
public class GameLogic implements ILogicTask {


    public Room room;

    public Player player;

    public int currentLevel = 1;

    @Override
    public boolean isReady() {
        return LStData.currentMode == LStData.MODE_GAME_PLAY;
    }

    @Override
    public void perform() {
        switch (currentStatus) {
            case 101:
                //TODO: game tick;
                break;
            case 1:
                LOG.println("[GL] Entering game_play environment");
                currentStatus++;
                break;
            case 2:
                currentLevel = 1;
                currentStatus = 109;
                break;
            case 109:
                if (room == null || room.levelID != currentLevel) {
                    currentStatus = 501;
                } else {
                    currentStatus = 102;
                }
                break;
            case 102:
                room.init();
                LOG.println("[GL] level begins now");
                currentStatus = 101;
                break;
            case 501:
                LOG.println("[GL] loading new level: " + currentLevel);
                if (LevelList.levelList[currentLevel] == null) {
                    LOG.println("[GL] level doesn't exist, entering room 0");
                    currentLevel = 0;
                } else {
                    room = new Room(LevelList.levelList[currentLevel], currentLevel);
                    currentStatus++;
                }
                break;
            case 502:
                LOG.println("[GL] requesting room textures...");

                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_BACK.png", room.texId + "B"));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_FRONT.png", room.texId + "F"));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_DATA.png", room.texId + "D"));

                currentStatus = 512;
            case 512:
                if (room.tryLoadDataLayer()) {
                    currentStatus++;
                }
                break;
            case 513:
                player = new Player();
                currentStatus = 109;

        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
