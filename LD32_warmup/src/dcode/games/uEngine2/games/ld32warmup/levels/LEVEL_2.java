package dcode.games.uEngine2.games.ld32warmup.levels;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.GameLogic;
import dcode.games.uEngine2.games.ld32warmup.Item;
import dcode.games.uEngine2.games.ld32warmup.LStData;
import dcode.games.uEngine2.games.ld32warmup.WorldObject;

import java.awt.*;

/**
 * Created by dusakus on 12.04.15.
 */
public class LEVEL_2 extends LevelBase {
    private boolean doorSmallOpen = false;
    private boolean doorBigOpen = false;
    private boolean keyFound = false;

    private WorldObject[] wos;

    Item key;

    @Override
    public String getTextureId() {
        return "R2";
    }

    @Override
    public void registerSprites(WorldObject[] worldObjects) {
        worldObjects[0] = new WorldObject("books-001.png", "BOK1", 11, 144, 101);
        worldObjects[1] = new WorldObject("books-003.png", "BOK2", 12, 171, 108);
        worldObjects[2] = new WorldObject("lamp-001.png", "LAM", 13, 173, 144);

        worldObjects[3] = new WorldObject("commode_dor_clos.png", "CDC", 14, 161, 174);
        worldObjects[4] = new WorldObject("commode_dor_open.png", "CDO", 15, -198, 174);

        worldObjects[5] = new WorldObject("wardrobe_dor_clos.png", "WDC", 16, 252, 76);
        worldObjects[6] = new WorldObject("wardrobe_dor_open.png", "WDO", 17, -400, 76);

        worldObjects[7] = new WorldObject("room-002_dor.png", "DOR", 6, -54, 131);

        wos = worldObjects;

        key = new Item("Gold key", "KEY0", false, null);


        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-001.png", "KEY0"));



    }

    @Override
    public Point getInitialPlayerLocation() {
        return new Point(50, 230);
    }

    @Override
    public void onObjectClicked(int objectID, Color playerPositionColor) {
        switch (objectID) {
            case 1:
                if(keyFound){
                    LStData.GL.showMessage("The door is open, let's go", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage("Gotta find the key...", GameLogic.MSGtYPE_info, null);
                }
            case 11:
                if (playerPositionColor.getBlue() == 11) {
                    if (doorSmallOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorSmallOpen = true;
                        wos[3].setX(-wos[3].getX());
                        wos[4].setX(-wos[4].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 12:
                if (playerPositionColor.getBlue() == 11) {
                    LStData.GL.showMessage("better not talk about this", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 13:
                if (playerPositionColor.getBlue() == 11) {
                    LStData.GL.showMessage("this drawer is empty", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 21:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 22:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 23:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 24:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 25:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                    //DaKey
                        keyFound = true;
                        wos[7].setX(-wos[7].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                        LStData.GL.showMessage("YEAH", GameLogic.MSGtYPE_item, key);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 26:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("There is nothing here", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 27:
                if (playerPositionColor.getBlue() == 21) {
                    if (doorBigOpen) {
                        LStData.GL.showMessage("Really?", GameLogic.MSGtYPE_info, null);
                    } else {
                        doorBigOpen = true;
                        wos[6].setX(192);
                        wos[5].setX(-wos[5].getX());
                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else {
                    LStData.GL.showMessage("TOO FAR", GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 31:
                LStData.GL.showMessage("That's a nice carpet", GameLogic.MSGtYPE_info, null);

        }
    }

    @Override
    public boolean isColorUnlocked(int RED) {
        switch (RED) {
            case 254:
                return keyFound;
            case 250:
                LStData.currentStatus = 2001;
            default:
                return true;
        }
    }
}
