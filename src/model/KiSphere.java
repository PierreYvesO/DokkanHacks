package model;

import manager.ImageManager;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KiSphere extends Button {

    private BufferedImage img;
    private KiType type;

    private static final Integer[] AGL_KI = new Integer[]{39, 105, 226};
    private static final Integer[] STR_KI = new Integer[]{254, 148, 170};
    private static final Integer[] TEQ_KI = new Integer[]{117, 222, 116};
    private static final Integer[] INT_KI = new Integer[]{194, 59, 229};
    private static final Integer[] RBW_KI = new Integer[]{230, 233, 195};
    private static final Integer[] PHY_KI = new Integer[]{254, 237, 130};


    KiSphere(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    private KiType findType() {

        Integer[] average = getAvgRGB();

        List<Double> l = (Arrays.asList(compareColor(PHY_KI, average), compareColor(INT_KI, average), compareColor(TEQ_KI, average), compareColor(AGL_KI, average), compareColor(STR_KI, average), compareColor(RBW_KI, average)));


        switch (l.indexOf(Collections.min(l))) {
            case 0:
                return (KiType.PHY);
            case 1:
                return (KiType.INT);
            case 2:
                return (KiType.TEQ);
            case 3:
                return (KiType.AGL);
            case 4:
                return (KiType.STR);
            default:
                return (KiType.RBW);
        }
    }

    private double compareColor(Integer[] KI_COLOR, Integer[] average) {
        double ki;

        ki = (2 * Math.pow((average[0] - KI_COLOR[0]), 2)) + (4 * Math.pow((average[1] - KI_COLOR[1]), 2)) + (3 * Math.pow((average[2] - KI_COLOR[2]), 2));

        return ki;
    }

    private Integer[] getAvgRGB() {
        Integer[] somme = {0, 0, 0};
        int nbcolor = 1;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int clr = img.getRGB(i, j);
                if ((clr & 0xff) < 230 && (clr & 0xff00 >> 8) < 230 && (clr & 0xff0000 >> 16) < 230) {
                    somme[2] += clr & 0xff;
                    somme[1] += (clr & 0xff00) >> 8;
                    somme[0] += (clr & 0xff0000) >> 16;
                    nbcolor++;
                }

            }
        }


        for (int i = 0; i < 3; i++) {
            somme[i] = somme[i] / nbcolor;
        }
        return somme;


    }

    private void getImage() {
        img = ImageManager.getBufferedImage().getSubimage(x, y, w, h);
    }

    public KiType getType() {
        return type;

    }

    private void setType(KiType type) {
        this.type = type;

    }

    void update() {

        getImage();
        setType(findType());
    }

    public BufferedImage getBuffI() {
        return img;
    }


}
