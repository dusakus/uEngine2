package dcode.games.uEngine2.games.rseqplayeralpha;

import dcode.games.uEngine2.BGTasks.PBGTask;
import dcode.games.uEngine2.StData;

/**
 * Created by dusakus on 09.02.15.
 */
public class ErrorHandler extends PBGTask {
	public static int ERRCODE_DontKnowWhatWentWrong = -1;
	public static int ERRCODE_WrongInitStatus = 101;

	private int nextStart = 10;

	@Override
	public boolean isReady() {
		if (nextStart <= 0) {
			return true;
		} else {
			nextStart--;
			return false;
		}
	}

	@Override
	public void perform() {
		StData.currentGC.currentBGT.WaitingTasks.add(new ErrorHandler());
		if (!(LStData.currentMode < 0)) {
			//todo do moar
			StData.LOG.println("Error occured, state is " + LStData.currentMode + " , errorcode: " + LStData.ERRORCODE);
		}
	}
}
