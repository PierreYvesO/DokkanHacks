class PixelButton extends Button {



    static final int[] FIGHT_ORANGE = {255, 255, 173};
    static final int[] END_LVL_ORANGE = {239, 178, 107};
    static final int[] END_FIGHT_ORANGE = {255, 158, 0};


    private int xPixel;
    private int yPixel;
    private int[] color;
    PixelButton(int x,int y ,int w, int h, int x_px, int y_px,int[] color){

        super(x,y,w,h);
        xPixel = x_px;
        yPixel = y_px;
        this.color = color;

    }

    boolean check(){
        return ImageManager.checkRGB(ImageManager.checkPixel(xPixel, yPixel),color);

    }


}
