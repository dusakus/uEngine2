package dcode.games.uEngine2.games.uirc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

/**
 * Created by dusakus on 09.02.15.
 */
public class LStData {

    public static String SRV = "irc.esper.net";
    public static String NAME = "dusakus|clientTest";
    public static String CH = "#mDiyo";

    public static Socket NET_SOCKET;
    public static BufferedReader READ;
    public static BufferedWriter WRITE;
    public static java.lang.StringBuilder currentInput = new StringBuilder();

    public static boolean connected = false;

    public String[] lastMSGs = new String[16];

}

