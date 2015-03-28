package dcode.games.uEngine2.games.rseqplayeralpha.SCREEN_playback;

import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.rseqplayeralpha.other.plbContainer;

/**
 * Created by dusakus on 05.03.15.
 */
public class ScriptProcessor {
	public int globalVar_int1 = 0;
	public int globalVar_int2 = 0;
	public int globalVar_int3 = 0;
	public boolean globalVar_bool1 = false;
	public boolean globalVar_bool2 = false;
	public boolean globalVar_bool3 = false;
	public String globalVar_String1 = "?";
	public String globalVar_String2 = "?";
	public String globalVar_String3 = "?";

	boolean[] activeScripts;

	private plbContainer PCont;

	private int tickID;
	private int nextTickDelay;

	public ScriptProcessor(plbContainer plbc) {
		PCont = plbc;
		activeScripts = new boolean[32];
		for (int i = 0; i < activeScripts.length; i++) {
			activeScripts[i] = false;
		}
		activeScripts[0] = true;
		tickID = 0;
	}

	public boolean TICK() {
		if (nextTickDelay > 0) {
			StData.LOG.println("[SCR_PR]Tick no." + tickID + " is delayed, " + nextTickDelay + " logic ticks left", "D");
			nextTickDelay--;
			return false;
		} else {
			tickID++;
		}
		StData.LOG.println("[SCR_PR]Beginning tick no." + tickID, "D");
		for (int i = 0; i < activeScripts.length; i++) {
			if (activeScripts[i]) {
				StData.LOG.println("[SCR_PR]Script with id " + i + " is active, processing...", "D");

				plbContainer.SCommand sc = PCont.Scripts[i].ScriptCommands.peek();
				StData.LOG.println("[SCR_PR]" + sc.commandGroup + "." + sc.commandKey + " is beeing checked, target tick: (" + sc.processAt + ")", "D");

				while (sc != null && sc.processAt == tickID) {
					StData.LOG.println("[SCR_PR]" + sc.commandGroup + "." + sc.commandKey + " is marked to be performed in this tick (" + tickID + ")", "D");
					sc = PCont.Scripts[i].ScriptCommands.poll();
					switch (sc.commandGroup) {
						case "SYSTEM":
							CG_SYSTEM(sc, i);
							break;
						case "VARS":
							CG_VARS(sc, i);
							break;
						case "BACKGROUND":
							CG_BACKGROUND(sc, i);
							break;
						default:
							StData.LOG.println("ScriptProcessor: unknown command group: " + sc.commandGroup, "D");
					}

					sc = PCont.Scripts[i].ScriptCommands.peek();
				}
			}
		}

		return false;
	}

	private void CG_BACKGROUND(plbContainer.SCommand sc, int i) {

	}

	private boolean CG_VARS(plbContainer.SCommand sc, int scriptID) {
		switch (sc.commandKey) {
			case "SETBOOL":
				if (sc.bool1) {
					switch (sc.int1) {
						case 1:
							globalVar_bool1 = sc.bool2;
							break;
						case 2:
							globalVar_bool2 = sc.bool2;
							break;
						case 3:
							globalVar_bool3 = sc.bool2;
							break;
					}
				} else {
					switch (sc.int1) {
						case 1:
							PCont.Scripts[scriptID].localVar_bool1 = sc.bool2;
							break;
						case 2:
							PCont.Scripts[scriptID].localVar_bool2 = sc.bool2;
							break;
						case 3:
							PCont.Scripts[scriptID].localVar_bool3 = sc.bool2;
							break;
					}
				}
				break;
			case "SETINT":
				if (sc.bool1) {
					switch (sc.int1) {
						case 1:
							globalVar_int1 = sc.int2;
							break;
						case 2:
							globalVar_int2 = sc.int2;
							break;
						case 3:
							globalVar_int3 = sc.int2;
							break;
					}
				} else {
					switch (sc.int1) {
						case 1:
							PCont.Scripts[scriptID].localVar_int1 = sc.int2;
							break;
						case 2:
							PCont.Scripts[scriptID].localVar_int2 = sc.int2;
							break;
						case 3:
							PCont.Scripts[scriptID].localVar_int3 = sc.int2;
							break;
					}
				}
				break;
			case "SETSTRING":
				if (sc.bool1) {
					switch (sc.int1) {
						case 1:
							globalVar_String1 = sc.extra;
							break;
						case 2:
							globalVar_String2 = sc.extra;
							break;
						case 3:
							globalVar_String3 = sc.extra;
							break;
					}
				} else {
					switch (sc.int1) {
						case 1:
							PCont.Scripts[scriptID].localVar_String1 = sc.extra;
							break;
						case 2:
							PCont.Scripts[scriptID].localVar_String2 = sc.extra;
							break;
					}
				}
				break;
			case "INCRINT":
				if (sc.bool1) {
					switch (sc.int1) {
						case 1:
							globalVar_int1 += sc.int2;
							break;
						case 2:
							globalVar_int2 += sc.int2;
							break;
						case 3:
							globalVar_int3 += sc.int2;
							break;
					}
				} else {
					switch (sc.int1) {
						case 1:
							PCont.Scripts[scriptID].localVar_int1 += sc.int2;
							break;
						case 2:
							PCont.Scripts[scriptID].localVar_int2 += sc.int2;
							break;
						case 3:
							PCont.Scripts[scriptID].localVar_int3 += sc.int2;
							break;
					}
				}
				break;
			case "ROTATE":

				break;
			default:
				StData.LOG.println("ScriptProcessor: unknown VARS command: " + sc.commandKey, "D");

		}

		return false;
	}

	private boolean CG_SYSTEM(plbContainer.SCommand sc, int i) {
		switch (sc.commandKey) {
			case "PRINT":
				StData.LOG.println("[SCRIPT]:" + sc.extra);
				break;
			case "SCRIPTENABLE":
				StData.LOG.println("[SCRIPT]: setting script active flag of script with id " + sc.int1 + " to " + sc.bool1 + ", MSG: " + sc.extra, "N");
				activeScripts[sc.int1] = sc.bool1;
				break;
			case "SETSPEED":
				StData.LOG.println("[SCRIPT]: setting logic speed to " + sc.int1 + ", MSG: " + sc.extra, "N");
				StData.threadManager.LT.LOOP_TPS = sc.int1;
				StData.threadManager.LT.LOOP_Recalculate = true;
				break;
			case "DELAY":
				StData.LOG.println("[SCRIPT]: delaying next tick by " + sc.int1 + ", MSG: " + sc.extra, "N");
				nextTickDelay = sc.int1;
				break;
			default:
				StData.LOG.println("ScriptProcessor: unknown SYSTEM command: " + sc.commandKey, "D");


		}

		return false;
	}
}
