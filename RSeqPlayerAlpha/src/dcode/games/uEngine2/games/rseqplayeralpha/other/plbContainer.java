package dcode.games.uEngine2.games.rseqplayeralpha.other;

import dcode.games.uEngine2.BGTasks.PBGTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.rseqplayeralpha.LStData;
import dcode.games.uEngine2.tools.READER;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by dusakus on 17.02.15.
 */
public class plbContainer {
	public File source = null;
	public String title = "unnamed";
	public String description = "unnamed";
	public plbSoundClip[] Sounds = new plbSoundClip[128];
	public plbTexture[] Textures = new plbTexture[512];
	public plbScript[] Scripts = new plbScript[32];

	private boolean notADir = false;
	private ArrayList<File> dirEntries;

	public plbContainer(File Source) {
		title = Source.getName();
		source = Source;
		description = "not loaded yet";
		StData.currentGC.currentBGT.LPTasks.add(new plbLoadTask(this, false));
	}

	public void loadBasicContent() {
		title = "loading...";
		dirEntries = new ArrayList<>();
		try {
			File[] list = source.listFiles();
			for (File f : list) {
				dirEntries.add(f);
			}
			System.out.println("List of entries:");
			for (File f : dirEntries) {
				System.out.println(f.getName());
			}
			title = "loading(listCreated)...";

		} catch (Exception e) {
			StData.LOG.printerr(e, "Failed to read/load basic content in file " + source.getName(), "E2");
			title = "Broken USArchive"; //miss
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			notADir = true;
			return;
		}
		try {
			title = "loading(readyToReadList)...";
			for (File ze : dirEntries) {
				if (ze.getName().endsWith(".desc")) {
					READER r = new READER(ze);
					String s = r.nextLine();
					while (s != null) {
						if (s.startsWith("TITLE:")) {
							this.title = s.substring(6);
						}
						if (s.startsWith("DESC:")) {
							this.description = s.substring(5);
						}
						s = r.nextLine();
					}
				}
			}
		} catch (Exception e) {
			StData.LOG.printerr(e, "Failed to process content of file " + source.getName(), "E2");
		}
		if (title.startsWith(source.getName())) {
			title = "Broken USArchive"; //miss
		}
	}


	public void loadExtendedContent() {
		if (notADir) {
			title = "unknown file";
			description = "the file " + source.getName() + " is not an archive, may cause crashes ]:|";
			return;
		}
		notADir = true;
		StData.LOG.println("[Loader] loading file...", "D");
		try {
			for (File ze : dirEntries) {
				if (ze.getName().endsWith(".scr")) {
					plbScript obj = new plbScript();


					READER r = new READER(ze);
					String s = r.nextLine();
					while (s != null) {
						obj.lines.add(s);
						s = r.nextLine();
					}
					int scriptID = Integer.parseInt(ze.getName().replace(".scr", ""));
					Scripts[scriptID] = obj;
					obj.prepare();
					StData.LOG.println("Loaded script file with id " + scriptID, "D");
				} else if (ze.getName().endsWith(".png")) {
					plbTexture obj = new plbTexture();
					obj.texture = ImageIO.read(ze);
					int texID = Integer.parseInt(ze.getName().replace(".png", ""));
					Textures[texID] = obj;
					StData.LOG.println("Loaded texture file with id " + texID, "D");
				} else if (ze.getName().endsWith(".desc")) {
					StData.LOG.println("Skipping descriptor, already read", "D");
				} else {
					StData.LOG.println("Unknown/Unsupported file detected in sequence folder: " + ze.getName(), "E6S");
				}
			}
		} catch (Exception E) {
			StData.LOG.printerr(E, "Error occurred during load of file", "E3");
		}
		notADir = false;
	}

	public void requestExtendedLoad() {
		StData.currentGC.currentBGT.WaitingTasks.add(new plbLoadTask(this, true));
	}

	class plbLoadTask extends PBGTask {

		private plbContainer target;
		private boolean fullLoad;

		public plbLoadTask(plbContainer t, boolean doFull) {
			target = t;
			fullLoad = doFull;
			this.TaskPriority = PRIORITY_LOW;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void perform() {
			StData.LOG.println("Loading file: " + target.source.getName());
			try {
				target.loadBasicContent();
			} catch (Exception e) {
				StData.LOG.println("Failed to access file:" + target.source.getName(), "E1");
			}
			try {
				if (fullLoad) target.loadExtendedContent();
			} catch (Exception e) {
				StData.LOG.println("Error occured reading file: " + target.source.getName(), "E3");
				LStData.currentMode = LStData.MODE_MENU_MAIN;
				LStData.currentStatus = 808;
			}


			this.done = true;
		}
	}


	public class plbScript {
		public ArrayList<String> lines = new ArrayList<>();
		public LinkedList<SCommand> ScriptCommands = new LinkedList<>();
		public int cTarget = 0;

		public int localVar_int1 = 0;
		public int localVar_int2 = 0;
		public int localVar_int3 = 0;
		public boolean localVar_bool1 = false;
		public boolean localVar_bool2 = false;
		public boolean localVar_bool3 = false;
		public String localVar_String1 = "?";
		public String localVar_String2 = "?";

		public String Name = "unnamed";
		public String Description = "none";


		public void prepare() {
			for (String s : lines) {
				if (s.startsWith("#")) {
					s = s.substring(1);
					switch (s.charAt(0)) {
						case 'T':  // Name
							s = s.substring(2);
							Name = s;
							break;
						case 'I':  // Entry point
							s = s.substring(2);
							cTarget = Integer.parseInt(s.trim());
							break;
						case 'D':  // Description
							s = s.substring(2);
							Description = s;
							break;
					}
				} else if (s.trim().length() > 10) {
					String[] blocks = s.split(";");
					if (blocks.length < 10) {
						StData.LOG.println("Script " + Name + " has incorrect line: " + s.trim());
						return;
					}
					SCommand sc = new SCommand();
					sc.processAt = Integer.parseInt(blocks[0].trim());
					sc.commandGroup = blocks[1].trim();
					sc.commandKey = blocks[2].trim();
					sc.extra = blocks[3];
					if (blocks[4].trim().length() > 0) sc.int1 = Integer.parseInt(blocks[4].trim());
					if (blocks[5].trim().length() > 0) sc.bool1 = Boolean.parseBoolean(blocks[5].trim());
					if (blocks[6].trim().length() > 0) sc.int2 = Integer.parseInt(blocks[6].trim());
					if (blocks[7].trim().length() > 0) sc.bool2 = Boolean.parseBoolean(blocks[7].trim());
					if (blocks[8].trim().length() > 0) sc.int3 = Integer.parseInt(blocks[8].trim());
					if (blocks[9].trim().length() > 0) sc.bool3 = Boolean.parseBoolean(blocks[9].trim());

					ScriptCommands.add(sc);

				}
			}
		}
	}

	public class plbTexture {
		public BufferedImage texture;
	}

	public class plbSoundClip {
		public Clip soundClip;
	}

	public class SCommand {
		public int processAt = -1;

		public String commandGroup = "UNKNOWN";
		public String commandKey = "UNKNOWN";
		public String extra = "";
		public int int1 = -1;
		public int int2 = -1;
		public int int3 = -1;
		public boolean bool1 = false;
		public boolean bool2 = false;
		public boolean bool3 = false;
	}
}

