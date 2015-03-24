package dcode.games.uEngine2.games.rseqplayeralpha;

import dcode.games.uEngine2.*;

public class playerStartup {
	public static void main(String[] args) {
		uGameSetup gs = new uGameSetup();
		gs.FPS = 60;
		gs.TPS_logic = 24;
		gs.TPS_MSX = 128;
		gs.TPS_BG = 16;

		gs.debug = true;
		gs.width = 400;
		gs.height = 240;
		gs.scale = 2;

		gs.safeName = "rseqplayeralpha";
		gs.screenName = "RSequencePlayerAlpha";
		gs.windowTitle = "\u2284-code sequence player Alpha";

		PuGameBase gb = new PuGameBase();

		gb.setup = gs;
		gb.initialInputHandler = new InputHandler();
		gb.contentInitializer = new INIT();

		Startup.StartGame(gb);
	}

	private static class INIT extends dcode.games.uEngine2.PuGameLoader {
		@Override
		public void loadInitialGameContent() {
			LStData.GC_menu = new GameContainer();
			LStData.GC_playback = new GameContainer();
			StData.logicTasks.registerBasic(new BaseLogic());
		}
	}
}
