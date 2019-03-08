import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;

import java.io.File;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Demo {

    private void init() {
        AndroidDebugBridge.init(false);
    }

    private void finish() {
        AndroidDebugBridge.terminate();
    }


    @Nullable
    private IDevice usingWaitLoop() {
        AndroidDebugBridge adb = AndroidDebugBridge.createBridge();

        try {
            int trials = 10;
            while (trials > 0) {
                Thread.sleep(50);
                if (adb.isConnected()) {
                    break;
                }
                trials--;
            }

            if (!adb.isConnected()) {
                System.out.println("Couldn't connect to ADB server");
                return null;
            }

            trials = 10;
            while (trials > 0) {
                Thread.sleep(50);
                if (adb.hasInitialDeviceList()) {
                    break;
                }
                trials--;
            }

            if (!adb.hasInitialDeviceList()) {
                System.out.println("Couldn't list connected devices");
                return null;
            }
            return adb.getDevices()[0];

        } catch (InterruptedException e) {
            e.getMessage();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();

        demo.init();

        // I think this is the way to go for non-interactive or short-running applications
        //System.out.println("Demo using wait loop to ensure connection to ADB server and then enumerate devices synchronously");
        IDevice device = demo.usingWaitLoop();

// convert raw data to an Image
        RawImage rawImage = device.getScreenshot();
        BufferedImage image = new BufferedImage(rawImage.width, rawImage.height, BufferedImage.TYPE_INT_ARGB);

        int index = 0;
        int IndexInc = rawImage.bpp >> 3;
        for (int y = 0 ; y < rawImage.height ; y++) {
            for (int x = 0 ; x < rawImage.width ; x++) {
                int value = rawImage.getARGB(index);
                index += IndexInc;
                image.setRGB(x, y, value);
            }
        }

        if (!ImageIO.write(image, "png", new File("test.png"))) {
            throw new IOException("Failed to find png writer");
        }

        demo.finish();
    }

}


