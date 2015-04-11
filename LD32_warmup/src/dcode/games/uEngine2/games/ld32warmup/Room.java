package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelBase;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 10.04.15.
 */
public class Room {
    public String texId;

    public BufferedImage dataLayer;

    public WorldObject[] worldObjects;

    public LevelBase level;
    public int levelID;

    public Room(LevelBase l, int lid) {
        level = l;
        levelID = lid;
        texId = l.getTextureId();
        StData.resources.grf.unload("RB");
        StData.resources.grf.unload("RF");
        StData.resources.grf.unload("RD");
    }

    public boolean tryLoadDataLayer() {
        if (StData.resources.grf.isTextureAviable("RD")) {
            dataLayer = StData.resources.grf.getTexture("RD");
            LStData.roomWidth = dataLayer.getWidth();
            LStData.roomHeight = dataLayer.getHeight();
            return true;
        } else return false;
    }

    public void tick(GameLogic gameLogic) {
        level.levelTick(gameLogic);
    }

    public void init() {
        texId = level.getTextureId();
        level.registerSprites(worldObjects);
    }

    public boolean checkWalkable(int targetX, int targetY) {
        return new Color(dataLayer.getRGB(targetX,targetY)).getRed() != 255;
    }


    public class WorldObject {
    }
}
