package dcode.games.uEngine2.tools;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.StData;

/**
 * Created by dusakus on 17.04.15.
 */
public class Shortcuts {
    public static void requestTexture(String source, String key){
        StData.generalBGT.LPTasks.add(new LoadBasicTexture(source, key));
    }
    public static void sortSprites(){
        StData.threadManager.RT.forceSpriteSort = true;
    }
    public static void log(String l){StData.LOG.println(l);}
    public static void debug(String l){StData.LOG.println(l, "D");}
}
