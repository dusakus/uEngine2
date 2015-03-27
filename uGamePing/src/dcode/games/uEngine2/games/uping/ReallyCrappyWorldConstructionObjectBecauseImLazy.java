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
		BatMan.areas[4][3].addBat(new Bat(120, Bat.FACING_UP, 130, 232));
		BatMan.areas[4][3].addBat(new Bat(40, Bat.FACING_DOWN, 330, 8));

		//Area 1-2
		BatMan.areas[1][2].addBat(new Bat(180, Bat.FACING_UP, 175, 232));
		BatMan.areas[1][2].addBat(new Bat(80, Bat.FACING_LEFT, 380, 80));

		//Area 1-3
		BatMan.areas[1][3].addBat(new Bat(180, Bat.FACING_UP, 175, 232));
		BatMan.areas[1][3].addBat(new Bat(80, Bat.FACING_LEFT, 380, 80));

		//Area 1-4
		BatMan.areas[1][4].addBat(new Bat(180, Bat.FACING_DOWN, 175, 8));
		BatMan.areas[1][4].addBat(new Bat(180, Bat.FACING_UP, 175, 232));

		//Area 1-5
		BatMan.areas[1][5].addBat(new Bat(180, Bat.FACING_DOWN, 175, 8));
		BatMan.areas[1][5].addBat(new Bat(180, Bat.FACING_UP, 175, 232));

		//Area 1-6
		BatMan.areas[1][6].addBat(new Bat(180, Bat.FACING_DOWN, 175, 8));
		BatMan.areas[1][6].addBat(new Bat(70, Bat.FACING_LEFT, 392, 170));

		//Area 2-1
		BatMan.areas[2][1].addBat(new Bat(50, Bat.FACING_UP, 175, 150));

		//Area 2-2
		BatMan.areas[2][2].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		BatMan.areas[2][2].addBat(new Bat(80, Bat.FACING_LEFT, 380, 80));

		//Area 2-3
		BatMan.areas[2][3].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		BatMan.areas[2][3].addBat(new Bat(30, Bat.FACING_LEFT, 380, 80));

		//Area 2-4
		BatMan.areas[2][4].addBat(new Bat(140, Bat.FACING_UP, 130, 114));
		BatMan.areas[2][4].addBat(new Bat(140, Bat.FACING_DOWN, 130, 114));

		//Area 2-5
		BatMan.areas[2][5].addBat(new Bat(140, Bat.FACING_UP, 130, 114));
		BatMan.areas[2][5].addBat(new Bat(140, Bat.FACING_DOWN, 130, 114));

		//Area 2-6
		BatMan.areas[2][6].addBat(new Bat(60, Bat.FACING_RIGHT, 8, 140));

		//Area 3-2
		BatMan.areas[3][2].addBat(new Bat(80, Bat.FACING_LEFT, 380, 80));
		BatMan.areas[3][2].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));

		//Area 3-3
		BatMan.areas[3][3].addBat(new Bat(30, Bat.FACING_LEFT, 392, 180));
		BatMan.areas[3][3].addBat(new Bat(20, Bat.FACING_RIGHT, -4, 150));

		//Area 3-5
		BatMan.areas[3][5].addBat(new Bat(30, Bat.FACING_LEFT, 392, 200));

		//Area 3-6
		BatMan.areas[3][6].addBat(new Bat(60, Bat.FACING_LEFT, 392, 90));
		BatMan.areas[3][6].addBat(new Bat(60, Bat.FACING_UP, 170, 232));

		//Area 4-2
		BatMan.areas[4][2].addBat(new Bat(80, Bat.FACING_RIGHT, 8, 80));
		BatMan.areas[4][2].addBat(new Bat(40, Bat.FACING_UP, 330, 232));

		//Area 4-3
		BatMan.areas[4][3].addBat(new Bat(120, Bat.FACING_UP, 180, 232));
		BatMan.areas[4][3].addBat(new Bat(40, Bat.FACING_DOWN, 330, 8));
		BatMan.areas[4][3].addBat(new Bat(30, Bat.FACING_RIGHT, -4, 180));

		//Area 4-4
		BatMan.areas[4][4].addBat(new Bat(120, Bat.FACING_DOWN, 180, -2));

		//Area 4-5
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_UP, 20, 114));
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_DOWN, 20, 114));
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_UP, 160, 114));
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_DOWN, 160, 114));
		BatMan.areas[4][5].addBat(new Bat(24, Bat.FACING_UP, 160, 180));
		BatMan.areas[4][5].addBat(new Bat(24, Bat.FACING_DOWN, 160, 180));
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_UP, 320, 114));
		BatMan.areas[4][5].addBat(new Bat(36, Bat.FACING_DOWN, 320, 114));
		BatMan.areas[4][5].addBat(new Bat(48, Bat.FACING_RIGHT, -4, 170));

		//Area 4-6
		BatMan.areas[4][6].addBat(new Bat(48, Bat.FACING_LEFT, 390, 80));
		BatMan.areas[4][6].addBat(new Bat(48, Bat.FACING_RIGHT, -2, 80));



	}

}
