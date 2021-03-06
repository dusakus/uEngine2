package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.SFX.tslib.Sound;

import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 09.02.15.
 */
public class LStData {

	public static final int MODE_SHUTDOWN = -10;                    //Just stop
	public static final int MODE_INIT = 0;                          //init
	public static final int MODE_MENU_MAIN = 10;                    //Show main menu
	public static final int MODE_MENU_INFO = 18;                    //Show player settings
	public static final int MODE_GAME_PLAY = 20;                    //...
	public static final int MODE_GAME_PAUSE = 21;                   //...
	public static final int MODE_GAME_LOADINGCONTENT = 29;          //Load sequence content

	public static int currentMode = 0;                              //Represents current app mode

	public static int currentStatus = 0;                            //Represents current app status

	public static int currAreaX = 3, currAreaY = 2;

	public static LAYERs_GameScene gameworld;
	public static ballMan ball;
	public static BatMan bats;

	public static Sound batHit;
	public static Sound wallHit;

	public static boolean enableCollisionOverlay = false;
	public static BufferedImage collisionMap;
}

