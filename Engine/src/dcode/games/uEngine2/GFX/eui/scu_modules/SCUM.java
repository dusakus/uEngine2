package dcode.games.uEngine2.GFX.eui.scu_modules;

/**
 * Created by dusakus on 03.06.15.
 */
public class SCUM {


    public class SCElement {
        public ELEMENT_TYPE type;
    }

    private enum ELEMENT_TYPE {
        textLine, textField, intScroll, intBar, checkbox
    }
}
