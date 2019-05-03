import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

class ImageManager {
    private static IDevice device;
    private static RawImage rawImage;
    private static BufferedImage buffImg = null;

    static void setDevice(IDevice d){
        device = d;
    }

    static void getScreen() {
        RawImage screen = null;
        try {
            screen = device.getScreenshot();
        } catch (AdbCommandRejectedException | TimeoutException | IOException e) {
            e.getMessage();
        }
        rawImage = screen;
    }

    static int[] checkPixel(int x, int y) {

        int w = rawImage.width;
        int value = rawImage.getARGB(y * w * 4 + x * 4 + 4);

        return new int[]{new Color(value).getRed(),
                new Color(value).getGreen(),
                new Color(value).getBlue()};
    }
    
    static BufferedImage getBufferedImage() {
        if(buffImg != null){
            return buffImg;
        }else{

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

        return image;}
    }
    
    static boolean checkRGB(int[] sampleColor, int[] expectedColor) {
        double somme = 0;

        for (int i = 0; i < 3; i++) {
            somme += Math.abs(sampleColor[i] - expectedColor[i]);
        }

        somme = somme / 3.0 / 255.0;

        return somme < .1;
    }

    static void saveScreenshot(){

        try {
          
            BufferedImage bi = getBufferedImage();
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveImage(BufferedImage bis,String name){

        try {

            File outputfile = new File(name+".png");
            ImageIO.write(bis, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static void loadTestImage(){


        try {
            buffImg = ImageIO.read(new File("C:\\Users\\AU00051k\\IdeaProjects\\test2\\src\\testScreenshot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
