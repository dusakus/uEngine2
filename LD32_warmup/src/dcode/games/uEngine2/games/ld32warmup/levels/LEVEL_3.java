package dcode.games.uEngine2.games.ld32warmup.levels;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.Item;
import dcode.games.uEngine2.games.ld32warmup.LStData;
import dcode.games.uEngine2.games.ld32warmup.WorldObject;

import java.awt.*;

/**
 * Created by dusakus on 13.04.15.
 */
public class LEVEL_3 extends LevelBase {

    private WorldObject[] wos;

    Item key;

    @Override
    public String getTextureId() {
        return "R3";
    }

    @Override
    public void registerSprites(WorldObject[] worldObjects) {
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
        }
    }

    @Override
    public boolean isColorUnlocked(int RED) {
        switch (RED) {
            case 250:
                LStData.currentStatus = 2001;
            default:
                return true;
        }
    }
}
