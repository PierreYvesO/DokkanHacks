package model;

import manager.ImageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static manager.ScreenPixelManager.*;


public class Map {


    private static final ArrayList<KiSphere> ROW1 = new ArrayList<>(Arrays.asList(
            new KiSphere(PX_KI_R1_P1_X, PX_KI_R1_P1_Y, PX_KI_R1_W, PX_KI_R1_H),
            new KiSphere(PX_KI_R1_P2_X, PX_KI_R1_P2_Y, PX_KI_R1_W, PX_KI_R1_H),
            new KiSphere(PX_KI_R1_P3_X, PX_KI_R1_P3_Y, PX_KI_R1_W, PX_KI_R1_H),
            new KiSphere(PX_KI_R1_P4_X, PX_KI_R1_P4_Y, PX_KI_R1_W, PX_KI_R1_H),
            new KiSphere(PX_KI_R1_P5_X, PX_KI_R1_P5_Y, PX_KI_R1_W, PX_KI_R1_H)));
    private static final ArrayList<KiSphere> ROW2 = new ArrayList<>(Arrays.asList(
            new KiSphere(PX_KI_R2_P1_X, PX_KI_R2_P1_Y, PX_KI_R2_W, PX_KI_R2_H),
            new KiSphere(PX_KI_R2_P2_X, PX_KI_R2_P2_Y, PX_KI_R2_W, PX_KI_R2_H),
            new KiSphere(PX_KI_R2_P3_X, PX_KI_R2_P3_Y, PX_KI_R2_W, PX_KI_R2_H),
            new KiSphere(PX_KI_R2_P4_X, PX_KI_R2_P4_Y, PX_KI_R2_W, PX_KI_R2_H)));
    private static final ArrayList<KiSphere> ROW3 = new ArrayList<>(Arrays.asList(
            new KiSphere(PX_KI_R3_P1_X, PX_KI_R3_P1_Y, PX_KI_R3_W, PX_KI_R3_H),
            new KiSphere(PX_KI_R3_P2_X, PX_KI_R3_P2_Y, PX_KI_R3_W, PX_KI_R3_H),
            new KiSphere(PX_KI_R3_P3_X, PX_KI_R3_P3_Y, PX_KI_R3_W, PX_KI_R3_H),
            new KiSphere(PX_KI_R3_P4_X, PX_KI_R3_P4_Y, PX_KI_R3_W, PX_KI_R3_H),
            new KiSphere(PX_KI_R3_P5_X, PX_KI_R3_P5_Y, PX_KI_R3_W, PX_KI_R3_H)));
    private static final ArrayList<KiSphere> ROW4 = new ArrayList<>(Arrays.asList(
            new KiSphere(PX_KI_R4_P1_X, PX_KI_R4_P1_Y, PX_KI_R4_W, PX_KI_R4_H),
            new KiSphere(PX_KI_R4_P2_X, PX_KI_R4_P2_Y, PX_KI_R4_W, PX_KI_R4_H),
            new KiSphere(PX_KI_R4_P3_X, PX_KI_R4_P3_Y, PX_KI_R4_W, PX_KI_R4_H),
            new KiSphere(PX_KI_R4_P4_X, PX_KI_R4_P4_Y, PX_KI_R4_W, PX_KI_R4_H)));
    private static final ArrayList<KiSphere> ROW5 = new ArrayList<>(Arrays.asList(
            new KiSphere(PX_KI_R5_P1_X, PX_KI_R5_P1_Y, PX_KI_R5_W, PX_KI_R5_H),
            new KiSphere(PX_KI_R5_P2_X, PX_KI_R5_P2_Y, PX_KI_R5_W, PX_KI_R5_H),
            new KiSphere(PX_KI_R5_P3_X, PX_KI_R5_P3_Y, PX_KI_R5_W, PX_KI_R5_H),
            new KiSphere(PX_KI_R5_P4_X, PX_KI_R5_P4_Y, PX_KI_R5_W, PX_KI_R5_H),
            new KiSphere(PX_KI_R5_P5_X, PX_KI_R5_P5_Y, PX_KI_R5_W, PX_KI_R5_H)));


    private static java.util.Map<Integer, ArrayList<KiSphere>> map = java.util.Map.of(1, ROW1, 2, ROW2, 3, ROW3, 4, ROW4, 5, ROW5);

    static java.util.Map<Integer, ArrayList<KiSphere>> getMap() {
        return map;
    }

    public static void updateMap() {
        ImageManager.getScreen();
        map.values().forEach(lki -> lki.forEach(KiSphere::update));

    }

    public static String displayMap() {
        StringBuilder sb = new StringBuilder();
        for (Integer a : new TreeSet<>(map.keySet()).descendingSet()) {
            for (KiSphere ki : map.get(a)) {
                //ImageManager.saveImage(ki.getBuffI());
                sb.append(ki.getType().name() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();


    }


}
