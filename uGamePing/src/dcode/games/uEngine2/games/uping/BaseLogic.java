package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.layers.ClearColorLayer;
import dcode.games.uEngine2.GFX.postproc.PP_scaleblur;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;

import java.awt.*;

import static dcode.games.uEngine2.StData.*;
import static dcode.games.uEngine2.games.uping.LStData.*;

public class BaseLogic implements ILogicTask {

	boolean isReady = false;


	public BaseLogic() {

		if (currentMode != MODE_INIT) {
			LOG.println("BaseLogic initialized outside of INIT phase", "E5");
		} else {
			isReady = true;
		}
	}

	@Override
	public boolean isReady() {
		return isReady;
	}

	@Override
	public void perform() {
		switch (currentMode) {
			case MODE_GAME_PLAY:
				switch (currentStatus) {
					case 101:
						StData.currentGC.currentSC.layers_Background.add(new ClearColorLayer(Color.BLACK));
						LStData.enableCollisionOverlay = true;
						LStData.gameworld = new LAYERs_GameScene();
						LStData.bats = new BatMan();
						LStData.ball = new ballMan();
						StData.currentGC.currentBGT.HPTasks.add(new BGT_collisionUpdate());
						StData.currentGC.currentSC.layers_Center.add(bats);

						StData.currentGC.currentSC.sprites[1] = ball;
						StData.currentGC.currentSC.sprites_middle[1] = 1;
						StData.currentGC.currentSC.postProcessors[1] = new PP_scaleblur(1.5F);


						new ReallyCrappyWorldConstructionObjectBecauseImLazy().writeWorldData(gameworld);

						gameworld.areas[currAreaX][currAreaY].enable();

						currentStatus = 301;
						break;
					case 301:
						doGameTick();
						break;
					case 302:
						gameworld.update();
						break;

				}
				break;
			case MODE_MENU_MAIN:
				switch (currentStatus) {
					case 0:
						currentStatus = 1;

						StData.generalBGT.LPTasks.add(new LoadBasicTexture("levels/level_W1_DATA.png", "lv1D"));
						StData.generalBGT.LPTasks.add(new LoadBasicTexture("levels/level_W1_LOW.png", "lv1B"));
						StData.generalBGT.LPTasks.add(new LoadBasicTexture("levels/level_W1_HIGH.png", "lv1T"));
						break;
					case 1:
						currentStatus = 2;
						if (true) { //ToDo: check texture loading status
							currentStatus = 3;
						}
						break;
					case 3:
						currentStatus = 250;
						break;
					case 250:
						currentStatus = 101;
						currentMode = MODE_GAME_PLAY;
						break;
					case 701:
						break;
					case 808:
						currentStatus++;
						break;
					case 809:
						currentStatus = 250;
						break;
				}
				break;
			case MODE_INIT:
				LOG.println("Beginning rSeqPlayer initialization");
				LOG.println("QuickStarting loading screen");
				currentGC.currentSC.layers_Overlay.add(new LAYER_Loading(301));
				currentStatus = 0;
				currentMode = MODE_MENU_MAIN;
				break;
			case MODE_SHUTDOWN:
				gameIsRunning = false;
				break;
		}
	}

	private void doGameTick() {
		LStData.bats.tick();
		LStData.ball.tick();
	}

	@Override
	public boolean doRepeat() {
		return true;
	}
}


