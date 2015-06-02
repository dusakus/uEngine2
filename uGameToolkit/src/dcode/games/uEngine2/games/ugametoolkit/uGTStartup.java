package dcode.games.uEngine2.games.ugametoolkit;

import dcode.games.uEngine2.*;
import dcode.games.uEngine2.localStorage.configStorage.CContainer;

import java.io.File;

/**
 * Created by dusakus on 24.03.15.
 */
public class uGTStartup {
    public static void main(String[] args) {
        uGameSetup gs = new uGameSetup();
        gs.FPS = 30;
        gs.TPS_logic = 60;
        gs.TPS_MSX = 0;
        gs.TPS_BG = 30;

        gs.debug = true;
        gs.width = 520;
        gs.height = 300;
        gs.scale = 2;

        gs.spriteTableSize = 16;
        gs.enableSpriteWrappers = true;
        gs.postProcCount = 0;
        gs.spriteLayerSize = 16;


        gs.safeName = "ugametoolkit";
        gs.screenName = "uGameToolkit";
        gs.windowTitle = " DCode uE2 game toolkit 0.1 ";

        PuGameBase gb = new PuGameBase();

        gb.setup = gs;
        gb.initialInputHandler = new InHandler();
        gb.contentInitializer = new Initializer();

        Startup.StartGame(gb);
    }

    private static class Initializer extends dcode.games.uEngine2.PuGameLoader {
        @Override
        public void loadInitialGameContent() {
            StData.currentGC = new GameContainer();
            StData.logicTasks.registerBasic(new BaseLogic());




            CContainer c = new CContainer(new File(StData.gameStorageDirectory, "testConfig.ugc"));


            c.saveAs(new File(StData.gameStorageDirectory, "testConfig.ugc"));
        }
    }
}
