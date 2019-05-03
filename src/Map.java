import java.util.ArrayList;
import java.util.Arrays;


class Map {


    private final ArrayList<KiSphere> ROW1 = new ArrayList<>(Arrays.asList(
            new KiSphere(60, 593, 130, 130),
            new KiSphere(260, 733, 130, 130),
            new KiSphere(448, 855, 130, 130),
            new KiSphere(672, 893, 130, 130),
            new KiSphere(893, 919, 130, 130)));
    private final ArrayList<KiSphere> ROW2 = new ArrayList<>(Arrays.asList(
            new KiSphere(269, 582, 90, 90),
            new KiSphere(440, 684, 90, 90),
            new KiSphere(614, 738, 90, 90),
            new KiSphere(806, 786, 90, 90)));
    private final ArrayList<KiSphere> ROW3 = new ArrayList<>(Arrays.asList(
            new KiSphere(336, 487, 60, 60),
            new KiSphere(420, 571, 60, 60),
            new KiSphere(559, 638, 60, 60),
            new KiSphere(714, 664, 60, 60),
            new KiSphere(840, 672, 60, 60)));
    private final ArrayList<KiSphere> ROW4 = new ArrayList<>(Arrays.asList(
            new KiSphere(446, 498, 45, 45),
            new KiSphere(531, 564, 45, 45),
            new KiSphere(646, 602, 45, 45),
            new KiSphere(758, 613, 45, 45)));
    private final ArrayList<KiSphere> ROW5 = new ArrayList<>(Arrays.asList(
            new KiSphere(477, 446, 35, 35),
            new KiSphere(541, 492, 35, 35),
            new KiSphere(619, 529, 35, 35),
            new KiSphere(706, 563, 35, 35),
            new KiSphere(777, 557, 35, 35)));

    private java.util.Map<Integer, ArrayList<KiSphere>> map = java.util.Map.of(1, ROW1, 2, ROW2, 3, ROW3, 4, ROW4, 5, ROW5);


    void updateMap() {
        map.values().forEach(lki -> lki.forEach(KiSphere::update));

    }

    KiSphere getMaxKi() {

        //System.out.println(map.get(1).get(0).getType());

        int max = 0;
        KiSphere maxki = null;
        // KiSphere ki = map.get(1).get(1);
        for (KiSphere ki : map.get(1)) {
            int pathcount = getPath(ki, ki.getType());

            if (pathcount > max) {
                max = pathcount;
                maxki = ki;
            }

            System.out.println("PathCOunt : " + (pathcount));
        }
        return maxki;

    }

    KiSphere getMaxKi(String type) {
        int max = 0;
        KiSphere maxki = null;

        for (KiSphere ki : map.get(1)) {
            if (ki.getType().equals(type)) {
                int pathcount = getPath(ki, ki.getType());
                if (pathcount > max) {
                    max = pathcount;
                    maxki = ki;
                }
            }

        }
        if (maxki != null)
            return maxki;
        else
            return getMaxKi();
    }

    void getRow(int r) {
        updateMap();
        int i = 0;
        StringBuilder ligne = new StringBuilder();
        for (KiSphere ki : map.get(r)) {
            ligne.append(" ").append(ki.getType());
            //System.out.println(Arrays.asList(ki.getAvgRGB()).toString());
            ImageManager.saveImage(ki.getBuffI(), "save\\" + r + " " + i);
            i++;
        }
        System.out.println(ligne + "\n");
    }

    private int getSides(KiSphere ki, String type) {
        //System.out.println("getSides");
        int row = getRow(ki);
        int col = getCollumn(ki);
        int side = 0;
        int maxcol;
        int mincol = 0;
        if (row % 2 == 0)
            maxcol = 3;
        else
            maxcol = 4;

        int sidecol = col + 1;

        while (sidecol <= maxcol) {
            //System.out.println("Side testRight  :" +sidecol + " type : " + getKiSphere(row, sidecol).getType() );
            if (getKiSphere(row, sidecol).getType().equals(type) && !type.equals("rbw")) {
                System.out.println("Side : " + row + "   " + (sidecol + 1));
                side++;
                sidecol++;
            } else {
                sidecol = maxcol + 1;
            }

        }

        sidecol = col - 1;
        while (sidecol >= mincol) {
            //System.out.println("Side testLeft  :" + getKiSphere(row, sidecol).getType());
            if (getKiSphere(row, sidecol).getType().equals(type) && !type.equals("rbw")) {
                System.out.println("Side : " + row + "   " + (sidecol + 1));
                side++;
                sidecol--;

            } else {
                sidecol = mincol - 1;
            }

        }
        //System.out.println("Side Count : " + side);
        return side;
    }


    private int getPath(KiSphere ki, String type) {
        //System.out.println("getPath");
        if (getRow(ki) == 5) {
            //System.out.println("5th row");
            return getSides(ki, type) + 1;
        }
        KiSphere next;
        if (ki.getType().equals("rbw") && type.equals("rbw")) {
            next = getNext(ki, ki.getType());
        } else {
            next = getNext(ki, type);
        }
        if (next != null) {
            //System.out.println("nextpath : " + getPath(next, type)+" , "+getSides(ki, type));
            if (!next.getType().equals("rbw") && type.equals("rbw")) {
                type = next.getType();
            }
            return getPath(next, type) + getSides(ki, type) + 1;
        } else
            return 1 + getSides(ki, type);

    }

    private int getRow(KiSphere ki) {
        //System.out.println("getRow");
        for (Integer row : map.keySet())
            if (map.get(row).contains(ki))
                return row;

        System.out.println("erreur");
        return 0;
    }

    private int getCollumn(KiSphere ki) {

        return map.get(getRow(ki)).indexOf(ki);
    }

    private KiSphere getNext(KiSphere ki, String type) {
        //System.out.println("getNext");
        int row = getRow(ki);
        int col = getCollumn(ki);
        KiSphere nextKi1;
        KiSphere nextKi2;
        System.out.println(row + " " + (col + 1));
        if (row == 5)
            return null;
        if (row % 2 != 0) {

            if (col == 0 || col == 4) {
                int colnext;
                if (col == 0)
                    colnext = 0;
                else
                    colnext = 3;

                KiSphere nextKi = getKiSphere(row + 1, colnext);
                if (type.equals("rbw")) {
                    return nextKi;
                }
                if (nextKi.getType().equals(type) || nextKi.getType().equals("rbw")) {
                    return nextKi;
                } else
                    return null;
            } else {
                nextKi1 = getKiSphere(row + 1, col - 1);
                nextKi2 = getKiSphere(row + 1, col);
            }
        } else {
            nextKi1 = getKiSphere(row + 1, col);
            nextKi2 = getKiSphere(row + 1, col + 1);

        }//Si il n'y a pas de suite
        if ((!nextKi1.getType().equals(type) && !nextKi1.getType().equals("rbw")) && (!nextKi2.getType().equals(type) && !nextKi2.getType().equals("rbw")))
            return null;
            //Si il a une suite sur le Ki 1 seuelment
        else if ((nextKi1.getType().equals(type) || nextKi1.getType().equals("rbw")) && (!nextKi2.getType().equals(type) && !nextKi2.getType().equals("rbw"))) {
            return nextKi1;
            //Si il a une suite sur le Ki 2 seuelment
        } else if ((!nextKi1.getType().equals(type) && !nextKi1.getType().equals("rbw")) && (nextKi2.getType().equals(type) || nextKi2.getType().equals("rbw"))) {
            return nextKi2;
            //Si il y a une suite possible sur les 2
        } else {

            if (type.equals("rbw")) {
                if (getPath(nextKi1, nextKi1.getType()) >= getPath(nextKi2, nextKi2.getType()))
                    return nextKi1;
                else
                    return nextKi2;


            }
            if (getPath(nextKi1, type) >= getPath(nextKi2, type))
                return nextKi1;
            else
                return nextKi2;

        }

    }

    private KiSphere getKiSphere(int row, int col) {
        //System.out.println("getKiSphere");
        return map.get(row).get(col);

    }


}
