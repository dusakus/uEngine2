package dcode.games.uEngine2.games.ld32warmup;

import dcode.games.uEngine2.BGTasks.internalTasks.LoadBasicTexture;
import dcode.games.uEngine2.LOGIC.ILogicTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ld32warmup.levels.LevelList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static dcode.games.uEngine2.StData.LOG;
import static dcode.games.uEngine2.StData.currentGC;
import static dcode.games.uEngine2.games.ld32warmup.LStData.*;

/**
 * Created by dusakus on 10.04.15.
 */
public class MainLogic implements ILogicTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        switch (currentMode) {
            case MODE_GAME_PLAY:

                break;
            case MODE_MENU_MAIN:

                break;
            case MODE_INIT:
                switch (currentStatus) {
                    case 0:
                        LOG.println("[INIT] Logic Entrypoint reached");
                        LOG.println("[INIT] Quickstarting loading screen");
                        currentGC.currentSC.layers_Overlay.add(new LAYER_Loading(101));
                        LOG.println("[INIT] Kappa");
                        try {
                            BufferedImage io = ImageIO.read(getClass().getResource("/dcode/kappa.jpg"));
                            BufferedImage bf = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
                            Graphics g = bf.getGraphics();
                            g.drawImage(io.getScaledInstance(64,64,BufferedImage.SCALE_REPLICATE), 0, 0, null);
                            StData.resources.grf.MISSINGTEX = bf;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        currentStatus++;
                        break;
                    case 1:
                        LOG.println("[INIT] Creating Logic objects");
                        currentGC.currentLT.registerBasic(new MenuLogic());
                        currentGC.currentLT.registerBasic(new GameLogic());
                        currentStatus += 9;
                        break;
                    case 10:
                        LOG.println("[INIT] requesting texture preloading");

                        StData.generalBGT.LPTasks.add(new LoadBasicTexture("msg/warning.png","MSGWARN"));
                        StData.generalBGT.LPTasks.add(new LoadBasicTexture("msg/info.png","MSGINFO"));
                        StData.generalBGT.LPTasks.add(new LoadBasicTexture("msg/item.png","MSGITEM"));

                        //TODO: preload textures
                        currentStatus++;
                        break;
                    case 11:
                        LOG.println("[INIT] creating level table");
                        LevelList.fillList();
                        currentStatus += 9;
                        break;
                    case 20:
                        LOG.println("[INIT] creating game objects");

                        currentStatus = 50;
                        break;
                    case 50:
                        LOG.println("[INIT] done, entering menu...");
                        currentMode = MODE_MENU_MAIN;
                        currentStatus = 1;
                        break;
                    default:
                        if (currentStatus < 0 || currentStatus > 50) {
                            LOG.println("[INIT] critical exception occured in state engine", "E5");
                        }
                        currentStatus--;
                }
                break;
            case MODE_SHUTDOWN:
                StData.gameIsRunning = false;
                break;
            default:
                LOG.println("[MGL] critical exception occured in state engine", "E5");
                currentMode = 10;
                currentStatus = 0;
        }
    }

    @Override
    public boolean doRepeat() {
        return true;
    }
}
