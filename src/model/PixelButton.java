package model;

import manager.ImageManager;
import manager.ScreenPixelManager;

public class PixelButton extends Button {


    public static final int[] FIGHT_ORANGE = {255, 170, 16};
    public static final int[] FIGHT_GREEN = {0, 150, 0};
    public static final int[] FIGHT_BLUE = {58, 186, 255};
    public static final int[] FIGHT_RED = {206, 32, 32};
    public static final int[] END_LVL_ORANGE = {239, 178, 107};
    public static final int[] END_FIGHT_ORANGE = {255, 158, 0};
    public static final int[] MULTIPATH_BLUE = {14, 129, 226};
    public static final int[] START_LVL_STAR_1 = {255, 255, 41};
    public static final int[] START_LVL_STAR_2 = {255, 235, 0};
    public static final int[] START_LVL_CLEARED = {8, 219, 255};
    public static final int[] SKIP = {181, 210, 115};


    int xPixel;
    int yPixel;
    private int[] color;

    public PixelButton(int[] x, int[] y, int[] w, int[] h, int[] x_px, int[] y_px, int[] color) {

        super(x, y, w, h);
        xPixel = x_px[ScreenPixelManager.screenResolution];
        yPixel = y_px[ScreenPixelManager.screenResolution];
        this.color = color;

    }

    public boolean check() {

        return ImageManager.checkRGB(ImageManager.getPixel(xPixel, yPixel), color);

    }


}
