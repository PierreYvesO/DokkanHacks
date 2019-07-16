import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Paths {

    HashMap<KiType, TreeSet<Path>> paths;

    public Paths() {
        paths = new HashMap<>();
        paths.put(KiType.PHY, new TreeSet<>());
        paths.put(KiType.INT, new TreeSet<>());
        paths.put(KiType.TEQ, new TreeSet<>());
        paths.put(KiType.AGL, new TreeSet<>());
        paths.put(KiType.STR, new TreeSet<>());
        paths.put(KiType.RBW, new TreeSet<>());


    }

    public void calcPaths() {
        Map.updateMap();
        for (KiSphere ki : Map.getMap().get(1)) {
            for (KiType type : KiType.values()) {
                Path p = new Path(ki, type);
                if (p.getValue() > 0)
                    paths.get(type).add(p);
            }
            System.out.println();
        }
    }

    public KiSphere getMax() {
        int max = 0;
        KiSphere maxKi = null;

        for (KiType type : KiType.values()) {
            Path temp = null;
            try {
                temp = paths.get(type).first();
                if (temp.getValue() >= max) {
                    max = temp.getValue();
                    maxKi = temp.getKisphere();
                }
            } catch (NoSuchElementException e) {

            }


        }
        return maxKi;
    }

    public KiSphere getMax(KiType type) {
        try {
            return paths.get(type).first().getKisphere();
        } catch (NoSuchElementException e) {
            return getMax();
        }
    }

    public void getPaths() {

        for (KiType t : paths.keySet()) {
            System.out.println(t);
            for (Path p : paths.get(t)) {
                System.out.print(p.getKisphere().getType().name() + " " + p.getValue() + " ");
            }
            System.out.println();

        }
    }
}
