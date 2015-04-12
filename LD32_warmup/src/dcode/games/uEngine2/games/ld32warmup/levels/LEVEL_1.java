package dcode.games.uEngine2.games.ld32warmup.levels;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.*;

import java.awt.*;

/**
 * Created by dusakus on 12.04.15.
 */
public class LEVEL_1 extends LevelBase {

    Item key;

    @Override
    public String getTextureId() {
        return "R1";
    }

    @Override
    public void registerSprites(WorldObject[] worldObjects) {
        worldObjects[0] = new WorldObject("table-001.png", "TAB", 11, 277, 263);
        worldObjects[1] = new WorldObject("vase-001.png", "VAS", 12, 326, 213);

        worldObjects[2] = new WorldObject("sofa-001.png", "SOF", 6, 48, 222);

        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-001.png", "KEY0"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-002.png", "KEY1"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/K/key-003.png", "KEY2"));

        key = new Item("Iron key", "KEY1", false, null);
    }

    @Override
    public Point getInitialPlayerLocation() {
        return new Point(200, 260);
    }

    @Override
    public void onObjectClicked(int objectID, Color playerPositionColor) {
        switch (objectID){
            case 5:
                if(playerPositionColor.getBlue() == 5){
                    //LStData.GL.showMessage("nothing here", GameLogic.MSGtYPE_info);
                    LStData.GL.showMessage("I opens doors!", GameLogic.MSGtYPE_item, key);
                } else {
                    LStData.GL.showMessage("it's too far", GameLogic.MSGtYPE_warning, null);
                }
                break;

        }
    }
}
