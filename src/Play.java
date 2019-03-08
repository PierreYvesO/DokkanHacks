import com.android.ddmlib.*;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;


import static java.lang.Thread.*;

class Play {
    private int lvl;
    private IDevice device;
    private RawImage rawImage;
    private String state = "start";
    private int waitTimes = 0;
    /*
     * 1 -  X  _  _
     * 2 -  _  X  _
     * 3 -  _  _  X
     * 4 -   X   _
     * 5 -   _   X
     * */
    private final int[][] POS_LVL = new int[][]{{190, 1318}, {575, 1318}, {870, 1318}, {360, 1318}, {700, 1318}};

    // 1 Orange 2 Gris
    private final int[][] COLOR_RUN = new int[][]{{255, 101, 0}, {99, 97, 99}, {255, 255, 173}, {239, 178, 107}, {247, 170, 16},{255, 158, 0}};

    private final int[][] multiPath = {{370, 880}, {370, 880}, {370, 880}, {370, 880}};

    Play(int lvl, IDevice device) {
        this.lvl = lvl;
        this.device = device;
    }

    boolean checkState() throws Exception {

        //Recupere l'image actuelle a l'ecran
        rawImage = this.getScreen();
        //Partie Selection du niveau et ami
        if (state.equals("start")) {
            levelChooser();
            state = "run";
            return checkState();
        }
        //Partie du parcours de la carte
        if (state.equals("run")) {
            int[] px = checkPixel(842, 1585);
            if (!(checkRGB(px, COLOR_RUN[0]) || checkRGB(px, COLOR_RUN[1]))) {
                if (checkRGB(checkPixel(436, 1796), COLOR_RUN[3])) {
                    tap(436, 1796);
                    wait(1500);
                    tap(155,1234);
                    System.out.println("End Lvl");
                    wait(10000);
                    state = "start";
                } else {
                    state = "fight";
                    System.out.println("Fighting...");
                }
            } else {
                if (checkRGB(px, COLOR_RUN[0])) {
                    System.out.println("Orange recognized...");
                    tap(842, 1585);
                    wait(2000);
                    waitTimes = 0;
                }
                if (checkRGB(px, COLOR_RUN[1]) || checkRGB(px, COLOR_RUN[2])) {
                    System.out.println("Grey recognized...");
                    if (waitTimes > 2) {
                        System.out.println("MultiPath recognized...");
                        tap(multiPath[0][0], multiPath[0][1]);
                        tap(multiPath[1][0], multiPath[1][1]);
                        tap(multiPath[2][0], multiPath[2][1]);
                        tap(multiPath[3][0], multiPath[3][1]);
                        wait(2000);
                        waitTimes = 0;
                    } else {
                        wait(2000);
                        waitTimes++;
                    }
                }
            }
            return checkState();
        }
        //Partie combat
        if (state.equals("fight")) {
            if(checkRGB(checkPixel(263,963), COLOR_RUN[5])){
                tap(535, 935);
                wait(1500);
                tap(535, 935);
                wait(1500);
                tap(535, 935);
                wait(3000);
                System.out.println("End Fighting ...");

            }
            else if (!checkRGB(checkPixel(980, 233), COLOR_RUN[4])) {
                state = "run";
                wait(5000);
                return checkState();
            }

            else{tap(535, 935);
            wait(3000);
            tap(535, 935);
            wait(3000);
            tap(535, 935);}
            wait(3000);

            state = "run";
            return checkState();
        }
        return true;
    }


    private void wait(int time) throws InterruptedException {
        sleep(time);
    }

    private RawImage getScreen() {
        RawImage screen = null;
        try {
            screen = device.getScreenshot();
        } catch (AdbCommandRejectedException e) {
            e.getMessage();
        } catch (TimeoutException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        return screen;
    }

    private int[] checkPixel(int x, int y) {

        int w = rawImage.width;
        int value = rawImage.getARGB(y * w * 4 + x * 4 + 4);

        return new int[]{new Color(value).getRed(),
                new Color(value).getGreen(),
                new Color(value).getBlue()};
    }


    private void tap(int x, int y) {
        try {
            device.executeShellCommand("input touchscreen tap " + x + " " + y, new IShellOutputReceiver() {
                public void addOutput(byte[] data, int offset, int length) {
                }

                public void flush() {
                }

                public boolean isCancelled() {
                    return false;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void levelChooser() throws InterruptedException {
        System.out.println("clic niveau");
        tap(POS_LVL[0][0], POS_LVL[0][1]);
        sleep(4000);
        System.out.println("clic difficulté");
        tap(POS_LVL[lvl][0], POS_LVL[lvl][1]);
        sleep(4000);
        System.out.println("clic ami"); //517 713
        tap(517, 713);
        sleep(4000);
        System.out.println("clic demarrer");//907 1582
        tap(907, 1582);
        sleep(8000);
    }


    private boolean checkRGB(int[] sampleColor, int[] expectedColor) {
        double somme = 0;
        //System.out.println(Arrays.toString(sampleColor));
        //System.out.println(Arrays.toString(expectedColor));

        for (int i = 0; i < 3; i++) {
            somme += Math.abs(sampleColor[i] - expectedColor[i]);
        }
        //System.out.println(somme);
        somme = somme / 3.0 / 255.0;
        //System.out.println(somme);
        return somme < .1;
    }
}
