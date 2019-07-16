import org.jetbrains.annotations.NotNull;

public class Path implements Comparable {

    KiSphere start;
    KiType type;
    int value = 0;
    int posinit;


    public Path(KiSphere start, KiType type) {

        this.start = start;
        this.type = type;

        posinit = Map.getMap().get(1).indexOf(start);
        value = getUp(1, posinit);
        if (type == KiType.RBW && value == 1 || start.getType() == KiType.RBW && value == 1)
            value = 0;
        //System.out.println("value ="+value);

    }


    public int getValue() {

        return value;
    }

    private int getSides(int line, int pos) {
        int sides = 0;
        int max;
        if (line % 2 != 0) {
            max = 5;
        } else {
            max = 4;
        }
        int temp = pos + 1;
        while (temp < max) {
            if (Map.getMap().get(line).get(temp).getType() == type) {
                sides++;
                temp++;
            } else
                break;

        }

        temp = pos - 1;
        while (temp >= 0) {
            if (Map.getMap().get(line).get(temp).getType() == type) {
                sides++;
                temp--;
            } else
                break;
        }
        return sides;
    }

    private int getUp(int line, int pos) {
        //System.out.println(line+" "+pos);
        if (pos < 0 || (line % 2 == 0 && pos > 3) || (line % 2 != 0 && pos > 4))
            return 0;

        if (Map.getMap().get(line).get(pos).getType() == type || Map.getMap().get(line).get(pos).getType() == KiType.RBW) {
            //System.out.println(line+"-"+pos);
            int sides = getSides(line, pos);
            //System.out.println("sides ="+sides);
            if (line == 5) {
                return getSides(line, pos) + 1;
            }
            if (line % 2 == 0) {
                return max(getUp(line + 1, pos), getUp(line + 1, pos + 1)) + 1 + sides;
            } else {
                return max(getUp(line + 1, pos - 1), getUp(line + 1, pos)) + 1 + sides;
            }
        } else
            return 0;

    }

    @Override
    public int compareTo(@NotNull Object o) {
        if (this.getValue() > ((Path) o).getValue()) {
            return -1;
        }
        if (this.getValue() == ((Path) o).getValue()) {
            return 0;
        }
        return 1;
    }

    /*@Override
    public boolean equals(Object obj) {
        if(compareTo(obj)==0)
            return true;
        else
            return false;
    }*/

    public KiSphere getKisphere() {
        return start;
    }

    private int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }
}

