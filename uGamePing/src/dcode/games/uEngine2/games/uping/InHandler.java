package dcode.games.uEngine2.games.uping;

import java.awt.event.KeyEvent;

/**
 * Created by dusakus on 24.03.15.
 */
public class InHandler extends dcode.games.uEngine2.PInputHandler {

	public static InHandler inst;

	public InHandler() {
		inst = this;
	}

	public boolean isKeyUp() {
		return this.isKeyPressed(KeyEvent.VK_UP) || isKeyPressed(KeyEvent.VK_W);
	}

	public boolean isKeyDown() {
		return this.isKeyPressed(KeyEvent.VK_DOWN) || isKeyPressed(KeyEvent.VK_S);
	}

	public boolean isKeyLeft() {
		return this.isKeyPressed(KeyEvent.VK_LEFT) || isKeyPressed(KeyEvent.VK_A);
	}

	public boolean isKeyRight() {
		return this.isKeyPressed(KeyEvent.VK_RIGHT) || isKeyPressed(KeyEvent.VK_D);
	}

	public boolean isKeyZ() {
		return this.isKeyPressed(KeyEvent.VK_ENTER) || isKeyPressed(KeyEvent.VK_Z);
	}

	public boolean isKeyX() {
		return this.isKeyPressed(KeyEvent.VK_ESCAPE) || isKeyPressed(KeyEvent.VK_X);
	}

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

	}

	@Override
	public void keyPressed_X() {

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
