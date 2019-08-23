package manager;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;


public class ImageManager {
    private static IDevice device;
    private static RawImage rawImage;
    private static BufferedImage buffImg = null;
    static int countImg = 0;

    public static void setDevice(IDevice d) {
        device = d;
    }

    public static void getScreen() {
        RawImage screen = null;
        try {
            screen = device.getScreenshot();
        } catch (AdbCommandRejectedException | TimeoutException | IOException e) {
            e.getMessage();
        }
        rawImage = screen;
    }

    public static int[] getPixel(int x, int y) {

        int w = rawImage.width;
        int value = rawImage.getARGB(y * w * 4 + x * 4 + 4);

        return new int[]{new Color(value).getRed(),
                new Color(value).getGreen(),
                new Color(value).getBlue()};
    }

    public static BufferedImage getBufferedImage() {
        if (buffImg != null) {
            return buffImg;
        } else {

            BufferedImage image = new BufferedImage(rawImage.width, rawImage.height,
                    BufferedImage.TYPE_INT_ARGB);

            int index = 0;
            int IndexInc = rawImage.bpp >> 3;
            for (int y = 0; y < rawImage.height; y++) {
                for (int x = 0; x < rawImage.width; x++) {
                    int value = rawImage.getARGB(index);
                    index += IndexInc;
                    image.setRGB(x, y, value);
                }
            }

            return image;
        }
    }


    public static boolean checkRGB(int[] sampleColor, int[] expectedColor) {
        double colorDist;

        //System.out.println(Arrays.toString(sampleColor));
        //System.out.println(Arrays.toString(expectedColor));

        colorDist = (2 * Math.pow((sampleColor[0] - expectedColor[0]), 2)) + (4 * Math.pow((sampleColor[1] - expectedColor[1]), 2)) + (3 * Math.pow((sampleColor[2] - expectedColor[2]), 2));
        //System.out.println(colorDist+"\n");

        return colorDist < 2000;
    }

    public static void saveScreenshot() {

        try {

            BufferedImage bi = getBufferedImage();
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImage(BufferedImage bis) {

        try {

            File outputfile = new File("image" + countImg + ".png");
            countImg++;
            ImageIO.write(bis, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void loadTestImage() {


        try {
            buffImg = ImageIO.read(new File("ki2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static BufferedImage processBI(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                if (isColor(img.getRGB(i, j), new Color(255, 154, 0)) && !(i < 15 && j < 7) && !(i < 5 && j < 30)) {
                    img.setRGB(i, j, Color.BLACK.getRGB());
                } else
                    img.setRGB(i, j, Color.WHITE.getRGB());
            }

        }

        Kernel kernel = new Kernel(3, 3, new float[]{1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f});
        BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        img = op.filter(img, null);
        //saveImage(img);
        return img;

    }

    private static boolean isColor(int rgb, Color color) {

        Color color1 = new Color(rgb);

        double distance = (color1.getRed() - color.getRed()) * (color1.getRed() - color.getRed()) +
                (color1.getGreen() - color.getGreen()) * (color1.getGreen() - color.getGreen()) +
                (color1.getBlue() - color.getBlue()) * (color1.getBlue() - color.getBlue());
        return distance < 1000;


    }
}
