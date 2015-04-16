package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.GFX.postproc.PP_scaleblur;
import dcode.games.uEngine2.GFX.sprites.Sprite;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelList;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameMessage1;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameMessage2;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameSceneBACK;
import dcode.games.uEngine2.games.ld32warmup.render.LAYER_GameSceneFRONT;

import static dcode.games.uEngine2.StData.*;
import static dcode.games.uEngine2.games.ld32warmup.LStData.*;


/**
 * Created by dusakus on 10.04.15.
 */
public class GameLogic implements ILogicTask {


    public String message = "NULL";

    public Room room;

    public Player player;

    public int currentLevel = 1;

    public Item currentItem = new Item("nul", "nul", false, "nul");

    public ScreenContent inGameSC = null;
    public static final int MSGtYPE_warning = 11;
    public static final int MSGtYPE_info = 12;
    public static final int MSGtYPE_item = 13;
    private long waitBegan;

    @Override
    public boolean isReady() {
        return LStData.currentMode == LStData.MODE_GAME_PLAY;
    }

    @Override
    public void perform() {
        switch (currentStatus) {


            case 101:           // inGame Tick
                room.tick(this);
                player.checkMove(inGameSC);
                if (newRightClick) {
                    room.checkRClick(player, RclickX, RclickY);
                    newRightClick = false;
                }
                break;


            case 1:
                LOG.println("[GL] Entering game_play environment");
                GL = this;
                currentLevel = 1;

                currentStatus++;
                break;
            case 2:
                if (inGameSC != null) currentStatus = 109;
                else currentStatus = 11;
                break;


            // First run INIT

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
            //END

            case 109:           // try entering the level
                if (room == null || room.levelID != currentLevel) {
                    inGameSC.sprites_middle = new int[StData.setup.spriteLayerSize];
                    inGameSC.sprites = new Sprite[StData.setup.spriteTableSize];
                    inGameSC.sprites[2] = player;
                    currentGC.currentSC = inGameSC;
                    currentStatus = 501;
                } else {
                    currentStatus = 102;
                }
                break;


            case 102:           //Begin loaded level
                room.init();
                LOG.println("[GL] level begins now");
                StData.currentGC.currentSC = inGameSC;
                inGameSC.sprites[2] = player;
                currentStatus = 2020;  // Start animation
                break;


            case 501:           //Load level
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

                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_BACK.png", "RB" + currentLevel));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_FRONT.png", "RF" + currentLevel));
                threadManager.BGT.addTask(new LoadBasicTexture("rooms/" + room.texId + "_DATA.png", "RD"+currentLevel));
                waitBegan = System.currentTimeMillis();
                currentStatus = 512;
            case 512:
                LOG.println("[GL] waiting for data texture...");

                if (room.tryLoadDataLayer() || waitBegan + 1000<System.currentTimeMillis()) {
                    currentStatus++;
                }
                break;
            case 513:
                player.setX((int) room.level.getInitialPlayerLocation().getX());
                player.setY((int) room.level.getInitialPlayerLocation().getY());
                player.targetX = (int) room.level.getInitialPlayerLocation().getX();
                player.targetY = (int) room.level.getInitialPlayerLocation().getY();
                player.inRoomX = player.targetX;
                player.inRoomY = player.targetY;
                currentStatus = 521;
                break;
            case 521:
                LOG.println("[GL] requesting worldObject textures...");
                room.loadWorldObjects();
                currentStatus++;
                break;
            case 522:
                LOG.println("[GL] placing world objects");
                room.insertWorldObjects(inGameSC);
                currentStatus = 109;
                break;
            case 1201:
                if (inGameSC.layers_Overlay.size() == 0) {
                    currentStatus = 101;
                }
                break;
            case 2020:
                threadManager.LT.LOOP_TPS = 10;
                threadManager.LT.LOOP_Recalculate = true;
                currentStatus++;
                break;
            case 2050:
                threadManager.LT.LOOP_TPS = 60;
                threadManager.LT.LOOP_Recalculate = true;
                inGameSC.postProcessors[1] = null;
                currentStatus = 101;
                break;
            case 2001:
                currentLevel++;
                threadManager.LT.LOOP_TPS = 10;
                threadManager.LT.LOOP_Recalculate = true;
                currentStatus++;
                break;
            case 2002:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.1F);
                currentStatus++;
                break;

            case 2003:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.4F);
                currentStatus++;
                break;

            case 2004:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.8F);
                currentStatus++;
                break;

            case 2005:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(2.1F);
                currentStatus++;
                break;

            case 2006:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(2.8F);
                currentStatus++;
                break;

            case 2007:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(4F);
                currentStatus++;
                break;

            case 2008:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(5F);
                currentStatus++;
                break;

            case 2009:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(7F);
                currentStatus++;
                break;

            case 2010:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(9F);
                currentStatus++;
                break;

            case 2011:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(13F);
                currentStatus++;
                break;

            case 2012:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(18F);
                currentStatus++;
                break;

            case 2013:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(25F);
                currentStatus++;
                break;

            case 2014:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(42F);
                room.unloadRoom();
                currentStatus = 109;
                break;
            case 2033:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.1F);
                currentStatus = 2050;
                break;

            case 2032:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.4F);
                currentStatus++;
                break;

            case 2031:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.8F);
                currentStatus++;
                break;

            case 2030:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(2.1F);
                currentStatus++;
                break;

            case 2029:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(2.8F);
                currentStatus++;
                break;

            case 2028:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(4F);
                currentStatus++;
                break;

            case 2027:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(5F);
                currentStatus++;
                break;

            case 2026:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(7F);
                currentStatus++;
                break;

            case 2025:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(9F);
                currentStatus++;
                break;

            case 2024:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(13F);
                currentStatus++;
                break;

            case 2023:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(18F);
                currentStatus++;
                break;

            case 2022:
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(25F);
                currentStatus++;
                break;

            case 2021:
                player.setX((int) room.level.getInitialPlayerLocation().getX());
                player.setY((int) room.level.getInitialPlayerLocation().getY());
                player.targetX = (int) room.level.getInitialPlayerLocation().getX();
                player.targetY = (int) room.level.getInitialPlayerLocation().getY();
                player.inRoomX = player.targetX;
                player.inRoomY = player.targetY;
                currentGC.currentSC.postProcessors[1] = new PP_scaleblur(42F);
                currentStatus++;
                break;

        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }

    public void showMessage(String s, int msGtYPE, Item item) {
        LOG.println("Showing message type "+msGtYPE);
        switch (msGtYPE) {
            case MSGtYPE_warning:
                currentStatus = 1201;
                message = "WARN: " + s;
                inGameSC.layers_Overlay.add(new LAYER_GameMessage1("WARN", 60, this));  //Orange message, with 1 second timeout;
                break;
            case MSGtYPE_info:
                currentStatus = 1201;
                message = "> " + s;
                inGameSC.layers_Overlay.add(new LAYER_GameMessage1("INFO", 90, this));  //Blue message, with 1.5 second timeout;
                break;
            case MSGtYPE_item:
                currentStatus = 1201;
                message = s;
                inGameSC.layers_Overlay.add(new LAYER_GameMessage2(item, 180, this));  //Green message, with 2 second timeout;
                break;

        }
    }

    public void reLoadWorldObjects(WorldObject[] wos) {
        room.reLoadWorldObjects(inGameSC, wos);
    }
}
