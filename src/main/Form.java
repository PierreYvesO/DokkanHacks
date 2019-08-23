package main;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import manager.ADBManager;
import manager.ImageManager;
import model.Button;
import singleton.Play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Form {
    private JRadioButton radioButton2;
    private JRadioButton radioButton5;
    private JRadioButton radioButton7;
    private JRadioButton radioButton4;
    private JRadioButton radioButton1;
    private JRadioButton radioButton3;
    private JLabel label1;
    private JTextField nombreDeToursTextField;
    private JRadioButton maximisationRadioButton;
    public static Form form;
    private JPanel rootPanel;
    private JButton validerButton;
    private JRadioButton minimisationRadioButton;
    private Thread thread = null;
    private ButtonGroup lvl;
    private ButtonGroup mode;
    private JButton annulerButton;
    private JLabel actualround;

    Form() {
        form = this;


        validerButton.addActionListener(e -> {
            int lvlCommand = Integer.parseInt(lvl.getSelection().getActionCommand());
            int modeCommand = Integer.parseInt(mode.getSelection().getActionCommand());
            int nb_tours = Integer.parseInt(nombreDeToursTextField.getText());
            ADBManager.setPhone();
            Button.setDevice(ADBManager.device);
            ImageManager.setDevice(ADBManager.device);

            try {
                if (thread == null) {
                    thread = new Thread(Play.getInstance(lvlCommand, modeCommand, nb_tours), "play");
                    thread.start();
                }
                if (thread.isInterrupted()) {
                    thread = new Thread(Play.getInstance(lvlCommand, modeCommand, nb_tours), "play");
                    thread.start();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        annulerButton.addActionListener(e -> {
            if (thread != null) {
                if (thread.isAlive()) {
                    thread.interrupt();
                    thread = null;
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DokkanHacks");
        frame.setContentPane(new Form().rootPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(frame, "Êtes-vous sûr de vouloir quitter?", "Quitter", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    ADBManager.finish();
                    System.exit(0);
                }

            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(5, 7, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new GridConstraints(0, 4, 4, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("singleton/strings").getString("nbTours"));
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nombreDeToursTextField = new JTextField();
        panel1.add(nombreDeToursTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        this.$$$loadLabelText$$$(label3, ResourceBundle.getBundle("ressources/strings").getString("mode"));
        panel1.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maximisationRadioButton = new JRadioButton();
        maximisationRadioButton.setActionCommand("2");
        maximisationRadioButton.setSelected(true);
        this.$$$loadButtonText$$$(maximisationRadioButton, ResourceBundle.getBundle("ressources/strings").getString("max"));
        panel1.add(maximisationRadioButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minimisationRadioButton = new JRadioButton();
        minimisationRadioButton.setActionCommand("1");
        minimisationRadioButton.setSelected(false);
        this.$$$loadButtonText$$$(minimisationRadioButton, ResourceBundle.getBundle("ressources/strings").getString("min"));
        panel1.add(minimisationRadioButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton2 = new JRadioButton();
        radioButton2.setActionCommand("1");
        radioButton2.setText("");
        rootPanel.add(radioButton2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton5 = new JRadioButton();
        radioButton5.setActionCommand("2");
        radioButton5.setText("");
        rootPanel.add(radioButton5, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton7 = new JRadioButton();
        radioButton7.setActionCommand("3");
        radioButton7.setText("");
        rootPanel.add(radioButton7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton4 = new JRadioButton();
        radioButton4.setActionCommand("5");
        radioButton4.setText("");
        rootPanel.add(radioButton4, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton1 = new JRadioButton();
        radioButton1.setActionCommand("4");
        radioButton1.setText("");
        rootPanel.add(radioButton1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton3 = new JRadioButton();
        radioButton3.setActionCommand("6");
        radioButton3.setText("");
        rootPanel.add(radioButton3, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("ressources/strings").getString("choix"));
        label1.setVerifyInputWhenFocusTarget(false);
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootPanel.add(panel2, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        annulerButton = new JButton();
        annulerButton.setHorizontalAlignment(0);
        this.$$$loadButtonText$$$(annulerButton, ResourceBundle.getBundle("ressources/strings").getString("annul"));
        panel2.add(annulerButton);
        validerButton = new JButton();
        this.$$$loadButtonText$$$(validerButton, ResourceBundle.getBundle("ressources/strings").getString("valid"));
        panel2.add(validerButton);
        final JLabel label4 = new JLabel();
        this.$$$loadLabelText$$$(label4, ResourceBundle.getBundle("ressources/strings").getString("actuTours"));
        rootPanel.add(label4, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualround = new JLabel();
        actualround.setText("0");
        rootPanel.add(actualround, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2.setLabelFor(nombreDeToursTextField);
        mode = new ButtonGroup();
        mode.add(maximisationRadioButton);
        mode.add(minimisationRadioButton);
        lvl = new ButtonGroup();
        lvl.add(radioButton2);
        lvl.add(radioButton5);
        lvl.add(radioButton7);
        lvl.add(radioButton4);
        lvl.add(radioButton1);
        lvl.add(radioButton3);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    public void setActualRound(int round) {
        actualround.setText(String.valueOf(round));
    }

    /**
     * @noinspection ALL
     */
    private void createUIComponents() {
        actualround = new JLabel();
    }
}
