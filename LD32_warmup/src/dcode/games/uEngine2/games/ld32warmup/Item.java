package dcode.games.uEngine2.games.ld32warmup;

/**
 * Created by dusakus on 12.04.15.
 */
public class Item {
    public String itemName = "ERROR";
    public String itemTexture = "ERROR";

    public boolean modifyPlayerSkin = false;
    public String playerModifier = "";

    public Item(String name, String texKey, boolean modifyPlayer, String playerModKey) {
        itemName = name;
        itemTexture = texKey;
        modifyPlayerSkin = modifyPlayer;
        playerModifier = playerModKey;
    }
}
