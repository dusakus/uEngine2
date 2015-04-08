package dcode.games.uEngine2.games.ugametoolkit;

import java.io.File;

/**
 * Created by dusakus on 09.02.15.
 */
public class LStData {

    public static final int MODE_SHUTDOWN = -10;                    //Just stop
    public static final int MODE_INIT = 0;                          //init
    public static final int MODE_MENU_MAIN = 10;                    //Show main menu
    public static final int MODE_MENU_INFO = 18;                    //Show player settings
    public static final int MODE_ACTION_LOADASSET = 71;             //load asset from file
    public static final int MODE_ACTION_LOADFILE = 81;              //load file to edit
    public static final int MODE_ACTION_SAVEFILE = 82;              //save modified file
    public static final int MODE_TASK_TILEMAPEDITOR = 110;          // .utm file editor  (uTileMap)
    public static final int MODE_TASK_GRIDEDITOR = 120;                // .ueg file editor  (uEGrid)
    public static final int MODE_TASK_SPRITEEDITOR = 130;            // .ues file editor  (uESprite)
    public static final int MODE_TASK_COLLISIONMAPEDITOR = 140;     // .ucm file editor  (uCollisionMap)
    public static final int MODE_TASK_TRIGGERMAPEDITOR = 150;       // .utg file editor  (uTriggerGrid)
    public static final int MODE_TASK_EVENTEDITOR = 160;            // .ued file editor  (uEventDescriptor)

    public static int currentMode = 0;                              //Represents current app mode

    public static int currentStatus = 0;                            //Represents current app status

    public static File target = null;

}

