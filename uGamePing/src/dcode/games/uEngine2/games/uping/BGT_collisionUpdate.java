package dcode.games.uEngine2.games.uping;

import dcode.games.uEngine2.BGTasks.PBGTask;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 24.03.15.
 */
public class BGT_collisionUpdate extends PBGTask {
	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void perform() {
		BufferedImage bf = new BufferedImage(400, 240, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D G2D = bf.createGraphics();
		G2D.drawImage(LStData.gameworld.getDataImage(), 0, 0, null);
		G2D.dispose();

		LStData.collisionMap = bf;
	}
}
