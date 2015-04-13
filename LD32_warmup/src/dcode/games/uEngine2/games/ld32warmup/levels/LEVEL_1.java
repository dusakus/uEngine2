package dcode.games.uEngine2.games.ld32warmup.levels;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.GameLogic;
import dcode.games.uEngine2.games.ld32warmup.Item;
import dcode.games.uEngine2.games.ld32warmup.LStData;
import dcode.games.uEngine2.games.ld32warmup.WorldObject;
import dcode.games.uEngine2.tools.numbarTools;

import java.awt.*;
import java.util.Random;

/**
 * Created by dusakus on 12.04.15.
 */
public class LEVEL_1 extends LevelBase {

    Item key;
    boolean keyFound = false;

    WorldObject[] wos;
    private String[] RTexts;

    @Override
    public String getTextureId() {
        return "R1";
    }

    @Override
    public void registerSprites(WorldObject[] worldObjects) {
        wos = worldObjects;
        wos[0] = new WorldObject("table-001.png", "TAB", 11, 277, 263);
        wos[1] = new WorldObject("vase-001.png", "VAS", 12, 326, 213);
        wos[4] = new WorldObject("books-003.png", "BOK", 13, 283, 254);

        wos[2] = new WorldObject("sofa-001.png", "SOF", 6, 48, 222);


        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/R/1/dor_closed.png", "DOR"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-001.png", "KEY0"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-002.png", "KEY1"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-003.png", "KEY2"));

        key = new Item("Iron key", "KEY1", false, null);

        RTexts = new String[30];
        //block 0
        RTexts[0] = "Notthing here.";
        RTexts[1] = "Better luck next time.";
        RTexts[2] = "Try again!";
        RTexts[3] = "Nope.";
        RTexts[4] = "Try somwhere else.";
        RTexts[5] = "Not there";
        RTexts[6] = "404 - Not Found";
        RTexts[7] = "You shaaal nooot fiiind!";

        //block 1
        RTexts[10] = "Too far, bro";
        RTexts[11] = "Drive me closer!";
        RTexts[12] = "Can't reach...";
        RTexts[13] = "My hand is to short";
        RTexts[14] = "Go, go Gadget hand";
        RTexts[15] = "One does not simple...";
        RTexts[16] = "DISTANCE=\"too far\"";
        RTexts[17] = "I don't have teleport :C";

        //block 2
        RTexts[20] = "Found it!";
        RTexts[21] = "A key! Let's open that door!";
        RTexts[22] = "Wooohooo!";
        RTexts[23] = "ITEM - {KEY} - FOUND";
        RTexts[24] = "It's here!";
        RTexts[25] = "Yaay! A key!";
        RTexts[26] = "Nice...";
        RTexts[27] = "Myy, Precioooousss!!";
    }

    @Override
    public Point getInitialPlayerLocation() {
        return new Point(200, 260);
    }

    @Override
    public void onObjectClicked(int objectID, Color playerPositionColor) {
        switch (objectID) {
            case 13:
                if (playerPositionColor.getBlue() == 13) {
                    LStData.GL.showMessage("What is this?", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage(getRandomText(1), GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 6:
                if (playerPositionColor.getBlue() == 6) {
                    LStData.GL.showMessage("Nothing worth reading...", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage(getRandomText(1), GameLogic.MSGtYPE_warning, null);
                }
                break;
            case 8:
                if(keyFound){
                    LStData.GL.showMessage("I can leave now", GameLogic.MSGtYPE_info, null);
                } else {
                    LStData.GL.showMessage("the Doors are locked...", GameLogic.MSGtYPE_info, null);
                }
                break;
            case 45:
            LStData.GL.showMessage("I like this picture", GameLogic.MSGtYPE_info, null);
                break;
            case 64:
                LStData.GL.showMessage("is it blood?", GameLogic.MSGtYPE_info, null);
                break;
            case 30:
                LStData.GL.showMessage("Sadly i can't sit...", GameLogic.MSGtYPE_info, null);
                break;
        }
        if (objectID == 11 || objectID == 12 || objectID == 14 || objectID == 5) {
            if (playerPositionColor.getBlue() == objectID) {
                if (new Random().nextInt() % 2 == 1) {
                    if (keyFound) LStData.GL.showMessage(getRandomText(0), GameLogic.MSGtYPE_info, null);
                    else {
                        StData.LOG.println("[L1] " + getRandomText(2));
                        LStData.GL.showMessage(getRandomText(2), GameLogic.MSGtYPE_item, key);
                        keyFound = true;
                        wos[3] = new WorldObject("dor_closed.png", "DOR", 2, 172, 47);

                        LStData.GL.reLoadWorldObjects(wos);
                    }
                } else
                    LStData.GL.showMessage(getRandomText(0), GameLogic.MSGtYPE_info, null);
            } else {
                LStData.GL.showMessage(getRandomText(1), GameLogic.MSGtYPE_warning, null);
            }
        }
    }

    private String getRandomText(int block) {
        int i = numbarTools.mod(new Random().nextInt() % 8);
        String s = RTexts[i + (block*10)];
        return s;
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
