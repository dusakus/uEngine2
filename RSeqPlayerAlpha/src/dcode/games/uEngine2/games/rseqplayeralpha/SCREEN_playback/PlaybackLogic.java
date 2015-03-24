package dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_playback;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.rseqplayeralpha.IIp;
import dcode.games.uEngine2.games.rseqplayeralpha.LStData;
import dcode.games.uEngine2.games.rseqplayeralpha.other.plbContainer;

import static dcode.games.uEngine2.games.rseqplayeralpha.LStData.*;

/**
 * Created by dusakus on 04.03.15.
 */
public class PlaybackLogic implements IIp, ILogicTask {

	private plbContainer Container;

	private ScriptProcessor scriptProcessor;

	private int selectionSwitchDelay = 2;
	private boolean confirmReceived = false;
	private int playbackStatus = 0;

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void perform() {
		//return if not in the sequence
		if (currentMode < 20 || currentMode > 29) {
			StData.LOG.println("PlaybackLogic object is running while not in sequence", "E6S");
			return;
		}

		//decrease button delay
		if (selectionSwitchDelay > 0) selectionSwitchDelay--;

		//Main menu processing path
		if (currentMode == MODE_PLAYBACK_PLAY) {
			switch (currentStatus) {
				case 10:
					StData.LOG.println("starting playback");
					currentStatus = 100;
					break;
				case 100:
					scriptProcessor.TICK();
					break;
			}
		} else if (currentMode == MODE_PLAYBACK_PAUSE) {
			//pause tick
		} else if (currentMode == MODE_PLAYBACK_LOADINGCONTENT) {
			//loading

			switch (currentStatus) {
				case 20:
					StData.LOG.println("PlaybackLogic entry point reached", "D");
					LStData.currentInputProcessor = this;
					currentStatus++;
					break;
				case 21:
					Container = LStData.currentTargetContainer;
					StData.LOG.println("Preparing container " + Container.title + "...", "N");
					currentStatus++;
					break;
				case 22:
					Container.loadExtendedContent();
					scriptProcessor = new ScriptProcessor(Container);
					currentStatus = 10;
					currentMode = MODE_PLAYBACK_PLAY;
					break;
			}
		}
	}

	@Override
	public boolean doRepeat() {
		return true;
	}

	@Override
	public void upPressed() {
		if (currentMode == MODE_PLAYBACK_PAUSE) {
			if (currentStatus > 101) {
				currentStatus--;
			}
		}
	}

	@Override
	public void downPressed() {
		if (currentMode == MODE_PLAYBACK_PAUSE) {
			if (currentStatus < 102) {
				currentStatus++;
			}
		}
	}

	@Override
	public void leftPressed() {

	}

	@Override
	public void rightPressed() {

	}

	@Override
	public void confirmPressed() {
		switch (currentMode) {
			case MODE_PLAYBACK_PLAY:
				playbackStatus = currentStatus;
				currentMode = MODE_PLAYBACK_PAUSE;
				currentStatus = 101;

				break;
			case MODE_PLAYBACK_PAUSE:
				switch (currentStatus) {
					case 101:
						currentMode = MODE_PLAYBACK_PLAY;
						currentStatus = playbackStatus;
						break;
					case 102:
						currentMode = MODE_MENU_MAIN;
						currentStatus = 808;
						break;
				}
				break;
		}
	}
}
