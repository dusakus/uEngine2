/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.SFX;

import dcode.games.uEngine2.StData;

/**
 * @author dusakus
 */
public class AudioThread extends Thread {

	public int ticks = 0;
	public boolean RUN = true;
	//
	private int TPS = 60;

	public AudioThread() {

		TPS = StData.setup.TPS_MSX;
	}

	@Override
	public void run() {
		//Timming variables
		int timeStep = 1000000000 / TPS;
		long currentTime = System.nanoTime();
		long nextTime = System.nanoTime() + timeStep;

		//FPS counter variables
		int FPS = 0;
		long lastFPSdraw = System.nanoTime();
		//LOOP
		while (StData.gameIsRunning) {

			//waiting for next planned time and performing sidetasks
			while (currentTime < nextTime) {
				try {
					Thread.sleep(5);
					currentTime = System.nanoTime();
				} catch (InterruptedException ex) {
				}
			}

			nextTime += timeStep;


			if (StData.setup.debug) {
				FPS++;
				if (System.nanoTime() - lastFPSdraw >= 1000000000L) {
					StData.LOG.println("[Audio Thread] Tick No." + ticks + ", measured TPS: " + FPS, "D");
					FPS = 0;
					lastFPSdraw = System.nanoTime();
				}
			}
			ticks++;
		}
	}

}
