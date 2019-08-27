package main;

import com.android.ddmlib.IDevice;
import manager.ADBManager;
import manager.ImageManager;
import manager.LogsManager;
import manager.ScreenPixelManager;
import model.Button;
import model.Map;
import model.Paths;
import singleton.Play;

import java.io.IOException;
import java.util.ResourceBundle;


class Demo {


    public static void main(String[] args) throws IOException {

        //testMap(null);
        //play();
        //display();
        //testBundle();
        screenshot(null);
        //testLogs();

    }

    private static void testLogs() {

        LogsManager logs = new LogsManager();
    }

    private static void testBundle() throws IOException {

        String s = ResourceBundle.getBundle("strings").getString("test");
        System.out.println(s);


        //Properties p = new Properties();
        //p.loadFromXML(new FileInputStream("ressources\\strings_fr_FR.xml"));
        //p.list(System.out);
    }

    static void testMap(IDevice device) {
        LogsManager.initLogManager();
        ADBManager.setPhone();
        device = ADBManager.device;
        ImageManager.setDevice(device);
        Button.setDevice(device);
        ImageManager.getScreen();
        ScreenPixelManager.init();
        //ImageManager.loadTestImage();
        Map.updateMap();
        System.out.println(Map.displayMap());
        Paths paths = new Paths();
        paths.calcPaths();
        System.out.println(paths.getPaths());
        //paths.getMax().tapIn();
        System.out.println(paths.getMax().getType());
        //ImageManager.saveImage(paths.getMax().getBuffI());
        //max.tapIn();
    }

    static void screenshot(IDevice device) {
        ADBManager.setPhone();
        device = ADBManager.device;
        ImageManager.setDevice(device);
        ImageManager.getScreen();
        System.out.println(ImageManager.getBufferedImage().getHeight());
        System.out.println(ImageManager.getBufferedImage().getWidth());
        ADBManager.finish();
        ImageManager.saveScreenshot();
    }


    private static void play() {

        ADBManager.setPhone();
        Button.setDevice(ADBManager.device);
        ImageManager.setDevice(ADBManager.device);
        Play p = Play.getInstance(1, 0, 2);
        Button.setMode(0);
        p.run();
        ADBManager.finish();


    }

    private static void display() {

        new Form();

    }


}


