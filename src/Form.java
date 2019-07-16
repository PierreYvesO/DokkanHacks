import com.android.ddmlib.IDevice;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private JRadioButton minimsationRadioButton;
    private JPanel rootPanel;
    private JButton validerButton;
    private ButtonGroup lvl;
    private ButtonGroup mode;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public Form() {
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lvlCommand = Integer.parseInt(lvl.getSelection().getActionCommand());
                int modeCommand = Integer.parseInt(mode.getSelection().getActionCommand());
                int nb_tours = Integer.parseInt(nombreDeToursTextField.getText());
                IDevice device = ADBManager.getPhone();
                new Play(lvlCommand, modeCommand, nb_tours, device);
            }
        });


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form");
        frame.setContentPane(new Form().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter?");
                if (i == 0) {
                    ADBManager.finish();
                    System.exit(0);
                }

            }
        });
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
        rootPanel.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new GridConstraints(0, 4, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Nombre de tours");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nombreDeToursTextField = new JTextField();
        panel1.add(nombreDeToursTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Mode");
        panel1.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maximisationRadioButton = new JRadioButton();
        maximisationRadioButton.setActionCommand("2");
        maximisationRadioButton.setText("Maximisation");
        panel1.add(maximisationRadioButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minimsationRadioButton = new JRadioButton();
        minimsationRadioButton.setActionCommand("1");
        minimsationRadioButton.setText("Minimsation");
        panel1.add(minimsationRadioButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        label1.setText("Choix de niveau");
        label1.setVerifyInputWhenFocusTarget(false);
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerButton = new JButton();
        validerButton.setText("Valider");
        rootPanel.add(validerButton, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2.setLabelFor(nombreDeToursTextField);
        mode = new ButtonGroup();
        mode.add(maximisationRadioButton);
        mode.add(minimsationRadioButton);
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
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
