package dcode.games.uEngine2.tools;

import dcode.games.uEngine2.BGTasks.internalTasks.DelayedInputresize;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.uGameSetup;
import dcode.games.uEngine2.window.Window;
import dcode.games.uEngine2.window.canvases.*;

/**
 * Created by dusakus on 13.05.15.
 */
public class Resize {
    public static void updateRenderingSetup() {

        StData.LOG.println("Setting fullscren mode to " + StData.setup.fullscreen, "D");

        switch (StData.setup.fullscreen) {
            case nope:
                StData.threadManager.canvas = new W_Std();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, false);
                break;
            case box:
                StData.threadManager.canvas = new FS_Box();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, true);
                break;
            case colored_box:
                StData.threadManager.canvas = new FS_CBox();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, true);
                break;
            case stretch:
                StData.threadManager.canvas = new FS_Stretch();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, true);
                break;
            case scaled_box:
                StData.threadManager.canvas = new FS_scBox();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, true);
                break;
            case colored_scaled_box:
                StData.threadManager.canvas = new FS_scCBox();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, true);
                break;
            case setup_util:
                StData.threadManager.canvas = new W_Eng();
                if (StData.threadManager.window != null) StData.threadManager.window.rimuw();
                StData.threadManager.window = new Window(StData.threadManager.canvas, false);
                break;
            default:
                StData.LOG.println("Unknown fullscreen mode, setting to window", "E2");
                StData.setup.fullscreen = uGameSetup.FullMODE.nope;
                updateRenderingSetup();
        }
        try {
            StData.threadManager.PW.updateFSOffsets();
        } catch (Exception e){
            StData.LOG.println("Unable to update mouse offsets settings, queued for later");
            Shortcuts.registerOneTimeBGTask(new DelayedInputresize(), true);
        }
    }
}
