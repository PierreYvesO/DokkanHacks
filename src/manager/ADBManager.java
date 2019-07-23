package manager;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;


public class ADBManager {
    public static IDevice device;


    static private void init() {
        AndroidDebugBridge.initIfNeeded(false);

    }

    public static void finish() {
        AndroidDebugBridge.terminate();
    }


    static private void usingWaitLoop() {
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

            }
            device = adb.getDevices()[0];

        } catch (InterruptedException e) {
            e.getMessage();
        }

    }

    public static void setPhone() {
        init();
        usingWaitLoop();
    }


}


