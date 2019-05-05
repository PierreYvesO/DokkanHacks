import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import org.jetbrains.annotations.Nullable;


class Demo {

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

        IDevice device = demo.usingWaitLoop();

        play(device);

        demo.finish();
    }

    static void testMap(IDevice device){
        ImageManager.setDevice(device);
        Button.setDevice(device);
        ImageManager.getScreen();
        //ImageManager.loadTestImage();
        Map map = new Map();
        map.getRow(5);
        map.getRow(4);
        map.getRow(3);
        map.getRow(2);
        map.getRow(1);
        KiSphere max = map.getMaxKi();
        System.out.println(max.getType());
       max.tapIn();
    }

    static void screenshot(IDevice device){
        ImageManager.setDevice(device);
        ImageManager.getScreen();
        ImageManager.saveScreenshot();
    }


    private static void play(IDevice device)throws Exception {
        Play p = new Play(2, device);
        p.checkState();


    }





}


