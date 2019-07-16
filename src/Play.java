import com.android.ddmlib.IDevice;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

class Play {
    private int lvl;
    //Button you click to choose the level you play
    private final PixelButton B_LEVEL = new PixelButton(110, 1285, 900, 115, 150, 1300, PixelButton.START_LVL_GREY);
    private final PixelButton B_CLEARED = new PixelButton(0, 0, 0, 0, 200, 1012, PixelButton.START_LVL_CLEARED);
    //Buttons for a level with 3 difficulties
    private final Button B_DIFF_LEFT_3 = new Button(130, 1235, 220, 220);
    private final Button B_DIFF_MIDDLE_3 = new Button(430, 1235, 220, 220);
    private final Button B_DIFF_RIGHT_3 = new Button(730, 1235, 220, 220);
    //Buttons for a level with 2 difficulties
    private final Button B_DIFF_LEFT_2 = new Button(270, 1235, 220, 220);
    private final Button B_DIFF_RIGHT_2 = new Button(590, 1235, 220, 220);
    //Button you click to use a certain path on the map
    private final PixelButton B_MULTIPATH_TOP_R = new PixelButton(661, 829, 130, 75, 726, 866, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_TOP_L = new PixelButton(292, 829, 130, 75, 357, 866, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_BOT_L = new PixelButton(292, 1016, 130, 75, 357, 1053, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_BOT_R = new PixelButton(661, 1016, 130, 75, 726, 1053, PixelButton.MULTIPATH_BLUE);

    //Button to choose a friend
    private final Button B_FRIEND = new Button(65, 605, 950, 195);

    //Button to start the level
    private final Button B_START = new Button(805, 1555, 225, 90);


    //Button to check the fighting state
    private final PixelButton figthingState_1 = new PixelButton(0, 0, 0, 0, 980, 233, PixelButton.FIGHT_ORANGE);
    private final PixelButton figthingState_2 = new PixelButton(0, 0, 0, 0, 1016, 336, PixelButton.FIGHT_GREEN);
    private final PixelButton figthingState_3 = new PixelButton(0, 0, 0, 0, 955, 335, PixelButton.FIGHT_BLUE);
    private final PixelButton figthingState_4 = new PixelButton(0, 0, 0, 0, 935, 273, PixelButton.FIGHT_RED);

    //Button you click to go through the map
    private final RunButton B_RUN_LEFT = new RunButton(203, 1575, 100, 100, 249, 1665, new Integer[]{24, 129, 111, 31, 159, null});
    private final RunButton B_RUN_MIDDLE = new RunButton(493, 1535, 100, 100, 538, 1628, new Integer[]{19, 103, 85, null, 78, 104});
    private final RunButton B_RUN_RIGHT = new RunButton(783, 1495, 100, 100, 828, 1588, new Integer[]{24, 133, 100, null, 130, null});
    private int round;
    private int actualround;
    private String state = "start";
    //Minimisation (1) ou Maximisation (0) du trajet
    private int run_mode;

    //Button to click when u finish the fight
    private final PixelButton B_KO = new PixelButton(100, 630, 900, 900, 263, 963, PixelButton.END_FIGHT_ORANGE);

    //Buttons you click at the end of the level
    private final PixelButton B_END = new PixelButton(350, 1765, 375, 85, 436, 1796, PixelButton.END_LVL_ORANGE);
    private final Button B_FRIEND_REQ = new Button(115, 1205, 375, 90);

    Play(int lvl, int run_mode, int round, IDevice device) {
        this.lvl = lvl;
        this.run_mode = run_mode;
        this.round = round;
        actualround = 1;
        Button.setDevice(device);
        ImageManager.setDevice(device);
    }

    void checkState() throws Exception {
        while (actualround <= round) {
            //Recupere l'image actuelle a l'ecran
            ImageManager.getScreen();
            //Partie Selection du niveau et ami
            switch (state) {
                case "start":
                    levelChooser();
                    break;

                //Partie du parcours de la carte
                case "run":
                    run();
                    break;

                //Partie combat
                case "fight":
                    fight();
                    break;
            }
            actualround++;
        }

    }

    private void fight() throws InterruptedException {


        if (B_KO.check()) {
            System.out.println("KO detected...");
            B_KO.tapIn();
            wait(1500);
            B_KO.tapIn();
            wait(1500);
            B_KO.tapIn();
            wait(3000);
            state = "run";
            System.out.println("End Fighting ...");

        } else if (figthingState_1.check() && figthingState_2.check() &&
                figthingState_3.check() && figthingState_4.check()){


            Paths paths = new Paths();
            paths.calcPaths();
            paths.getMax().tapIn();
            wait(3000);

            paths.calcPaths();
            paths.getMax().tapIn();

            wait(3000);
            paths.calcPaths();
            paths.getMax().tapIn();

        }else if (B_END.check()) {
            B_END.tapIn();
            wait(1500);
            B_FRIEND_REQ.tapIn();
            System.out.println("End Lvl");
            wait(10000);
            state = "start";
            actualround++;
        }else if(B_RUN_LEFT.check())
            state = "run";
        wait(3000);


    }

    private void run() throws InterruptedException {
        if (B_RUN_RIGHT.check()) {
            String color = B_RUN_RIGHT.getColor();

            if (color.equals("orange")) {
                System.out.println("Orange recognized...");

                if (run_mode == 1)
                    chooseRunButtonMin();
                else
                    chooseRunButtonMax();
            } else {
                System.out.println("Grey recognized...");
                if (B_MULTIPATH_TOP_R.check() || B_MULTIPATH_BOT_L.check() || B_MULTIPATH_TOP_L.check() || B_MULTIPATH_BOT_R.check()) {
                    System.out.println("MultiPath recognized...");

                    ArrayList<PixelButton> tab_multi = new ArrayList<>();
                    if (B_MULTIPATH_TOP_R.check()) {
                        tab_multi.add(B_MULTIPATH_TOP_R);
                    }
                    if (B_MULTIPATH_TOP_L.check()) {
                        tab_multi.add(B_MULTIPATH_TOP_L);
                    }
                    if (B_MULTIPATH_BOT_R.check()) {
                        tab_multi.add(B_MULTIPATH_BOT_R);
                    }
                    if (B_MULTIPATH_BOT_L.check()) {
                        tab_multi.add(B_MULTIPATH_BOT_L);
                    }
                    Random r = new Random();
                    tab_multi.get(r.nextInt(tab_multi.size())).tapIn();
                }
            }
        } else {
                state = "fight";
                System.out.println("Fighting...");

        }
    }

    private void chooseRunButtonMax() throws InterruptedException {

        if (B_RUN_LEFT.updateStatus())
            B_RUN_LEFT.update();

        if (B_RUN_MIDDLE.updateStatus())
            B_RUN_MIDDLE.update();

        if (B_RUN_RIGHT.updateStatus())
            B_RUN_RIGHT.update();

        //System.out.println("" + B_RUN_LEFT.getValue() + " " + B_RUN_MIDDLE.getValue() + " " + B_RUN_RIGHT.getValue());

        if (B_RUN_LEFT.getValue() >= B_RUN_MIDDLE.getValue() &&
                B_RUN_LEFT.getValue() > B_RUN_RIGHT.getValue()) {
            B_RUN_LEFT.tapIn();
            B_RUN_LEFT.needUpdate();
        } else if (B_RUN_MIDDLE.getValue() >= B_RUN_LEFT.getValue() &&
                B_RUN_MIDDLE.getValue() > B_RUN_RIGHT.getValue()) {
            B_RUN_MIDDLE.tapIn();
            B_RUN_MIDDLE.needUpdate();
        } else {
            B_RUN_RIGHT.tapIn();
            B_RUN_RIGHT.needUpdate();
        }

        wait(2000);
    }

    private void chooseRunButtonMin() throws InterruptedException {

        if (B_RUN_LEFT.updateStatus())
            B_RUN_LEFT.update();

        if (B_RUN_MIDDLE.updateStatus())
            B_RUN_MIDDLE.update();

        if (B_RUN_RIGHT.updateStatus())
            B_RUN_RIGHT.update();

        if (B_RUN_LEFT.getValue() <= B_RUN_MIDDLE.getValue() &&
                B_RUN_LEFT.getValue() < B_RUN_RIGHT.getValue()) {
            B_RUN_LEFT.tapIn();
            B_RUN_LEFT.needUpdate();
        } else if (B_RUN_MIDDLE.getValue() <= B_RUN_LEFT.getValue() &&
                B_RUN_MIDDLE.getValue() < B_RUN_RIGHT.getValue()) {
            B_RUN_MIDDLE.tapIn();
            B_RUN_MIDDLE.needUpdate();
        } else {
            B_RUN_RIGHT.tapIn();
            B_RUN_RIGHT.needUpdate();
        }

        wait(2000);

    }

    private void wait(int time) throws InterruptedException {
        sleep(time);
    }

    private void levelChooser() throws InterruptedException {
        System.out.println("clic niveau");
        while (!B_LEVEL.check()) {
            ImageManager.getScreen();
        }
        B_LEVEL.tapIn();
        System.out.println("clic difficulté");
        while (!B_CLEARED.check()) {
            ImageManager.getScreen();
        }
        Button b;
        switch (lvl) {

            // 2 Levels
            case 4:
                b = B_DIFF_LEFT_2;
                break;
            case 5:
                b = B_DIFF_RIGHT_2;
                break;
            // 3 Levels
            case 1:
                b = B_DIFF_LEFT_3;
                break;
            case 3:
                b = B_DIFF_RIGHT_3;
                break;
            // Only 1 level
            default:
                b = B_DIFF_MIDDLE_3;
                break;
        }
        b.tapIn();
        sleep(4000);
        System.out.println("clic ami"); //517 713
        B_FRIEND.tapIn();
        sleep(4000);
        System.out.println("clic demarrer");//907 1582
        B_START.tapIn();
        sleep(8000);
        state = "run";
    }

}
