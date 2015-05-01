package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBitmapFont;
import dcode.games.uEngine2.StData;

/**
 * Created by dusakus on 4/29/15.
 */
public class LoadFonts implements ILoadMe {
    @Override
    public void loadDys() throws Exception {
        new LoadBitmapFont("FONT/pixel_7_9.png", "FGENB", 7, 9, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?#*)(][><,.:;-+_=&~^$@\"/\\").perform();
        new LoadBitmapFont("FONT/pixel_7_9_WHITE.png", "FGENW", 7, 9, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?#*)(][><,.:;-+_=&~^$@\"/\\").perform();
    }

    @Override
    public boolean checkFinished() {
        return true;
    }
}
