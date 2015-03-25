package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 24.03.15.
 */
public class LAYERs_GameScene {

	public Area[][] areas = new Area[33][33];
	public int shiftByX = 0, shiftByY = 0;
	public boolean moving = false;
	int lastAreaX = 16, lastAreaY = 16;
	private int mStep = 0;


	public Area createArea(String str, int i, int i1, LAYERs_GameScene worldMan) {
		return new Area(str, i, i1, worldMan);
	}

	public void update() {
		if (!moving) {
			areas[lastAreaX][lastAreaY].old = true;
			areas[LStData.currAreaX][LStData.currAreaY].enable();
			StData.LOG.println("Moving to Area [" + LStData.currAreaX + "," + LStData.currAreaY + "]");
			moving = true;
		} else {
			shiftByX = (mStep * 4 * (LStData.currAreaX - lastAreaX));
			shiftByY = ((int) (mStep * 2.4 * (LStData.currAreaY - lastAreaY)));
		}
		mStep++;
		if (mStep == 100) {
			areas[lastAreaX][lastAreaY].disable();
			StData.LOG.println("Moving done [" + LStData.currAreaX + "," + LStData.currAreaY + "]");
			lastAreaX = LStData.currAreaX;
			lastAreaY = LStData.currAreaY;
			LStData.currentStatus = 301;
			shiftByX = 0;
			shiftByY = 0;
			mStep = 0;
			moving = false;
		}
	}

	public BufferedImage getDataImage() {
		return StData.resources.grf.getTexture(areas[lastAreaX][lastAreaY].areaID + "D");
	}


	public class Area extends AreaDataContainer {

		public boolean old;
		private LayerBottom lb;
		private LayerTop lt;
		private LayerData ls;

		public Area(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			super(aID, acX, acY, gsc);
			lb = new LayerBottom(aID, acX, acY, gsc);
			lt = new LayerTop(aID, acX, acY, gsc);
			ls = new LayerData(aID, acX, acY, gsc);
		}

		public void disable() {
			StData.currentGC.currentSC.layers_Background.remove(lb);
			StData.currentGC.currentSC.layers_Foreground.remove(lt);
			StData.currentGC.currentSC.layers_Overlay.remove(ls);
		}

		public void enable() {
			StData.currentGC.currentSC.layers_Background.add(lb);
			StData.currentGC.currentSC.layers_Foreground.add(lt);
			StData.currentGC.currentSC.layers_Overlay.add(ls);
		}
	}

	private class AreaDataContainer {

		String areaID = "ARmain";
		int areaCoordX = 0;
		int areaCoordY = 0;
		LAYERs_GameScene gameScene;

		public AreaDataContainer(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			areaID = aID;
			areaCoordX = acX;
			areaCoordY = acY;
			gameScene = gsc;
		}

	}

	private class LayerBottom extends AreaDataContainer implements ILayer {


		public LayerBottom(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			super(aID, acX, acY, gsc);
			StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_LOW.png", aID + "B"));
		}

		@Override
		public void draw(Graphics2D G2D) {
			if (gameScene.moving) {
				if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0 - gameScene.shiftByX, 0 - gameScene.shiftByY, null);
				else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY > areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0, 0 - gameScene.shiftByY - 240, null);
				else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY < areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0, 0 - gameScene.shiftByY + 240, null);
				else if (gameScene.lastAreaX > areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0 - gameScene.shiftByX - 400, 0, null);
				else if (gameScene.lastAreaX < areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0 - gameScene.shiftByX + 400, 0, null);
			} else G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "B", 400, 240), 0, 0, null);
		}

		@Override
		public boolean removeMe() {
			return false;
		}

		@Override
		public boolean renderMe() {
			return LStData.currentMode == LStData.MODE_GAME_PLAY;
		}
	}

	private class LayerTop extends AreaDataContainer implements ILayer {

		public LayerTop(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			super(aID, acX, acY, gsc);
			StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_HIGH.png", aID + "T"));
		}

		@Override
		public void draw(Graphics2D G2D) {
			if (gameScene.moving) {
				if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0 - gameScene.shiftByX, 0 - gameScene.shiftByY, null);
				else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY > areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, 0 - gameScene.shiftByY - 240, null);
				else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY < areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, 0 - gameScene.shiftByY + 240, null);
				else if (gameScene.lastAreaX > areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0 - gameScene.shiftByX - 400, 0, null);
				else if (gameScene.lastAreaX < areaCoordX && gameScene.lastAreaY == areaCoordY)
					G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0 - gameScene.shiftByX + 400, 0, null);
			} else G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, 0, null);
		}

		@Override
		public boolean removeMe() {
			return false;
		}

		@Override
		public boolean renderMe() {
			return LStData.currentMode == LStData.MODE_GAME_PLAY;
		}
	}

	private class LayerData extends AreaDataContainer implements ILayer {

		public LayerData(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			super(aID, acX, acY, gsc);
			StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_DATA.png", aID + "D"));
		}

		@Override
		public void draw(Graphics2D G2D) {
			//G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "D", 400, 240), 0, 0, null);

		}

		@Override
		public boolean removeMe() {
			return false;
		}

		@Override
		public boolean renderMe() {
			return LStData.enableCollisionOverlay;
		}
	}

}
