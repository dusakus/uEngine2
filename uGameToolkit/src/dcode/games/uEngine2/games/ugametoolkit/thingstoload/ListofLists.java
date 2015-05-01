package dcode.games.uEngine2.games.ugametoolkit.thingstoload;

import dcode.games.uEngine2.StData;

import java.util.LinkedList;

/**
 * Created by dusakus on 4/29/15.
 */
public class ListofLists {
    private LinkedList<Thingy> things = new LinkedList<Thingy>();
    private LinkedList<Thingy> finishedThings = new LinkedList<Thingy>();

    public static ListofLists instance;

    public void fillList() {
        things.offer(new Thingy(new LoadBanners()));
        things.offer(new Thingy(new LoadFonts()));
        things.offer(new Thingy(new LoadUI()));
        things.offer(new Thingy(new LoadBackgrounds()));
        things.offer(new Thingy(new LoadCursors()));


    }

    public boolean performNext() {
        if (things.peek().perform()) {
            finishedThings.offer(things.poll());
        } else {
            things.offer(things.poll());
        }
        return things.isEmpty();
    }

    public boolean isFinished() {
        Thingy toRemove = null;
        for (Thingy t : finishedThings) {
            if (t.isDone()) toRemove = t;
        }
        if (toRemove != null) finishedThings.remove(toRemove);
        return finishedThings.isEmpty();
    }

    private class Thingy {
        public ILoadMe toLoad = null;
        public long startedAt = 0L;
        public int retries = 0;

        public Thingy(ILoadMe toLoad) {
            this.toLoad = toLoad;
            startedAt = System.nanoTime();
        }

        public boolean perform() {
            try {
                toLoad.loadDys();
                return true;
            } catch (Exception E) {
                StData.LOG.println("[uGT-Loader] Thingy " + toLoad.getClass().getName() + " generated an exception, retries count: " + retries);
                retries++;
                return retries > 8;
            }
        }

        public boolean isDone() {
            if (System.nanoTime() - startedAt > 1000000) return true;
            return toLoad.checkFinished();
        }
    }
}
