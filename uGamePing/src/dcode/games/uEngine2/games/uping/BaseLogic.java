package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;

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
						LStData.enableCollisionOverlay = true;
						LStData.gameworld = new LAYERs_GameScene();
						LStData.bats = new BatMan();
						LStData.ball = new ballMan();
						StData.currentGC.currentBGT.HPTasks.add(new BGT_collisionUpdate());
						StData.currentGC.currentSC.layers_Center.add(bats);

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
				currentGC.currentSC.layers_Background.add(new LAYER_Loading(301));
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


