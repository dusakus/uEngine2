package dcode.games.uEngine2.games.uping;

import java.awt.*;

/**
 * Created by dusakus on 24.03.15.
 */
public class ReallyCrappyWorldConstructionObjectBecauseImLazy {

	public void writeWorldData(LAYERs_GameScene worldMan) {
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 10; j++) {
				worldMan.areas[i][j] = worldMan.createArea("lv1", i, j, worldMan);
				BatMan.areas[i][j] = new BatPack();

			}
		}
		Bat b = new Bat(80, Bat.FACING_LEFT, 380, 80);
		b.color = Color.BLUE;
		BatMan.areas[3][2].addBat(b);
		BatMan.areas[3][2].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
	}

}
