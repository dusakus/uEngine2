package dcode.games.uEngine2.games.rseqplayeralpha;

import dcode.games.uEngine2.GFX.layers.FillTextureLayer;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_playback.PlaybackLogic;
import dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection.LAYER_actions;
import dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection.LAYER_list;
import dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection.LAYER_selection;
import dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection.MenuLogic;
import dcode.games.uEngine2.games.rseqplayeralpha.other.LAYER_Loading;

import static dcode.games.uEngine2.StData.*;
import static dcode.games.uEngine2.games.rseqplayeralpha.LStData.*;

public class BaseLogic implements ILogicTask {

	boolean isReady = false;


	public BaseLogic() {


		if (currentMode != MODE_INIT) {
			currentMode = MODE_ERROR;
			ERRORCODE = 101;
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
			case MODE_PLAYBACK_PLAY:

				break;
			case MODE_MENU_LIST:

				break;
			case MODE_MENU_MAIN:
				switch (currentStatus) {
					case 0:
						StData.currentGC = GC_menu;
						currentStatus = 1;
						break;
					case 1:
						currentStatus = 2;
						if (true) { //ToDo: check texture loading status
							currentStatus = 3;
						}
						break;
					case 3:
						createMenuContent();
						LStData.GC_menu.currentLT.registerBasic(new MenuLogic());
						currentStatus = 250;
						break;
					case 701:
						//ToDo: load object, transition to playback sc
						break;
					case 808:
						StData.currentGC = GC_menu;
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
				currentGC.currentSC.layers_Background.add(new FillTextureLayer("missing"));
				currentGC.currentSC.layers_Background.add(new LAYER_Loading(40));
				currentStatus = 2;
				currentMode = MODE_LOADBASE;
				break;
			case MODE_CRITERROR:
				StData.LOG.println("CRITICAL ERROR INTERNAL EXIT STATUS: status:" + currentStatus + ", errorcode:" + ERRORCODE);
				gameIsRunning = false;
				break;
			case MODE_ERROR:
				StData.LOG.println("ERROR INTERNAL EXIT STATUS: status:" + currentStatus + ", errorcode:" + ERRORCODE + " msg " + lastMSG);
				gameIsRunning = false;
				break;
			case MODE_SHUTDOWN:
				gameIsRunning = false;
				break;
			case MODE_LOADBASE:
				init_SelContainer();
				init_PlbContainer();

				currentMode = MODE_MENU_MAIN;
				currentStatus = 0;
				break;
			case MODE_PLAYBACK_LOADINGCONTENT:
				if (currentStatus == 10) {
					StData.currentGC = GC_playback;
					GC_playback.currentSC.layers_Foreground.add(new LAYER_Loading(900));
					GC_playback.currentLT.registerBasic(new PlaybackLogic());
					currentStatus = 20;
				}
				break;
		}
	}

	@Override
	public boolean doRepeat() {
		return true;
	}

	private void init_SelContainer() {
	}

	private void init_PlbContainer() {
	}

	private void createMenuContent() {
		LStData.GC_menu.currentSC.layers_Background.add(new FillTextureLayer("MBGbase"));
		LStData.GC_menu.currentSC.layers_Background.add(new LAYER_actions());
		LStData.GC_menu.currentSC.layers_Background.add(new LAYER_list());
		LStData.GC_menu.currentSC.layers_Background.add(new LAYER_selection());

	}
}


