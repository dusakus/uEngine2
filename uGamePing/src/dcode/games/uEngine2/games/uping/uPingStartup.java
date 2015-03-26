package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.*;

/**
 * Created by dusakus on 24.03.15.
 * <p/>
 * This project may (or may not) be my commission for miniLD #58
 */
public class uPingStartup {
	public static void main(String[] args) {
		uGameSetup gs = new uGameSetup();
		gs.FPS = 60;
		gs.TPS_logic = 100;
		gs.TPS_MSX = 0;
		gs.TPS_BG = 5;

		gs.debug = true;
		gs.width = 400;
		gs.height = 240;
		gs.scale = 2;

		gs.spriteTableSize = 10;
		gs.enableSpriteWrappers = false;
		gs.postProcCount = 0;
		gs.spriteLayerSize = 4;


		gs.safeName = "uping";
		gs.screenName = "uGamePing";
		gs.windowTitle = " ]PING[ ";

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
		}
	}
}
