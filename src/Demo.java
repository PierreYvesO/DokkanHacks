import com.android.ddmlib.IDevice;


class Demo {


    public static void main(String[] args) {

        //testMap(null);
        //play();
        display();

    }

    static void testMap(IDevice device) {
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
        ImageManager.saveImage(paths.getMax().getBuffI());
        //max.tapIn();
    }

    static void screenshot(IDevice device) {
        ImageManager.setDevice(device);
        ImageManager.getScreen();
        ImageManager.saveScreenshot();
    }


    private static void play() throws Exception {

        IDevice device = ADBManager.getPhone();
        Play p = new Play(0, 1, 1, device);
        p.checkState();
        ADBManager.finish();


    }

    private static void display() {

        new Form();

    }


}


