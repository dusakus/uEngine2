package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.GameContainer;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by dusakus on 09.02.15.
 */
public class LStData {

    public static final int MODE_SHUTDOWN = -10;                    //Just stop
    public static final int MODE_INIT = 0;                          //init
    public static final int MODE_MENU_MAIN = 10;                    //Show main menu
    public static final int MODE_GAME_PLAY = 100;                   //play teh game

    public static int currentMode = 0;                              //Represents current app mode

    public static int currentStatus = 0;                            //Represents current app status

    public static int roomWidth = 400, roomHeight = 300;

    public static boolean newRightClick = false;
    public static int RclickX = 10, RclickY = 10;

    public static GameLogic GL;

    public static BufferedImage StoredFrame;
}

