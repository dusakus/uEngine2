package dcode.games.uEngine2.games.uping;

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
	int lastAreaX = 3, lastAreaY = 2;
	private int mStep = 0;


	public Area createArea(String str, int i, int i1, LAYERs_GameScene worldMan) {
		return new Area(str, i, i1, worldMan);
	}

	public void update() {
		if (!moving) {
			areas[lastAreaX][lastAreaY].old = true;
			areas[lastAreaX][lastAreaY].updateDir();
			areas[LStData.currAreaX][LStData.currAreaY].enable();
			areas[LStData.currAreaX][LStData.currAreaY].updateDir();
			StData.LOG.println("Moving to Area [" + LStData.currAreaX + "," + LStData.currAreaY + "]");
			moving = true;
		} else {
			shiftByX = (mStep * 2 * (lastAreaX - LStData.currAreaX));
			shiftByY = ((int) (mStep * 1.2 * (lastAreaY - LStData.currAreaY)));
		}
		mStep++;
		if (mStep == 1) {
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
		return (BufferedImage) StData.resources.grf.getPartTexture(areas[3][2].areaID + "D", (LStData.currAreaX - 1) * 400, (LStData.currAreaY - 1) * 240, 400, 240);
	}


	public class Area extends AreaDataContainer {

		public boolean old;
		public int moveDir = -1;   // 1:  /\   2:  >  3:  \/   4:  <
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
			old = false;
			StData.currentGC.currentSC.layers_Background.add(lb);
			StData.currentGC.currentSC.layers_Foreground.add(lt);
			StData.currentGC.currentSC.layers_Overlay.add(ls);
		}

		void updateDir() {
			if (LStData.currAreaX > areaCoordX) moveDir = 4;
			if (LStData.currAreaX < areaCoordX) moveDir = 2;
			if (LStData.currAreaY > areaCoordY) moveDir = 1;
			if (LStData.currAreaY < areaCoordY) moveDir = 3;
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

		public Image getTexture(String id) {
			return StData.resources.grf.getPartTexture(id, (areaCoordX - 1) * 400, (areaCoordY - 1) * 240, 400, 240);
		}

	}

	private class LayerBottom extends AreaDataContainer implements ILayer {


		public LayerBottom(String aID, int acX, int acY, LAYERs_GameScene gsc) {
			super(aID, acX, acY, gsc);
			//StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_LOW.png", aID + "B"));
		}

		@Override
		public void draw(Graphics2D G2D) {
			if (gameScene.moving) {

				if (!LStData.gameworld.areas[areaCoordX][areaCoordY].old) {
					G2D.drawImage(getTexture(areaID + "B"), gameScene.shiftByX, gameScene.shiftByY, null);
				} else {
					switch (LStData.gameworld.areas[areaCoordX][areaCoordY].moveDir) {
						case 1:
							G2D.drawImage(getTexture(areaID + "B"), gameScene.shiftByX, gameScene.shiftByY + 240, null);
							break;
						case 2:
							G2D.drawImage(getTexture(areaID + "B"), gameScene.shiftByX - 400, gameScene.shiftByY, null);
							break;
						case 3:
							G2D.drawImage(getTexture(areaID + "B"), gameScene.shiftByX, gameScene.shiftByY - 240, null);
							break;
						case 4:
							G2D.drawImage(getTexture(areaID + "B"), gameScene.shiftByX + 400, gameScene.shiftByY, null);
							break;
					}
				}

			} else {
				G2D.drawImage(getTexture(areaID + "B"), 0, 0, null);
			}
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
			//StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_HIGH.png", aID + "T"));
		}

		@Override
		public void draw(Graphics2D G2D) {
	        /*if (gameScene.moving) {
                if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY == areaCoordY)
                    G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0 - gameScene.shiftByX, 0 - gameScene.shiftByY, null);
                else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY > areaCoordY)
                    G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, gameScene.shiftByY - 240, null);
                else if (gameScene.lastAreaX == areaCoordX && gameScene.lastAreaY < areaCoordY)
                    G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, gameScene.shiftByY + 240, null);
                else if (gameScene.lastAreaX > areaCoordX && gameScene.lastAreaY == areaCoordY)
                    G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), gameScene.shiftByX - 400, 0, null);
                else if (gameScene.lastAreaX < areaCoordX && gameScene.lastAreaY == areaCoordY)
                    G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), gameScene.shiftByX + 400, 0, null);
            } else G2D.drawImage(StData.resources.grf.getScaledTexture(areaID + "T", 400, 240), 0, 0, null);
            */
			G2D.drawImage(getTexture(areaID + "T"), 0, 0, null);

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
			//StData.currentGC.currentBGT.LPTasks.add(new LoadBasicTexture("levels/level_" + aID + "_DATA.png", aID + "D"));
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
