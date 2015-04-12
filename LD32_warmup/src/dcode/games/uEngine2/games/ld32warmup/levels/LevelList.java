package dcode.games.uEngine2.games.ld32warmup.levels;

/**
 * Created by dusakus on 10.04.15.
 */
public class LevelList {
    public static LevelBase[] levelList;

    public static void fillList() {
        levelList = new LevelBase[16];

        levelList[0] = new LEVEL_testificate();
        levelList[1] = new LEVEL_1();
    }
}
