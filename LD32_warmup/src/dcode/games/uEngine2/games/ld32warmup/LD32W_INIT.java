package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class LD32W_INIT {
    public static void main(String[] args) {
        uGameSetup gs = new uGameSetup();
        gs.FPS = 60;
        gs.TPS_logic = 60;
        gs.TPS_MSX = 0;
        gs.TPS_BG = 16;

        gs.debug = true;
        gs.width = 400;
        gs.height = 300;
        gs.scale = 2;

        gs.spriteTableSize = 120;
        gs.enableSpriteWrappers = true;
        gs.postProcCount = 2;
        gs.spriteLayerSize = 120;


        gs.safeName = "ld32warmup";
        gs.screenName = "LD32WUP";
        gs.windowTitle = " Warmup for Ludum Dare 32 ";
        
        PuGameBase gb = new PuGameBase();

        gb.setup = gs;
        gb.initialInputHandler = new InHandler();
        gb.contentInitializer = new PuGameLoader() {
            @Override
            public void loadInitialGameContent() {
                StData.logicTasks.registerBasic(new MainLogic());
            }
        };

        Startup.StartGame(gb);
    }

}