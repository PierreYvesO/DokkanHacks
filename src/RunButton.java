import java.awt.image.BufferedImage;
import java.util.Arrays;

class RunButton extends PixelButton {

    private BufferedImage img;
    private int value;
    private final Integer[] BLACK_VALUES;
    private boolean updated = false;
    private int[] color;
    private static final int[] RUN_BUTTON_ORANGE = {251, 111, 0};
    private static final int[] RUN_BUTTON_GREY = {99, 97, 99};
    private static final int[] RUN_BUTTON_TRANSITION = {255, 255, 173};


    RunButton(int x, int y, int w, int h, int xpx, int ypx, Integer[] BLACK_VALUES) {
        super(x, y, w, h, xpx, ypx, null);
        this.BLACK_VALUES = BLACK_VALUES;
    }


    private void getImage() {
        img = ImageManager.getBufferedImage().getSubimage(x, y, w, h);
    }

    private int checkBlackLevel() {
        int count = 0;


        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {

                int pixel = img.getRGB(j, i);

                if ((pixel & 0x00FFFFFF) == 0)
                    count++;
            }
        }

        return count;
    }

    private void setValue() {

        value = Arrays.asList(BLACK_VALUES).indexOf(checkBlackLevel()) + 1;
    }

    int getValue() {
        return value;
    }

    void update() {
        getImage();
        setValue();
        updated = true;
    }

    void needUpdate() {
        updated = false;
    }

    boolean updateStatus() {

        return !updated;
    }

    String getColor() {

        if (color == RUN_BUTTON_ORANGE) {
            return "orange";
        } else {
            return "grey";
        }
    }


    @Override
    boolean check() {
        boolean orange, grey = false;
        if ((orange = ImageManager.checkRGB(ImageManager.getPixel(xPixel, yPixel), RUN_BUTTON_ORANGE)) ||
                (grey = ImageManager.checkRGB(ImageManager.getPixel(xPixel, yPixel), RUN_BUTTON_GREY)) ||
                (ImageManager.checkRGB(ImageManager.getPixel(xPixel, yPixel), RUN_BUTTON_TRANSITION))) {

            //System.out.println(orange);
            //System.out.println(grey);

            if (orange)
                color = RUN_BUTTON_ORANGE;
            else if (grey)
                color = RUN_BUTTON_GREY;
            else
                color = RUN_BUTTON_TRANSITION;
            return true;
        } else
            return false;
    }
}
