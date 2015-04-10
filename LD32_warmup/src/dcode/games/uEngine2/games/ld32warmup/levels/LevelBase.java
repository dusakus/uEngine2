package dcode.games.uEngine2.games.ld32warmup.levels;

import dcode.games.uEngine2.games.ld32warmup.GameLogic;
import dcode.games.uEngine2.games.ld32warmup.Room;

import java.awt.*;

/**
 * Created by dusakus on 10.04.15.
 */
public abstract class LevelBase {

    public abstract String getTextureId();

    public void levelTick(GameLogic gl) {
    }

    public abstract void registerSprites(Room.WorldObject[] worldObjects);

    public Point getInitialPlayerLocation() {
        return new Point(24, 200);
    }

}
