package dcode.games.uEngine2.games.uping;

import java.awt.*;

/**
 * Created by dusakus on 24.03.15.
 */
public class ReallyCrappyWorldConstructionObjectBecauseImLazy {

	public void writeWorldData(LAYERs_GameScene worldMan) {
		worldMan.areas[16][16] = worldMan.createArea("str", 16, 16, worldMan);
		BatMan.areas[16][16] = new BatPack();
		Bat b = new Bat(80, Bat.FACING_LEFT, 380, 80);
		b.color = Color.BLUE;
		BatMan.areas[16][16].addBat(b);
		BatMan.areas[16][16].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		BatMan.areas[17][16].addBat(b);
		BatMan.areas[17][16].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		BatMan.areas[15][16].addBat(b);
		BatMan.areas[15][16].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		worldMan.areas[17][16] = worldMan.createArea("str", 17, 16, worldMan);
		worldMan.areas[15][16] = worldMan.createArea("str", 15, 16, worldMan);

	}

}
