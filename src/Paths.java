import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Paths {

    private HashMap<KiType, TreeSet<Path>> paths;

    Paths() {
        paths = new HashMap<>();
        paths.put(KiType.PHY, new TreeSet<>());
        paths.put(KiType.INT, new TreeSet<>());
        paths.put(KiType.TEQ, new TreeSet<>());
        paths.put(KiType.AGL, new TreeSet<>());
        paths.put(KiType.STR, new TreeSet<>());
        paths.put(KiType.RBW, new TreeSet<>());


    }

    void calcPaths() {
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

    KiSphere getMax() {
        int max = 0;
        KiSphere maxKi = null;

        for (KiType type : KiType.values()) {
            Path temp;
            try {
                temp = paths.get(type).first();
                if (temp.getValue() >= max) {
                    max = temp.getValue();
                    maxKi = temp.getKisphere();
                }
            } catch (NoSuchElementException ignored) {

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
