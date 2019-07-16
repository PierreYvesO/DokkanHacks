import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


class Map {


    public static final ArrayList<KiSphere> ROW1 = new ArrayList<>(Arrays.asList(
            new KiSphere(60, 593, 130, 130),
            new KiSphere(260, 733, 130, 130),
            new KiSphere(448, 855, 130, 130),
            new KiSphere(672, 893, 130, 130),
            new KiSphere(893, 919, 130, 130)));
    private static final ArrayList<KiSphere> ROW2 = new ArrayList<>(Arrays.asList(
            new KiSphere(269, 582, 90, 90),
            new KiSphere(440, 684, 90, 90),
            new KiSphere(614, 738, 90, 90),
            new KiSphere(806, 786, 90, 90)));
    private static final ArrayList<KiSphere> ROW3 = new ArrayList<>(Arrays.asList(
            new KiSphere(336, 487, 60, 60),
            new KiSphere(420, 571, 60, 60),
            new KiSphere(559, 638, 60, 60),
            new KiSphere(714, 664, 60, 60),
            new KiSphere(840, 672, 60, 60)));
    private static final ArrayList<KiSphere> ROW4 = new ArrayList<>(Arrays.asList(
            new KiSphere(446, 498, 45, 45),
            new KiSphere(531, 564, 45, 45),
            new KiSphere(646, 602, 45, 45),
            new KiSphere(758, 613, 45, 45)));
    private static final ArrayList<KiSphere> ROW5 = new ArrayList<>(Arrays.asList(
            new KiSphere(477, 446, 35, 35),
            new KiSphere(541, 492, 35, 35),
            new KiSphere(619, 529, 35, 35),
            new KiSphere(706, 563, 35, 35),
            new KiSphere(777, 557, 35, 35)));


    private static java.util.Map<Integer, ArrayList<KiSphere>> map = java.util.Map.of(1, ROW1, 2, ROW2, 3, ROW3, 4, ROW4, 5, ROW5);

    public static java.util.Map<Integer, ArrayList<KiSphere>> getMap() {
        return map;
    }

    static void updateMap() {
        map.values().forEach(lki -> lki.forEach(KiSphere::update));

    }

    static void displayMap() {
        for (Integer a : new TreeSet<>(map.keySet()).descendingSet()) {
            for (KiSphere ki : map.get(a)) {
                System.out.print(ki.getType().name() + " ");
            }
            System.out.println();

        }



    }



}
