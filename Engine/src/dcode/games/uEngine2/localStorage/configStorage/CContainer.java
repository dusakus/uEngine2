package dcode.games.uEngine2.localStorage.configStorage;

import dcode.games.uEngine2.StData;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by dusakus on 31.05.15.
 *
 *
 */
public class CContainer {

    public File fileLocation;
    public String configHeader;
    private CLeaf missingLeaf;

    private CBranch master;


    //Creates new empty container
    public CContainer() {
        master = new CBranch("MASTER");
    }

    //Creates container from existing file
    public CContainer(File f) {

    }

    //Creates container from internal template
    public CContainer(URL u) {

    }

    private CBranch getBranch(String key, boolean create) {
        String[] keys;
        CBranch currentTarget = master;

        //grab key list
        if (key.contains(".")) keys = key.split(".");
        else if (key.contains("/")) keys = key.split(".");
        else { //single step key is still a list...
            keys = new String[1];
            keys[0] = key;
        }

        try {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == null) break;
                currentTarget = currentTarget.getBranch(keys[i], create);
            }
        } catch (Exception NullPointerException) {

        }

        return currentTarget;
    }

    private CLeaf getLeaf(String key, boolean create) {
        String leafName;
        if (key.contains(".")) {
            leafName = key.substring(key.lastIndexOf('.'), key.length());
            key = key.substring(0, key.lastIndexOf('.'));
        } else if (key.contains("/")) {
            leafName = key.substring(key.lastIndexOf('/'), key.length());
            key = key.substring(0, key.lastIndexOf('/'));
        } else { //single step key is still a list...
            leafName = key;
            key = "this";
        }

        return getBranch(key, create).getLeaf(leafName, create);
    }

    public int getInt(String key, int DEFAULT) {
        CLeaf cl = getLeaf(key, false);
        if (cl == null) {
            StData.LOG.println("[CONFIG] Integer Leaf at " + key + " not found, creating", "E2");
            cl = getLeaf(key, true);
            cl.setInt(DEFAULT);
        }
        if (!cl.hasIntSet) {
            StData.LOG.println("[CONFIG] Leaf at " + key + " found, but no integer is set, using default", "E2");
            cl.setInt(DEFAULT);
        }
        return cl.VALUE_INT;
    }
    public String getString(String key, String DEFAULT) {
        CLeaf cl = getLeaf(key, false);
        if (cl == null) {
            StData.LOG.println("[CONFIG] String Leaf at " + key + " not found, creating", "E2");
            cl = getLeaf(key, true);
            cl.setString(DEFAULT);
        }
        if (!cl.hasStringSet) {
            StData.LOG.println("[CONFIG] Leaf at " + key + " found, but no string is set, using default", "E2");
            cl.setString(DEFAULT);
        }
        return cl.VALUE_STR;
    }
    public float getFloat(String key, float DEFAULT) {
        CLeaf cl = getLeaf(key, false);
        if (cl == null) {
            StData.LOG.println("[CONFIG] Float Leaf at " + key + " not found, creating", "E2");
            cl = getLeaf(key, true);
            cl.setFloat(DEFAULT);
        }
        if (!cl.hasFloatSet) {
            StData.LOG.println("[CONFIG] Leaf at " + key + " found, but no float is set, using default", "E2");
            cl.setFloat(DEFAULT);
        }
        return cl.VALUE_FLOAT;
    }
    public boolean getBool(String key, boolean DEFAULT) {
        CLeaf cl = getLeaf(key, false);
        if (cl == null) {
            StData.LOG.println("[CONFIG] Boolean Leaf at " + key + " not found, creating", "E2");
            cl = getLeaf(key, true);
            cl.setBool(DEFAULT);
        }
        if (!cl.hasBoolSet) {
            StData.LOG.println("[CONFIG] Leaf at " + key + " found, but no boolean is set, using default", "E2");
            cl.setBool(DEFAULT);
        }
        return cl.VALUE_BOOL;
    }


    private abstract class CObject {
        protected String key;

        public abstract boolean isLeaf();

        public boolean checkKey(String s) {
            return key.equals(s);
        }
    }

    private class CBranch extends CObject {
        protected String header;

        private LinkedList<CObject> obs;

        public CBranch(String k) {
            key = k;
            obs = new LinkedList<CObject>();
        }

        public CBranch getBranch(String key, boolean allowCreation) {
            if (key.equalsIgnoreCase("THIS")) return this;
            for (CObject c : obs) {
                if (!c.isLeaf() && c.checkKey(key)) return (CBranch) c;
            }
            if (allowCreation) {
                obs.add(new CBranch((key)));
                return getBranch(key, false);
            }
            return null;
        }

        public CLeaf getLeaf(String key, boolean allowCreation) {
            for (CObject c : obs) {
                if (c.isLeaf() && c.checkKey(key)) return (CLeaf) c;
            }
            if (allowCreation) {
                obs.add(new CLeaf((key)));
                return getLeaf(key, false);
            }
            return null;
        }

        @Override
        public boolean isLeaf() {
            return false;
        }
    }

    private class CLeaf extends CObject {

        //INFO
        protected String INT_comment;
        protected String FLOAT_comment;
        protected String STR_comment;
        protected String BOOL_comment;

        //valus set
        protected boolean hasIntSet = false;
        protected boolean hasBoolSet = false;
        protected boolean hasStringSet = false;
        protected boolean hasFloatSet = false;

        //Values
        protected int VALUE_INT = -1;
        protected float VALUE_FLOAT = -1.0f;
        protected String VALUE_STR = "ERR";
        protected boolean VALUE_BOOL = false;

        public CLeaf(String k) {
            key = k;
        }

        public void setInt(int i) {
            VALUE_INT = i;
            hasIntSet = true;
        }

        public void setString(String i) {
            VALUE_STR = i;
            hasStringSet = true;
        }

        public void setFloat(float i) {
            VALUE_FLOAT = i;
            hasFloatSet = true;
        }

        public void setBool(boolean i) {
            VALUE_BOOL = i;
            hasBoolSet = true;
        }

        @Override
        public boolean isLeaf() {
            return true;
        }
    }

}
