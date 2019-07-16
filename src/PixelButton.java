class PixelButton extends Button {


    static final int[] FIGHT_ORANGE = {255, 170, 16};
    static final int[] FIGHT_GREEN = {0, 150, 0};
    static final int[] FIGHT_BLUE = {58, 186, 255};
    static final int[] FIGHT_RED = {206, 32, 32};
    static final int[] END_LVL_ORANGE = {239, 178, 107};
    static final int[] END_FIGHT_ORANGE = {255, 158, 0};
    static final int[] MULTIPATH_BLUE = {14, 129, 226};
    static final int[] START_LVL_GREY = {66, 66, 74};
    static final int[] START_LVL_CLEARED = {8, 219, 255};


    int xPixel;
    int yPixel;
    private int[] color;

    PixelButton(int x, int y, int w, int h, int x_px, int y_px, int[] color) {

        super(x, y, w, h);
        xPixel = x_px;
        yPixel = y_px;
        this.color = color;

    }

    boolean check() {

        return ImageManager.checkRGB(ImageManager.getPixel(xPixel, yPixel), color);

    }


}
