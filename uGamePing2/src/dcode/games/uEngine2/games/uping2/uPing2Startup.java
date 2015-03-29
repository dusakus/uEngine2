package dcode.games.uEngine2.games.uping2;

import dcode.games.uEngine2.PuGameBase;
import dcode.games.uEngine2.Startup;
import dcode.games.uEngine2.uGameSetup;

/**
 * Created by dusakus on 29.03.15.
 */
public class uPing2Startup {
    public static void main(String[] args) {
        uGameSetup gs = new uGameSetup();
        gs.FPS = 30;
        gs.TPS_logic = 120;
        gs.TPS_MSX = 0;
        gs.TPS_BG = 30;

        gs.debug = true;
        gs.width = 400;
        gs.height = 240;
        gs.scale = 2;

        gs.spriteTableSize = 4;
        gs.enableSpriteWrappers = true;
        gs.postProcCount = 1;
        gs.spriteLayerSize = 2;


        gs.safeName = "uping2";
        gs.screenName = "uGamePing2";
        gs.windowTitle = " ]]PING[[ ";

        PuGameBase gb = new PuGameBase();

        gb.setup = gs;
        gb.initialInputHandler = new InHandler();
        gb.contentInitializer = new Initializer();

        Startup.StartGame(gb);

    }

    private static class Initializer extends dcode.games.uEngine2.PuGameLoader {
        @Override
        public void loadInitialGameContent() {

        }
    }
}
