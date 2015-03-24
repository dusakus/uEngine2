package dcode.games.uEngine2.games.rseqplayeralpha;

import dcode.games.uEngine2.GameContainer;
import dcode.games.uEngine2.games.rseqplayeralpha.other.plbContainer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dusakus on 09.02.15.
 */
public class LStData {

	public static final int MODE_SHUTDOWN = -10;                  //sthap, just sthap
	public static final int MODE_CRITERROR = -2;                  //unrecoverable error
	public static final int MODE_ERROR = -1;                      //probably recoverable error
	public static final int MODE_INIT = 0;                        //init
	public static final int MODE_LOADBASE = 1;                    //Load base app content (settings, font, gui icons, colors)
	public static final int MODE_MENU_MAIN = 10;                  //Show main menu
	public static final int MODE_MENU_LIST = 11;                  //Show list of sequences found
	public static final int MODE_MENU_CONFIRM = 12;               //Show info, confirm starting a sequence
	public static final int MODE_MENU_SETTINGS = 15;              //Show player settings
	public static final int MODE_MENU_INFO = 18;                  //Show player settings
	public static final int MODE_MENU_LOADINGDESCRIPTOR = 19;     //Load sequence descriptor
	public static final int MODE_PLAYBACK_PLAY = 20;              //...
	public static final int MODE_PLAYBACK_PAUSE = 21;             //...
	public static final int MODE_PLAYBACK_LOADINGCONTENT = 29;    //Load sequence content

	public static int currentMode = 0;                      //Represents current app mode

	public static int currentStatus = 0;                    //Represents current app status

	public static int ERRORCODE = 0; // Value other than 0 represents an error id, will be used someday...

	public static ArrayList<File> containers = new ArrayList<>();

	public static GameContainer GC_menu;
	public static GameContainer GC_playback;

	public static IIp currentInputProcessor;
	public static ArrayList<plbContainer> containerList;
	public static plbContainer currentTargetContainer;
}

