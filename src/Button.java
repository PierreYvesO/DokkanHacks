import com.android.ddmlib.IDevice;
import com.android.ddmlib.NullOutputReceiver;

import java.util.concurrent.ThreadLocalRandom;

class Button {
    private static IDevice device;

    final int x, y, w, h;

    Button(int x, int y, int w, int h) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    static void setDevice(IDevice d) {
        device = d;
    }

    void tapIn() {

        int randomX = ThreadLocalRandom.current().nextInt(x, x + w + 1);
        int randomY = ThreadLocalRandom.current().nextInt(y, y + h + 1);

        try {
            device.executeShellCommand("input touchscreen tap " + randomX + " " + randomY, new NullOutputReceiver());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}


/*
new IShellOutputReceiver() {
                public void addOutput(byte[] data, int offset, int length) {
                }

                public void flush() {
                }

                public boolean isCancelled() {
                    return false;
                }
            });
 */