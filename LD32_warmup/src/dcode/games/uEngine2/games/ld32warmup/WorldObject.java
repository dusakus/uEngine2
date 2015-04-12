package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.GFX.sprites.Sprite;

import java.awt.*;

public class WorldObject extends Sprite{
    String texSource = "...";
    int spritePosition = 5;

    public WorldObject(String source, String key, int depth, int XPos, int YPos) {
        texSource = source;
        spritePosition = depth;

        x = XPos;
        y = YPos;

        this.textureKey = key;
    }

    @Override
    public int getX() {
        return x - LStData.GL.room.getCurrentXShift();
    }

    @Override
    public int getY() {
        return y - LStData.GL.room.getCurrentYShift();
    }

    @Override
    public Image getCustomTexture() {
        return null;
    }

    @Override
    public boolean doCustomRender() {
        return false;
    }

    @Override
    public void customRender(Graphics2D G) {

    }
}
