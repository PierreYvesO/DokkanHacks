import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import org.jetbrains.annotations.Nullable;

public class ADBManager {


    static private void init() {
        AndroidDebugBridge.init(false);
    }

    static void finish() {
        AndroidDebugBridge.terminate();
    }


    @Nullable
    static private IDevice usingWaitLoop() {
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

    public static IDevice getPhone() {
        init();
        return usingWaitLoop();
    }


}


