/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.ResourceManager.DSU;

import java.util.ArrayList;

/**
 * @author dusakus
 */
public class DSU_NODE_SUBSTOR {
	ArrayList<DSU_OBJECT> objects;

	public DSU_NODE_SUBSTOR() {
		objects = new ArrayList<>();
	}

	public Object getObject(String key) {
		for (int i = 0; i < objects.size(); i++) {
			DSU_OBJECT get = objects.get(i);
			if (get.KEY.equals(key)) return get.OBJ;
		}
		return null;
	}

	public int getSize() {
		return objects.size();
	}

	void removeObject(String key) {
		for (int i = 0; i < objects.size(); i++) {
			DSU_OBJECT get = objects.get(i);
			if (get.KEY.equals(key)) objects.remove(get);
		}
	}
}
