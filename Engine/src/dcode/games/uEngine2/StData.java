/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2;

import dcode.games.uEngine2.BGTasks.BackgroundTasks;
import dcode.games.uEngine2.GFX.debugInfoLayer;
import dcode.games.uEngine2.LOGIC.LogicTasks;
import dcode.games.uEngine2.ResourceManager.ResMan;
import dcode.games.uEngine2.localStorage.configStorage.CContainer;
import dcode.games.uEngine2.tools.ExceptionHandler;
import dcode.games.uEngine2.translator.Translator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * THIS file stores essential game objects, accesed from multiple threads
 *
 * @author dusakus
 */
public class StData {

	public static final String VersionString = "2.1.a.010";                    //Engine version string

	public static uGameSetup setup;                                             //game configuration object
	public static DCoutputH LOG;                                                //Log output
	public static String lastMSG = "INIT";                                      //Last system message, used in debug overlay
	public static BufferedImage NextFrame;                                      //Next frame, probably beeing rendered
	public static ThreadManager threadManager;                                  //Thread managment thread
	public static ResMan resources;                                             //Resource manager, providing game content and loading it
	public static GameContainer currentGC;                                      //Game container,whole game, independent from any threads
	public static LogicTasks logicTasks;                                        //internal logic task list
	public static BackgroundTasks generalBGT;
	public static File gameStorageDirectory;
	public static boolean gameIsRunning = true;                                 //is game running, or should stop execution
	static PuGameBase GameInitializer;                                          //Game initialization object, provided by game itself
	public static Translator translator;
	public static boolean gameFreeze = false;
	public static boolean isRendering = false;
	public static Random gRand = new Random(System.currentTimeMillis());
	public static ExceptionHandler EXC = new ExceptionHandler();
	public static debugInfoLayer dlayer;
	public static CContainer engineConfig;
}
