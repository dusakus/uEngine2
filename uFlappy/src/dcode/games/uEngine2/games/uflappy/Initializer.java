/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.games.uflappy;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.GFX.ScreenContent;
import dcode.games.uEngine2.GFX.layers.FillTextureLayer;
import dcode.games.uEngine2.GFX.sprites.Sprite;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.PuGameLoader;
import dcode.games.uEngine2.StData;

import java.awt.*;
import java.util.Random;

/**
 * @author dusakus
 */
public class Initializer extends PuGameLoader {

	public static ScreenContent scMenu;
	public static ScreenContent scGame;

	public static genericSprite fl1, fl2;
	public static genericSprite pipeUp, pipeDown;
	public static genericSprite player;

	public static int fallSpeed = 0;
	public static int fallBoostDelay = 10;

	public static void reinitGame() {
		fl1 = new genericSprite("GF1", 0, 472);
		fl2 = new genericSprite("GF2", 0, 455);
		player = new genericSprite("Gplayer", 10, 240);

		pipeUp = new genericSprite("GPt", -100, 900);
		pipeDown = new genericSprite("GPb", -100, 900);

		scGame.sprites[11] = fl1;
		scGame.sprites[12] = fl2;
		scGame.sprites[1] = player;
		scGame.sprites_back[11] = 11;
		scGame.sprites_back[12] = 12;
		scGame.sprites_middle[1] = 1;
		scGame.sprites[21] = pipeUp;
		scGame.sprites[22] = pipeDown;
		scGame.sprites_front[1] = 21;
		scGame.sprites_front[2] = 22;
	}

	@Override
	public void loadInitialGameContent() {
		StData.threadManager.BGT.addTask(new LoadBasicTexture("background.png", "Gbg"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("pipe-top.png", "GPt"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("pipe-bottom.png", "GPb"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("bird.png", "Gplayer"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("floor.png", "GF1"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("floor2.png", "GF2"));
		StData.threadManager.BGT.addTask(new LoadBasicTexture("home-background.png", "Mbg"));

		fl1 = new genericSprite("GF1", 0, 472);
		fl2 = new genericSprite("GF2", 0, 455);
		player = new genericSprite("Gplayer", 10, 240);

		pipeUp = new genericSprite("GPt", -1, 900);
		pipeDown = new genericSprite("GPb", -1, 900);

		scMenu = new ScreenContent();
		scMenu.layers_Background.add(new FillTextureLayer("Mbg"));

		StData.currentGC.currentSC = scMenu;

		scGame = new ScreenContent();
		scGame.layers_Background.add(new FillTextureLayer("Gbg"));
		scGame.sprites[11] = fl1;
		scGame.sprites[12] = fl2;
		scGame.sprites[1] = player;
		scGame.sprites_back[11] = 11;
		scGame.sprites_back[12] = 12;
		scGame.sprites_middle[1] = 1;
		scGame.sprites[21] = pipeUp;
		scGame.sprites[22] = pipeDown;
		scGame.sprites_front[1] = 21;
		scGame.sprites_front[2] = 22;

		StData.logicTasks.registerBasic(new updateGraphics());

		reinitGame();
	}
}

class updateGraphics implements ILogicTask {

	private Random r = new Random();
	private int pipeHeight = -1;

	@Override
	public boolean isReady() {
		if (UFlappy.isInGame) {
			if (StData.currentGC.currentSC == Initializer.scMenu) {
				StData.currentGC.currentSC = Initializer.scGame;
			}
			return true;
		} else {
			if (StData.currentGC.currentSC == Initializer.scGame) {
				StData.currentGC.currentSC = Initializer.scMenu;
			}
			return false;
		}
	}

	@Override
	public void perform() {

		Initializer.fl1.setX(Initializer.fl1.getX() - 1);
		if (Initializer.fl1.getX() < -28) {
			Initializer.fl1.setX(0);
		}
		Initializer.fl2.setX(Initializer.fl2.getX() - 1);
		if (Initializer.fl2.getX() < -19) {
			Initializer.fl2.setX(0);
		}
		Initializer.pipeUp.setX(Initializer.pipeUp.getX() - 2);
		Initializer.pipeDown.setX(Initializer.pipeDown.getX() - 2);
		Initializer.player.setY(Initializer.player.getY() + Initializer.fallSpeed);
		Initializer.fallBoostDelay--;
		if (Initializer.fallBoostDelay < 0) {
			Initializer.fallSpeed++;
			Initializer.fallBoostDelay = 5 + Initializer.fallSpeed;
		}
		if (Initializer.pipeUp.getX() < -64) {
			Initializer.pipeUp.setX(401);
			Initializer.pipeDown.setX(401);
			pipeHeight = r.nextInt(275) + 25;
			Initializer.pipeUp.setY(pipeHeight - 604);
			Initializer.pipeDown.setY(pipeHeight + 115);
		}
		if (Initializer.pipeUp.getX() < 15 && Initializer.pipeUp.getX() > -55) {
			if (Initializer.player.getY() < pipeHeight || Initializer.player.getY() + 16 > pipeHeight + 120) {
				UFlappy.isInGame = false;
				Initializer.reinitGame();
			}
		}
		if (Initializer.player.getY() > 480 || Initializer.player.getY() < -15) {
			UFlappy.isInGame = false;
			Initializer.reinitGame();
		}

	}

	@Override
	public boolean doRepeat() {
		return true;
	}

}

class genericSprite extends Sprite {

	public genericSprite(String tKey, int initialX, int initialY) {
		textureKey = tKey;
		x = initialX;
		y = initialY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public Image getCustomTexture() {
		return null;
	}

	@Override
	public boolean doCustomRender() {
		return false;
	}

	@Override
	public void customRender(Graphics2D G) {
	}

}
