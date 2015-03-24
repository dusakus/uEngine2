package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.LOGIC.ILogicTask;

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
				currentGC.currentSC.layers_Background.add(new LAYER_Loading(40));
				currentStatus = 2;
				currentMode = MODE_MENU_MAIN;
				break;
			case MODE_SHUTDOWN:
				gameIsRunning = false;
				break;
		}
	}

	@Override
	public boolean doRepeat() {
		return true;
	}
}


