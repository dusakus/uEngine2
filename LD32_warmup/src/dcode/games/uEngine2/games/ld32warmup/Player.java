package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.GFX.sprites.Sprite;
import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class Player extends Sprite {

    public int animID = 1;
    public int direction = 0;

    private int lastX = 0, lastY = 0;
    public int inRoomX = 0, inRoomY = 0;
    public int targetX = 0, targetY = 0;

    GameLogic gl;
    private int animDelay = 4;

    private int sPos = 2;

    public Player(GameLogic gameLogic) {
        gl = gameLogic;
    }


    @Override
    public Image getCustomTexture() {
        if (animDelay == 0) {
            if (lastX != inRoomX || lastY != inRoomY) {
                animID++;
                if (lastX > inRoomX) direction = 0;
                if (lastX < inRoomX) direction = 1;
                if (animID > 4) animID = 1;
                lastX = inRoomX;
                lastY = inRoomY;
                animDelay = 6;
            }
        } else animDelay--;
        if(LStData.GL.currentItem.modifyPlayerSkin)return StData.resources.grf.getTexture("pla" + LStData.GL.currentItem.playerModifier + (animID + (direction * 4)));
        return StData.resources.grf.getTexture("pla" + (animID + (direction * 4)));
    }

    @Override
    public boolean doCustomRender() {
        return false;
    }

    @Override
    public void customRender(Graphics2D G) {

    }

    public void requestTextures() {
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL0.png", "pla1"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL1.png", "pla2"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL2.png", "pla3"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerL3.png", "pla4"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR0.png", "pla5"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR1.png", "pla6"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR2.png", "pla7"));
        StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/P/playerR3.png", "pla8"));
    }

    boolean delayY = false;

    public void checkMove(ScreenContent sc) {

        sc.sprites_middle[0] = 2;

        if (targetX > inRoomX && gl.room.checkWalkable(inRoomX + 1, inRoomY)) inRoomX++;
        if (targetX < inRoomX && gl.room.checkWalkable(inRoomX - 1, inRoomY)) inRoomX--;
        if (delayY) {
            delayY = false;
        } else {
            delayY = true;
            if (targetY > inRoomY && gl.room.checkWalkable(inRoomX, inRoomY + 1)) inRoomY++;
            if (targetY < inRoomY && gl.room.checkWalkable(inRoomX, inRoomY - 1)) inRoomY--;
        }

        if (inRoomX < 200) x = inRoomX;
        else if (inRoomX > LStData.roomWidth - 200) x = inRoomX + 400 - LStData.roomWidth;
        else x = 200;

        if (inRoomY < 150) y = inRoomY;
        else if (inRoomY > LStData.roomHeight - 150) y = inRoomY + 300 - LStData.roomHeight;
        else y = 150;

        //correct the coords for rendering;

        x = x - 32;
        y = y - 60;

        //set sprite position in sprite table
        gl.inGameSC.sprites_middle[sPos] = -1;
        this.sPos = gl.room.getSpritezindex(inRoomX, inRoomY);
        gl.inGameSC.sprites_middle[sPos] = 2;


    }

    public void checkTarget() {
        if (targetX > LStData.roomWidth || targetX < 0 || targetY > LStData.roomHeight || targetY < 0 || !gl.room.checkWalkable(targetX, targetY)) {
            targetX = inRoomX;
            targetY = inRoomY;
            gl.showMessage("I can't go there", GameLogic.MSGtYPE_warning, null);
        }
    }

    public void updateTarget(int x, int y) {

        targetX = inRoomX + x - this.x - 32;
        targetY = inRoomY + y - this.y - 60;

        StData.LOG.println("[PLAYER] updating target to " + targetX + " : " + targetY);
    }
}
