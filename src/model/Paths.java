package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Paths {

    private HashMap<KiSphere, TreeSet<Path>> pathPos;

    public Paths() {
        pathPos = new HashMap<>();
        ArrayList<KiSphere> map = Map.getMap().get(1);
        pathPos.put(map.get(4), new TreeSet<>());
        pathPos.put(map.get(3), new TreeSet<>());
        pathPos.put(map.get(2), new TreeSet<>());
        pathPos.put(map.get(1), new TreeSet<>());
        pathPos.put(map.get(0), new TreeSet<>());


    }

    public void calcPaths() {
        Map.updateMap();
        for (KiSphere ki : pathPos.keySet()) {
            for (KiType type : KiType.values()) {
                Path p = new Path(ki, type);

                if (p.getValue() > 0)
                    pathPos.get(ki).add(p);
            }

        }
    }

    public KiSphere getMax() {
        int max = 0;
        KiSphere maxKi = null;

        for (KiSphere ki : pathPos.keySet()) {
            Path temp;
            try {
                temp = pathPos.get(ki).first();
                if (temp.getValue() >= max) {
                    max = temp.getValue();
                    maxKi = temp.getStartKisphere();
                }
            } catch (NoSuchElementException ignored) {

            }


        }
        clearPaths();
        return maxKi;
    }

    public KiSphere getMax(KiType type) {
        int max = 0;
        KiSphere maxKi = null;


        for (KiSphere ki : pathPos.keySet()) {
            Path temp;
            temp = pathPos.get(ki).first();
            if (temp.getValue() >= max && temp.getType() == type) {
                max = temp.getValue();
                maxKi = temp.getStartKisphere();
            }
        }

        if (maxKi == null)
            return getMax();
        else
            return maxKi;
    }

    public String getPaths() {

        StringBuilder sb = new StringBuilder();


        for (KiSphere t : pathPos.keySet()) {
            sb.append("\n" + t.getType() + "\n--");
            for (Path p : pathPos.get(t)) {
                sb.append(p.getType() + " " + p.getValue() + " ");
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public void clearPaths() {
        for (TreeSet<Path> set : pathPos.values()) {

            set.clear();
        }


    }

}
