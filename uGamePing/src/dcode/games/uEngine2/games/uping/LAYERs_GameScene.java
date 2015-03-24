package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * Created by dusakus on 24.03.15.
 */
public class LAYERs_GameScene {


	private class Area extends AreaDataContainer {

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
