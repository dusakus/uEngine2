
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.games.uflappy;

import dcode.games.uEngine2.PuGameBase;
import dcode.games.uEngine2.Startup;
import dcode.games.uEngine2.uGameSetup;

/**
 * @author dusakus
 */
public class UFlappy {

	public static boolean isInGame = false;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		uGameSetup gs = new uGameSetup();
		gs.FPS = 60;
		gs.TPS_logic = 60;
		gs.TPS_MSX = 256;
		gs.TPS_BG = 2;

		gs.debug = true;
		gs.width = 400;
		gs.height = 500;
		gs.scale = 1;

		gs.safeName = "uflappy";
		gs.screenName = "uFlappy";
		gs.windowTitle = "uFlappy, ";

		PuGameBase gb = new PuGameBase();

		gb.setup = gs;
		gb.initialInputHandler = new InHandler();
		gb.contentInitializer = new Initializer();

		Startup.StartGame(gb);

	}

}
