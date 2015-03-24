package dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.rseqplayeralpha.IIp;
import dcode.games.uEngine2.games.rseqplayeralpha.LStData;
import dcode.games.uEngine2.games.rseqplayeralpha.other.plbContainer;

import java.io.File;
import java.util.ArrayList;

import static dcode.games.uEngine2.games.rseqplayeralpha.LStData.*;

/**
 * Created by dusakus on 09.02.15.
 */
public class MenuLogic implements ILogicTask, IIp {

	private int selectionSwitchDelay = 2;
	private boolean confirmReceived = false;

	private ArrayList<File> containers;

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void perform() {
		//return if not in the menu
		if (currentMode < 10 || currentMode > 19) {
			StData.LOG.println("MenuLogic object is running while not in menu", "E6S");
			return;
		}

		//bind to input on initvideos
		if (currentStatus == 250) {
			StData.LOG.println("MenuLogic entry point reached");
			LStData.currentInputProcessor = this;
			currentStatus = 300;
		}

		//decrease button delay
		if (selectionSwitchDelay > 0) selectionSwitchDelay--;

		//Main menu processing path
		if (currentMode == MODE_MENU_MAIN) {
			//process confirmation
			if (confirmReceived && currentStatus < 305 && currentStatus > 300) {
				currentStatus = currentStatus + 10;
			}
			if (currentStatus > 310 && currentStatus < 320) {
				switch (currentStatus - 310) {
					case 1:
						currentMode = MODE_MENU_LIST;
						currentStatus = 10;
						break;
					case 2:
						currentMode = MODE_MENU_SETTINGS;
						currentStatus = 10;
						break;
					case 3:
						currentMode = MODE_MENU_INFO;
						currentStatus = 10;
						break;
					case 4:
						currentMode = MODE_SHUTDOWN;
						currentStatus = 0;
						break;
				}
			}
		} else if (currentMode == MODE_MENU_LIST) {
			switch (currentStatus) {
				case 10:
					prepareFileList();
					confirmReceived = false;
					currentStatus = 11;
					containerList = new ArrayList<>();
					break;
				case 11:
					if (containers.isEmpty()) {
						currentStatus = 12;
						return;
					}
					processNextFile();

					break;
				case 12:
					StData.LOG.println("List of titles");
					for (plbContainer pC : containerList) {
						StData.LOG.println(pC.title);
					}
					currentStatus = 101;

			}
			if (currentStatus > 200 && currentStatus < 250) {
				StData.LOG.println("Selection made, selection id is " + (currentStatus - 200));
				currentTargetContainer = containerList.get(currentStatus - 201);
				containerList = null;
				currentTargetContainer.requestExtendedLoad();
				currentMode = MODE_PLAYBACK_LOADINGCONTENT;
				currentStatus = 10;
			}
		}
	}

	private void processNextFile() {
		File f = containers.get(0);
		containers.remove(f);
		plbContainer pC = new plbContainer(f);
		containerList.add(pC);
	}

	private void prepareFileList() {
		StData.LOG.println("Starting to build file list...", "N");
		containers = new ArrayList<>();
		File dir = new File(StData.gameStorageDirectory, "uVideos");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File[] fl = dir.listFiles();
		if (fl.length < 1) return;   //Break for empty list
		for (int i = 0; i < fl.length; i++) {
			containers.add(fl[i]);
			StData.LOG.println("toCheck: " + fl[i].getName());
		}

		for (int i = containers.size() - 1; i >= 0; i--) {
			if (!containers.get(i).isDirectory()) {
				containers.remove(i);
			}
		}
		StData.LOG.println("final list: ");
		for (File f : containers) {
			StData.LOG.println("=] " + f.getName());
		}
		LStData.containers = containers;
		StData.LOG.println("Loading file list finished");
	}

	@Override
	public boolean doRepeat() {
		return true;
	}

	@Override
	public void upPressed() {
		switch (currentMode) {
			case MODE_MENU_MAIN:
				if (selectionSwitchDelay == 0 && currentStatus > 301) {
					currentStatus--;
					selectionSwitchDelay = 2;
				}
				break;
			case MODE_MENU_LIST:
				if (selectionSwitchDelay == 0 && currentStatus > 101) {
					currentStatus--;
					selectionSwitchDelay = 2;
				}
				break;
		}
	}

	@Override
	public void downPressed() {
		switch (currentMode) {
			case MODE_MENU_MAIN:
				if (selectionSwitchDelay == 0 && currentStatus < 304) {
					currentStatus++;
					selectionSwitchDelay = 2;
				}
				break;
			case MODE_MENU_LIST:
				if (selectionSwitchDelay == 0 && currentStatus < 100 + LStData.containerList.size()) { //todo: list size
					currentStatus++;
					selectionSwitchDelay = 2;
				}
				break;
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
			case MODE_MENU_MAIN:
				if (currentStatus < 305 && currentStatus > 300) {
					confirmReceived = true;
					StData.LOG.println("Selected: " + (currentStatus - 300), "D");
				}
				break;
			case MODE_MENU_LIST:
				currentStatus += 100;
				break;
		}
	}
}
