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

        //testMap(null);
        //play();
        display();

    }

    static void testMap(IDevice device){
        //ImageManager.setDevice(device);
        //Button.setDevice(device);
        //ImageManager.getScreen();
        ImageManager.loadTestImage();
        Map.updateMap();
        //Map.displayMap();
        Paths paths = new Paths();
        paths.calcPaths();
        //paths.getPaths();
        //paths.getMax().tapIn();
        System.out.println(paths.getMax().getType());
        ImageManager.saveImage(paths.getMax().getBuffI(), "kiMax");
        //max.tapIn();
    }

    static void screenshot(IDevice device){
        ImageManager.setDevice(device);
        ImageManager.getScreen();
        ImageManager.saveScreenshot();
    }


    private static void play() throws Exception {
        Demo demo = new Demo();

        demo.init();

        IDevice device = demo.usingWaitLoop();
        Play p = new Play(0, 1, 1, device);
        p.checkState();

        demo.finish();


    }

    private static void display() {

        new Form();

    }





}


