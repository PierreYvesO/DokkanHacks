package model;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.NullOutputReceiver;

import java.util.concurrent.ThreadLocalRandom;

public class Button {
    private static IDevice device;

    final int x, y, w, h;
    static int mode = 0;

    public Button(int x, int y, int w, int h) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public static void setMode(int choice) {
        mode = choice;
    }

    public static void setDevice(IDevice d) {
        device = d;
    }

    public void tapIn() {
        if (mode != 0) {
            System.out.println("tap");
        } else {
            int randomX = ThreadLocalRandom.current().nextInt(x, x + w + 1);
            int randomY = ThreadLocalRandom.current().nextInt(y, y + h + 1);

            try {
                device.executeShellCommand("input touchscreen tap " + randomX + " " + randomY, new NullOutputReceiver());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}