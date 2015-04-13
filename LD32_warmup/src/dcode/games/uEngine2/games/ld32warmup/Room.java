package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
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

	public WorldObject[] worldObjects = new WorldObject[100];

	public LevelBase level;
	public int levelID;

	public Room(LevelBase l, int lid) {
		level = l;
		levelID = lid;
		texId = l.getTextureId();
		level.registerSprites(worldObjects);
	}

	public boolean tryLoadDataLayer() {
		if (StData.resources.grf.isTextureAviable("RD" + levelID)) {
			dataLayer = StData.resources.grf.getTexture("RD" + levelID);
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
	}

	public boolean checkWalkable(int targetX, int targetY) {
		if (new Color(dataLayer.getRGB(targetX, targetY)).getRed() == 255) return false;
		if (new Color(dataLayer.getRGB(targetX, targetY)).getRed() == 0) return true;
		return level.isColorUnlocked(new Color(dataLayer.getRGB(targetX, targetY)).getRed());
	}

	public void loadWorldObjects() {
		for (WorldObject wo : worldObjects) {
			if (wo != null) {
				StData.generalBGT.LPTasks.add(new LoadBasicTexture("sprites/R/" + levelID + "/" + wo.texSource, wo.getTextureKey()));
			}
		}
	}

	public void unloadRoom() {
		for (WorldObject wo : worldObjects) {
			if (wo != null) {
				StData.resources.grf.unload(wo.getTextureKey());
				LStData.GL.inGameSC.sprites_middle[wo.spritePosition] = -1;
			}
		}

		StData.resources.grf.unload("RB"+levelID);
		StData.resources.grf.unload("RF"+levelID);
		StData.resources.grf.unload("RD"+levelID);
	}

	public void insertWorldObjects(ScreenContent sc) {
		for (int i = 0; i < 100; i++) {
			WorldObject wo = worldObjects[i];
			if (wo != null) {
				sc.sprites[i + 10] = wo;
				sc.sprites_middle[wo.spritePosition] = i + 10;
			}
		}
	}


	public int getSpritezindex(int inRoomX, int inRoomY) {
		return new Color(dataLayer.getRGB(inRoomX, inRoomY)).getGreen() - 100;
	}

	public void checkRClick(Player p, int rclickX, int rclickY) {
		int targetX = p.inRoomX + rclickX - p.getX() - 32;
		int targetY = p.inRoomY + rclickY - p.getY() - 60;
		Color c = new Color(dataLayer.getRGB(targetX, targetY));
		Color playerpColor = new Color(dataLayer.getRGB(p.inRoomX, p.inRoomY));

		if (c.getBlue() - 100 > 0) {
			level.onObjectClicked(c.getBlue() - 100, playerpColor);
		}
	}

	public int getCurrentXShift() {
		return LStData.GL.player.inRoomX - LStData.GL.player.getX() - 32;
	}

	public int getCurrentYShift() {
		return LStData.GL.player.inRoomY - LStData.GL.player.getY() - 60;
	}

	public void reLoadWorldObjects(ScreenContent sc, WorldObject[] wos) {
		StData.LOG.println("[ROOM] reloading sprites");
		for (int i = 0; i < 100; i++) {
			worldObjects[i] = wos[i];
		}
		loadWorldObjects();
		insertWorldObjects(sc);
	}
}

