import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    static int[] getPixel(int x, int y) {

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
        double colorDist;

        //System.out.println(Arrays.toString(sampleColor));
        //System.out.println(Arrays.toString(expectedColor));

        colorDist = (2*Math.pow((sampleColor[0] - expectedColor[0]),2)) + (4*Math.pow((sampleColor[1] - expectedColor[1]),2)) + (3*Math.pow((sampleColor[2] - expectedColor[2]),2));
        //System.out.println(colorDist+"\n");

        return colorDist < 2000;
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
            buffImg = ImageIO.read(new File("ki2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
