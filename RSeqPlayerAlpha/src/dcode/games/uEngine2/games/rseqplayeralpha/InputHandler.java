package dcode.games.uEngine2.games.rseqplayeralpha;

public class InputHandler extends dcode.games.uEngine2.PInputHandler {


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
		LStData.currentInputProcessor.upPressed();
	}

	@Override
	public void keyPressed_S() {
		LStData.currentInputProcessor.downPressed();

	}

	@Override
	public void keyPressed_A() {
		LStData.currentInputProcessor.leftPressed();

	}

	@Override
	public void keyPressed_D() {
		LStData.currentInputProcessor.rightPressed();

	}

	@Override
	public void keyPressed_Z() {
		LStData.currentInputProcessor.confirmPressed();

	}

	@Override
	public void keyPressed_X() {

	}

	@Override
	public void keyPressed_UP() {
		LStData.currentInputProcessor.upPressed();

	}

	@Override
	public void keyPressed_DOWN() {
		LStData.currentInputProcessor.downPressed();

	}

	@Override
	public void keyPressed_LEFT() {
		LStData.currentInputProcessor.leftPressed();

	}

	@Override
	public void keyPressed_RIGHT() {
		LStData.currentInputProcessor.rightPressed();

	}

	@Override
	public void keyPressed_ENTER() {
		LStData.currentInputProcessor.confirmPressed();

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
