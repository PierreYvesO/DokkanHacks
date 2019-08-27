package singleton;

import main.Form;
import manager.ImageManager;
import manager.LogsManager;
import model.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;
import static manager.ScreenPixelManager.*;

public class Play implements Runnable {

    private int lvl;
    /**
     * Instance unique pré-initialisée
     */
    private static Play INSTANCE = new Play();
    //Button you click to choose the level you play
    private final PixelButton B_LEVEL_1 =
            new PixelButton(PX_LEVEL_X, PX_LEVEL_Y, PX_LEVEL_W, PX_LEVEL_H,
                    PX_LEVEL_CHECK_X, PX_LEVEL_CHECK_Y, PixelButton.START_LVL_STAR_1);
    private final PixelButton B_LEVEL_2 =
            new PixelButton(PX_LEVEL_X, PX_LEVEL_Y, PX_LEVEL_W, PX_LEVEL_H,
                    PX_LEVEL_CHECK_X, PX_LEVEL_CHECK_Y, PixelButton.START_LVL_STAR_2);
    private final PixelButton B_CLEARED =
            new PixelButton(PX_NULL, PX_NULL, PX_NULL, PX_NULL, PX_CLEARED_CHECK_X, PX_CLEARED_CHECK_Y, PixelButton.START_LVL_CLEARED);
    //Buttons for a level with 3 difficulties
    private final Button B_DIFF_LEFT_3 = new Button(PX_DIFF_LEFT_3_X, PX_DIFF_Y, PX_DIFF_W, PX_DIFF_H);
    private final Button B_DIFF_MIDDLE_3 = new Button(PX_DIFF_MIDDLE_3_X, PX_DIFF_Y, PX_DIFF_W, PX_DIFF_H);
    private final Button B_DIFF_RIGHT_3 = new Button(PX_DIFF_RIGHT_3_X, PX_DIFF_Y, PX_DIFF_W, PX_DIFF_H);
    //Buttons for a level with 2 difficulties
    private final Button B_DIFF_LEFT_2 = new Button(PX_DIFF_LEFT_2_X, PX_DIFF_Y, PX_DIFF_W, PX_DIFF_H);
    private final Button B_DIFF_RIGHT_2 = new Button(PX_DIFF_RIGHT_2_X, PX_DIFF_Y, PX_DIFF_W, PX_DIFF_H);
    //Button you click to use a certain path on the map
    private final PixelButton B_MULTIPATH_TOP_R =
            new PixelButton(PX_B_MULTIPATH_TOP_R_X, PX_B_MULTIPATH_TOP_R_Y, PX_B_MULTIPATH_W, PX_B_MULTIPATH_H,
                    PX_B_MULTIPATH_TOP_R_CHECK_X, PX_B_MULTIPATH_TOP_R_CHECK_Y, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_TOP_L =
            new PixelButton(PX_B_MULTIPATH_TOP_L_X, PX_B_MULTIPATH_TOP_L_Y, PX_B_MULTIPATH_W, PX_B_MULTIPATH_H,
                    PX_B_MULTIPATH_TOP_L_CHECK_X, PX_B_MULTIPATH_TOP_L_CHECK_Y, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_BOT_L =
            new PixelButton(PX_B_MULTIPATH_BOT_L_X, PX_B_MULTIPATH_BOT_L_Y, PX_B_MULTIPATH_W, PX_B_MULTIPATH_H,
                    PX_B_MULTIPATH_BOT_L_CHECK_X, PX_B_MULTIPATH_BOT_L_CHECK_Y, PixelButton.MULTIPATH_BLUE);
    private final PixelButton B_MULTIPATH_BOT_R =
            new PixelButton(PX_B_MULTIPATH_BOT_R_X, PX_B_MULTIPATH_BOT_R_Y, PX_B_MULTIPATH_W, PX_B_MULTIPATH_H,
                    PX_B_MULTIPATH_BOT_R_CHECK_X, PX_B_MULTIPATH_BOT_R_CHECK_Y, PixelButton.MULTIPATH_BLUE);
    //Button to skip in figth mode
    private final PixelButton B_SKIP =
            new PixelButton(PX_SKIP_X, PX_SKIP_Y, PX_SKIP_W, PX_SKIP_H, PX_SKIP_CHECK_X, PX_SKIP_CHECK_Y, PixelButton.SKIP);
    //Button to choose a friend
    private final Button B_FRIEND = new Button(PX_FRIEND_X, PX_FRIEND_Y, PX_FRIEND_W, PX_FRIEND_H);
    //Button to start the level
    private final Button B_START = new Button(PX_START_X, PX_START_Y, PX_START_W, PX_START_H);
    //Button to check the fighting state
    private final PixelButton figthingState_1 =
            new PixelButton(PX_NULL, PX_NULL, PX_NULL, PX_NULL, PX_FIGHTING_STATE_1_CHECK_X, PX_FIGHTING_STATE_1_CHECK_Y, PixelButton.FIGHT_ORANGE);
    private final PixelButton figthingState_2 =
            new PixelButton(PX_NULL, PX_NULL, PX_NULL, PX_NULL, PX_FIGHTING_STATE_2_CHECK_X, PX_FIGHTING_STATE_2_CHECK_Y, PixelButton.FIGHT_GREEN);
    private final PixelButton figthingState_3 =
            new PixelButton(PX_NULL, PX_NULL, PX_NULL, PX_NULL, PX_FIGHTING_STATE_3_CHECK_X, PX_FIGHTING_STATE_3_CHECK_Y, PixelButton.FIGHT_BLUE);
    private final PixelButton figthingState_4 =
            new PixelButton(PX_NULL, PX_NULL, PX_NULL, PX_NULL, PX_FIGHTING_STATE_4_CHECK_X, PX_FIGHTING_STATE_4_CHECK_Y, PixelButton.FIGHT_RED);
    //Button you click to go through the map
    private final RunButton B_RUN_LEFT =
            new RunButton(PX_B_RUN_LEFT_X, PX_B_RUN_LEFT_Y, PX_B_RUN_W, PX_B_RUN_H, PX_B_RUN_LEFT_CHECK_X, PX_B_RUN_LEFT_CHECK_Y);
    private int round;
    private int actualround;
    private final RunButton B_RUN_MIDDLE =
            new RunButton(PX_B_RUN_MIDDLE_X, PX_B_RUN_MIDDLE_Y, PX_B_RUN_W, PX_B_RUN_H, PX_B_RUN_MIDDLE_CHECK_X, PX_B_RUN_MIDDLE_CHECK_Y);
    //Minimisation (1) ou Maximisation (0) du trajet
    private int run_mode;
    private final RunButton B_RUN_RIGHT =
            new RunButton(PX_B_RUN_RIGTH_X, PX_B_RUN_RIGTH_Y, PX_B_RUN_W, PX_B_RUN_H, PX_B_RUN_RIGTH_CHECK_X, PX_B_RUN_RIGTH_CHECK_Y);
    //Button to click when u finish the fight
    private final PixelButton B_KO = new PixelButton(PX_KO_X, PX_KO_Y, PX_KO_W, PX_KO_H, PX_KO_CHECK_X, PX_KO_CHECK_Y, PixelButton.END_FIGHT_ORANGE);
    //Buttons you click at the end of the level
    private final PixelButton B_END =
            new PixelButton(PX_END_X, PX_END_Y, PX_END_W, PX_END_H, PX_END_CHECK_X, PX_END_CHECK_Y, PixelButton.END_LVL_ORANGE);
    private String state = "run";
    private final Button B_FRIEND_REQ = new Button(PX_FRIEND_REQ_X, PX_FRIEND_REQ_Y, PX_FRIEND_REQ_W, PX_FRIEND_REQ_H);

    public static Play getInstance(int lvl, int run_mode, int round) {
        LogsManager.initLogManager();
        INSTANCE.lvl = lvl;
        INSTANCE.run_mode = run_mode;
        INSTANCE.round = round;
        INSTANCE.actualround = 0;
        return INSTANCE;
    }


    @Override
    public void run() {
        while (actualround <= round) {

            //Recupere l'image actuelle a l'ecran
            ImageManager.getScreen();
            //Partie Selection du niveau et ami
            LogsManager.getLog().info(state);
            switch (state) {
                case "start":
                    levelChooser();
                    break;

                //Partie du parcours de la carte
                case "run":
                    pathRun();
                    break;

                //Partie combat
                case "fight":
                    fight();
                    break;
            }

        }

    }

    private void fight() {

        checkSkip();
        if (B_KO.check()) {
            LogsManager.getLog().info("K.O.");
            B_KO.tapIn();
            B_KO.tapIn();
            wait(3000);
            B_KO.tapIn();
            B_KO.tapIn();
            wait(3000);
            B_KO.tapIn();
            B_KO.tapIn();
            wait(3000);
            checkSkip();



        } else if (figthingState_1.check() && figthingState_2.check() &&
                figthingState_3.check() && figthingState_4.check()) {
            LogsManager.getLog().info("Orbs");

            Paths paths = new Paths();
            paths.calcPaths();
            LogsManager.getLog().info(Map.displayMap());
            LogsManager.getLog().info(paths.getPaths());
            paths.getMax().tapIn();
            wait(4000);

            paths = new Paths();
            paths.calcPaths();
            LogsManager.getLog().info(Map.displayMap());
            LogsManager.getLog().info(paths.getPaths());
            paths.getMax().tapIn();
            wait(4000);

            paths = new Paths();
            paths.calcPaths();
            LogsManager.getLog().info(Map.displayMap());
            LogsManager.getLog().info(paths.getPaths());
            paths.getMax().tapIn();
            wait(4000);


        } else if (B_END.check()) {
            B_END.tapIn();
            wait(1000);
            B_FRIEND_REQ.tapIn();
            wait(1000);
            state = "start";
            actualround++;
            Form.form.setActualRound(actualround);
            LogsManager.getLog().info("End Lvl");
        } else if (B_RUN_LEFT.check()) {
            LogsManager.getLog().info("End Fighting ...");
            state = "run";
        }

    }

    private void checkSkip() {
        if (B_SKIP.check()) B_SKIP.tapIn();
        state = "fight";
    }

    private void pathRun() {
        if (B_RUN_RIGHT.check()) {
            String color = B_RUN_RIGHT.getColor();

            if (color.equals("orange")) {
                LogsManager.getLog().info("Orange recognized...");

                if (run_mode == 1)
                    chooseRunButtonMin();
                else
                    chooseRunButtonMax();
            } else {
                LogsManager.getLog().info("Grey recognized...");
                if (B_MULTIPATH_TOP_R.check() || B_MULTIPATH_BOT_L.check() || B_MULTIPATH_TOP_L.check() || B_MULTIPATH_BOT_R.check()) {
                    LogsManager.getLog().info("MultiPath recognized...");

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
                wait(1000);
            }
        } else {
            if (!state.equals("figth"))
                LogsManager.getLog().info("Fighting...");
            state = "fight";
        }
    }

    private void chooseRunButtonMax() {

        if (B_RUN_LEFT.updateStatus())
            B_RUN_LEFT.update();

        if (B_RUN_MIDDLE.updateStatus())
            B_RUN_MIDDLE.update();

        if (B_RUN_RIGHT.updateStatus())
            B_RUN_RIGHT.update();

        LogsManager.getLog().info("" + B_RUN_LEFT.getValue() + " " + B_RUN_MIDDLE.getValue() + " " + B_RUN_RIGHT.getValue());

        if (B_RUN_LEFT.getValue() >= B_RUN_MIDDLE.getValue() &&
                B_RUN_LEFT.getValue() > B_RUN_RIGHT.getValue()) {
            B_RUN_LEFT.tapIn();

        } else if (B_RUN_MIDDLE.getValue() >= B_RUN_LEFT.getValue() &&
                B_RUN_MIDDLE.getValue() > B_RUN_RIGHT.getValue()) {
            B_RUN_MIDDLE.tapIn();

        } else {
            B_RUN_RIGHT.tapIn();

        }

        wait(2000);
    }

    private void chooseRunButtonMin() {

        if (B_RUN_LEFT.updateStatus())
            B_RUN_LEFT.update();

        if (B_RUN_MIDDLE.updateStatus())
            B_RUN_MIDDLE.update();

        if (B_RUN_RIGHT.updateStatus())
            B_RUN_RIGHT.update();

        LogsManager.getLog().info("" + B_RUN_LEFT.getValue() + " " + B_RUN_MIDDLE.getValue() + " " + B_RUN_RIGHT.getValue());

        if (B_RUN_LEFT.getValue() <= B_RUN_MIDDLE.getValue() &&
                B_RUN_LEFT.getValue() < B_RUN_RIGHT.getValue()) {
            B_RUN_LEFT.tapIn();

        } else if (B_RUN_MIDDLE.getValue() <= B_RUN_LEFT.getValue() &&
                B_RUN_MIDDLE.getValue() < B_RUN_RIGHT.getValue()) {
            B_RUN_MIDDLE.tapIn();

        } else {
            B_RUN_RIGHT.tapIn();

        }

        wait(2000);

    }

    private void wait(int time) {
        try {
            sleep(time);
        } catch (InterruptedException ignored) {

        }
    }

    private void levelChooser() {
        resetRun();
        LogsManager.getLog().info("clic niveau");
        while (!B_LEVEL_1.check() && !B_LEVEL_2.check()) {
            ImageManager.getScreen();
        }
        B_LEVEL_1.tapIn();
        LogsManager.getLog().info("clic difficulté");
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
        wait(4000);
        LogsManager.getLog().info("clic ami"); //517 713
        B_FRIEND.tapIn();
        wait(4000);
        LogsManager.getLog().info("clic demarrer");//907 1582
        B_START.tapIn();
        wait(8000);
        state = "run";
    }

    private void resetRun() {
        B_RUN_LEFT.needUpdate();
        B_RUN_MIDDLE.needUpdate();
        B_RUN_RIGHT.needUpdate();
    }
}

