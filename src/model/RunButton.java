package model;

import manager.ImageManager;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class RunButton extends PixelButton {

    private BufferedImage img;
    private Tesseract tesseract;
    private int value;
    private boolean updated = false;
    private int[] color;
    private static final int[] RUN_BUTTON_ORANGE = {251, 111, 0};
    private static final int[] RUN_BUTTON_GREY = {99, 97, 99};
    private static final int[] RUN_BUTTON_TRANSITION = {255, 255, 173};


    public RunButton(int x, int y, int w, int h, int xpx, int ypx) {
        super(x, y, w, h, xpx, ypx, null);
        tesseract = new Tesseract();
        tesseract.setLanguage("digits");
        tesseract.setDatapath(".\\ressources");

    }


    private void getImage() {

        img = ImageManager.getBufferedImage().getSubimage(x + 15, y + 10, 70, 70);
    }

    private void setValue() {
        String text = "";

        try {
            text = tesseract.doOCR(ImageManager.processBI(img));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        text = text.replace("7", "1");
        text = text.replaceAll("[\\r\\n]", "");

        value = Integer.parseInt(text);
    }

    public int getValue() {
        return value;
    }

    public void update() {
        getImage();
        setValue();
        updated = true;
    }

    public void needUpdate() {
        updated = false;
    }

    public boolean updateStatus() {

        return !updated;
    }

    public String getColor() {

        if (color == RUN_BUTTON_ORANGE) {
            return "orange";
        } else {
            return "grey";
        }
    }


    @Override
    public boolean check() {
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

    @Override
    public void tapIn() {
        super.tapIn();
        updated = false;
    }
}
