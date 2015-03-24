/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.games.uflappy;

import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.PInputHandler;
import dcode.games.uEngine2.StData;

/**
 * @author dusakus
 */
public class InHandler extends PInputHandler {

	@Override
	public void keyTyped(char Char) {
	}

	@Override
	public void keyPressed(int ID) {

	}

	@Override
	public void keyReleased(int ID) {
	}

	@Override
	public void keyPressed(String Key) {
	}

	@Override
	public void keyReleased(String Key) {
	}

	@Override
	public void keyPressed_W() {
	}

	@Override
	public void keyPressed_S() {

	}

	@Override
	public void keyPressed_A() {

	}

	@Override
	public void keyPressed_D() {

	}

	@Override
	public void keyPressed_Z() {
		if (UFlappy.isInGame) {
			StData.logicTasks.registerBasic(new CharJump());
			Initializer.fallSpeed = -1;
		}
	}

	@Override
	public void keyPressed_X() {
		UFlappy.isInGame = !UFlappy.isInGame;
	}

	@Override
	public void keyPressed_UP() {

	}

	@Override
	public void keyPressed_DOWN() {

	}

	@Override
	public void keyPressed_LEFT() {

	}

	@Override
	public void keyPressed_RIGHT() {

	}

	@Override
	public void keyPressed_ENTER() {

	}

	@Override
	public void keyPressed_SPACE() {

	}

	@Override
	public void keyPressed_BACKSPACE() {

	}

	@Override
	public void keyPressed_ESC() {

	}

	@Override
	public void keyPressed_TAB() {

	}

	@Override
	public void keyPressed_KonamiCode() {

	}

	@Override
	public void clickedRight(int x, int y) {

	}

	@Override
	public void clickedLeft(int x, int y) {

	}

	@Override
	public void clickedMiddle(int x, int y) {

	}

	@Override
	public void scrolledUp(int x, int y) {

	}

	@Override
	public void scrolledDown(int x, int y) {

	}

}

class CharJump implements ILogicTask {

	private int tCount = 0;

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void perform() {
		if (tCount < 10) {
			Initializer.player.setY(Initializer.player.getY() - 2);
		}
		Initializer.player.setY(Initializer.player.getY() - 2);
		tCount++;
	}

	@Override
	public boolean doRepeat() {
		return tCount < 41;
	}

}
