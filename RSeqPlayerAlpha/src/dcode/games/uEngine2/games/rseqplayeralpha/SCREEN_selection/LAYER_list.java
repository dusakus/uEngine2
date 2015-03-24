package dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_selection;

import dcode.games.uEngine2.GFX.ILayer;
import dcode.games.uEngine2.games.rseqplayeralpha.LStData;
import dcode.games.uEngine2.games.rseqplayeralpha.other.LAYER_Loading;

import java.awt.*;

/**
 * Created by dusakus on 09.02.15.
 */
public class LAYER_list implements ILayer {

	private LAYER_Loading ll = new LAYER_Loading(-1);

	@Override
	public void draw(Graphics2D G2D) {
		if (LStData.currentMode == LStData.MODE_MENU_MAIN) {
			drawMainList(G2D);
		} else if (LStData.currentMode == LStData.MODE_MENU_LIST) {
			drawListList(G2D);
		} else if (LStData.currentStatus == LStData.MODE_MENU_CONFIRM) {
			drawSelection(G2D);
		}

	}

	private void drawSelection(Graphics2D G2D) {
	}

	private void drawListList(Graphics2D G2D) {
		if (LStData.currentStatus < 100) {
			ll.draw(G2D);
			return;
		}
		G2D.setColor(Color.GRAY);
		G2D.fillRect(0, 0, 400, 240);

		G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));

		G2D.setColor(Color.YELLOW);
		G2D.drawString("SELECT FILE", 10, 42);

		G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		G2D.setColor(Color.WHITE);

		G2D.drawString("Select file", 16, 62);

		for (int i = 0; i < LStData.containerList.size(); i++) {
			if (LStData.currentStatus == 101 + i) {
				G2D.setColor(Color.YELLOW);
			} else {
				G2D.setColor(Color.WHITE);
			}
			G2D.drawString(LStData.containerList.get(i).title, 24, 80 + 16 * i);
		}
	}

	private void drawMainList(Graphics2D G2D) {
		//ToDo: fancier list

		G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
		G2D.setColor(Color.YELLOW);
		G2D.drawString("MENU", 10, 42);

		G2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		G2D.setColor(Color.WHITE);
		G2D.drawString("Select file", 16, 62);
		G2D.drawString("Settings", 16, 82);
		G2D.drawString("About", 16, 102);
		G2D.drawString("Exit", 16, 122);

		G2D.setColor(Color.RED);
		switch (LStData.currentStatus) {
			case 301:
				G2D.drawString("Select file", 16, 62);
				break;
			case 302:
				G2D.drawString("Settings", 16, 82);
				break;
			case 303:
				G2D.drawString("About", 16, 102);
				break;
			case 304:
				G2D.drawString("Exit", 16, 122);
				break;

		}

	}

	@Override
	public boolean removeMe() {
		return false;
	}

	@Override
	public boolean renderMe() {
		return true;
	}
}
