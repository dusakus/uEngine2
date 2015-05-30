package dcode.games.uEngine2.games.ugametoolkit.uiinput;

import dcode.games.uEngine2.StData;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dusakus on 01.05.15.
 */
public class ButtonList {

    private ArrayList<Button> buttons;

    public ButtonList() {
        buttons = new ArrayList<Button>();
    }

    public void registerButton(Button b) {
        if (b.getBounds() != null && b.getHandler() != null) {
            buttons.add(b);
        } else {
            StData.LOG.println("Thied to register button with missing properties!", "E2");
        }
    }

    public void removeButton(Button b) {
        buttons.remove(b);
    }

    public void LClick(int x, int y) {
        Point p = new Point(x, y);
        for (Button b : buttons) {
            if (b.getBounds().contains(p)) {
                b.getHandler().handleButtonPress(b);
            }
        }
    }

    public void RClick(int x, int y) {
        Point p = new Point(x, y);
        for (Button b : buttons) {
            if (b.getBounds().contains(p)) {
                b.getHandler().handleButtonRightClick(b);
            }
        }
    }

    public ArrayList<Button> getList() {
        return buttons;
    }
}
