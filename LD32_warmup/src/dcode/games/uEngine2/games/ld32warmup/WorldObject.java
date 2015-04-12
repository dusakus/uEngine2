package dcode.games.uEngine2.games.ld32warmup;

public class WorldObject {
    String texKey = "UNKNOWN";
    String texSource = "...";
    int spritePosition = 5;

    int x = 0;
    int y = 0;

    public WorldObject(String source, String key, int depth, int XPos, int YPos) {
        texKey = key;
        texSource = source;
        spritePosition = depth;

        x = XPos;
        y = YPos;
    }
}
