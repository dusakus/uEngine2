package dcode.games.uEngine2.games.uirc;

import dcode.games.uEngine2.BGTasks.PBGTask;

/**
 * Created by dusakus on 31.03.15.
 */
public class NetworkReader extends PBGTask {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void perform() {
        String line;
        try {
            if ((line = LStData.READ.readLine()) != null) {
                if (line.toLowerCase().startsWith("PING ")) {
                    // We must respond to PINGs to avoid being disconnected.
                    LStData.WRITE.write("PONG " + line.substring(5) + "\r\n");
                    LStData.WRITE.flush();
                } else {
                    // Print the raw line received by the bot.
                    System.out.println(line);
                }
            }
        } catch (Exception e) {

        }
    }
}
