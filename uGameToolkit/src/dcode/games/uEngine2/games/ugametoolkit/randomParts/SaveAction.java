package dcode.games.uEngine2.games.ugametoolkit.randomParts;

import java.io.File;

/**
 * Created by dusakus on 23.04.15.
 */
public interface SaveAction {

    boolean checkFile(File f);

    boolean saveToFile(File f);
}
