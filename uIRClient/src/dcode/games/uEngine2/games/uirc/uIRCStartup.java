package dcode.games.uEngine2.games.uirc;

import dcode.games.uEngine2.*;

import java.io.*;
import java.net.Socket;

/**
 * Created by dusakus on 24.03.15.
 */
public class uIRCStartup {
    public static void main(String[] args) {
        uGameSetup gs = new uGameSetup();
        gs.FPS = 60;
        gs.TPS_logic = 60;
        gs.TPS_MSX = 0;
        gs.TPS_BG = 10;

        gs.debug = false;
        gs.width = 520;
        gs.height = 300;
        gs.scale = 2;

        gs.spriteTableSize = 16;
        gs.enableSpriteWrappers = true;
        gs.postProcCount = 0;
        gs.spriteLayerSize = 16;


        gs.safeName = "ugametoolkit";
        gs.screenName = "uGameToolkit";
        gs.windowTitle = " DCode uE2 game toolkit 0.1 ";

        PuGameBase gb = new PuGameBase();

        gb.setup = gs;
        gb.initialInputHandler = new InHandler();
        gb.contentInitializer = new Initializer();

        Startup.StartGame(gb);
    }

    private static class Initializer extends PuGameLoader {
        @Override
        public void loadInitialGameContent() {
            StData.currentGC = new GameContainer();
            StData.logicTasks.registerBasic(new INITLOGIC());
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        StData.LOG.println("Connecting to the server...");
                        LStData.NET_SOCKET = new Socket(LStData.SRV, 6667);
                        StData.LOG.println("CONNECTED");
                        LStData.WRITE = new BufferedWriter(new OutputStreamWriter(LStData.NET_SOCKET.getOutputStream()));
                        LStData.READ = new BufferedReader(new InputStreamReader(LStData.NET_SOCKET.getInputStream()));

                        StData.LOG.println("setting nickname to " + LStData.NAME);

                        LStData.WRITE.write("NICK " + LStData.NAME + "\r\n");
                        LStData.WRITE.write("USER " + LStData.NAME + " 7 * : uIRClient r0 \r\n");
                        LStData.WRITE.flush();

                        LStData.connected = true;

                        String line = null;
                        while ((line = LStData.READ.readLine()) != null) {
                            StData.LOG.println(line);
                            if (line.indexOf("004") >= 0) {
                                StData.LOG.println("Joining channel " + LStData.CH);

                                LStData.WRITE.write("JOIN " + LStData.CH + "\r\n");
                                LStData.WRITE.flush();

                                LStData.connected = true;
                                break;
                            } else if (line.indexOf("433") >= 0) {
                                System.out.println("Nickname is already in use.");
                                return;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            };

            new Thread(r).start();
        }
    }
}
