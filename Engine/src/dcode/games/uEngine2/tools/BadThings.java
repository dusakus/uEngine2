package dcode.games.uEngine2.tools;

import dcode.games.uEngine2.StData;
import java.awt.event.MouseListener;

/**
 * Created by dusakus on 07.05.15.
 */
public class BadThings {

    public static void insertOwnMouseHandler(MouseListener mouseListener){
        StData.threadManager.window.registerExtMouseListener(mouseListener);
    }
}
