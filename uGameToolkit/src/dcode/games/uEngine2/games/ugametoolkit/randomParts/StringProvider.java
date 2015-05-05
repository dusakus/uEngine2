package dcode.games.uEngine2.games.ugametoolkit.randomParts;

/**
 * Created by dusakus on 05.05.15.
 */
public abstract class StringProvider {

    private String lastVal = "NONE";

    public String getValue() {
        lastVal = updateSource();
        return lastVal;
    }

    public String getLAstValue() {
        return lastVal;
    }

    public boolean hasChanged() {
        return !lastVal.equals(updateSource());
    }

    public abstract String updateSource();
}
