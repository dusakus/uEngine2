package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.tools.Shortcuts;
import dcode.games.uEngine2.tools.ext.j2s.gifReader;

/**
 * Created by dusakus on 01.05.15.
 */
public class LoadCursors implements  ILoadMe {
    @Override
    public void loadDys() throws Exception {
        gifReader gr = new gifReader();
        gr.read(this.getClass().getResourceAsStream("/dcode/games/uEngine2/games/ugametoolkit/res/gfx/GENERAL_UI/cursors.gif"));


        //load standard cursorset
        Shortcuts.registerTexture(gr.getFrame(0), "CURpoA");
        Shortcuts.registerTexture(gr.getFrame(1), "CURpoD");
        Shortcuts.registerTexture(gr.getFrame(2), "CURcpi");
        Shortcuts.registerTexture(gr.getFrame(3), "CURepo");
        Shortcuts.registerTexture(gr.getFrame(4), "CURdef");
        Shortcuts.registerTexture(gr.getFrame(5), "CURsms");
        Shortcuts.registerTexture(gr.getFrame(6), "CURlms");

        Shortcuts.debug("Finished loading cursors");


    }

    @Override
    public boolean checkFinished() {
        return true;
    }
}
