package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelList;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameSceneBACK;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameSceneFRONT;

import static dcode.games.uEngine2.games.ld32warmup.LStData.*;
import static dcode.games.uEngine2.StData.*;


/**
 * Created by dusakus on 10.04.15.
 */
public class GameLogic implements ILogicTask {


    public Room room;

    public Player player;

    public int currentLevel = 1;

    private ScreenContent inGameSC = null;
    private ScreenContent temp = null;
    public static final int MSGtYPE_warning = 11;

    @Override
    public boolean isReady() {
        return LStData.currentMode == LStData.MODE_GAME_PLAY;
    }

    @Override
    public void perform() {
        switch (currentStatus) {
            case 101:
                room.tick(this);
                player.checkMove(inGameSC);
                break;
            case 1:
                LOG.println("[GL] Entering game_play environment");
                currentStatus++;
                break;
            case 2:
                currentLevel = 1;
                if (inGameSC != null) currentStatus = 109;
                else currentStatus = 11;
                break;
            case 11:
                LOG.println("[GL] inGameSC is null, initializing...");
                inGameSC = new ScreenContent();
                currentStatus++;
                break;
            case 12:
                inGameSC.layers_Background.add(new LAYER_GameSceneBACK());
                inGameSC.layers_Foreground.add(new LAYER_GameSceneFRONT());
                currentStatus++;
                break;
            case 13:
                player = new Player(this);
                LOG.println("[GL] requesting player textures");
                player.requestTextures();
                inGameSC.sprites[2] = player;
                currentStatus++;
                break;
            case 14:
                LOG.println("[GL] initialization complete");
                currentStatus = 2;
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
                temp = StData.currentGC.currentSC;
                StData.currentGC.currentSC = inGameSC;
                inGameSC.sprites[2] = player;
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

                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_BACK.png", "RB"));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_FRONT.png", "RF"));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_DATA.png", "RD"));

                currentStatus = 512;
            case 512:
                if (room.tryLoadDataLayer()) {
                    currentStatus++;
                }
                break;
            case 513:
                player = new Player(this);
                player.setX((int)room.level.getInitialPlayerLocation().getX());
                player.setY((int) room.level.getInitialPlayerLocation().getY());
                player.targetX = (int)room.level.getInitialPlayerLocation().getX();
                player.targetY = (int)room.level.getInitialPlayerLocation().getY();
                player.inRoomX = player.targetX;
                player.inRoomY = player.targetY;
                currentStatus = 109;

        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }

    public void showMessage(String s, int msGtYPE_warning) {

    }
}
