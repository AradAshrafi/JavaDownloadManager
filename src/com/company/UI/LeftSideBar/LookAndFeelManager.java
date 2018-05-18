package com.company.UI.LeftSideBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LookAndFeelManager extends JFrame {
    private Container containerToChange;
    private GridLayout gridLayout;
    private JButton undoButton;
    private ArrayList<String> lookAndFeelHistory;


    public LookAndFeelManager(Container uiContainer) {
        /**
         * some initialization
         */
        super("Pick Your Theme");
        gridLayout = new GridLayout(2, 0, 10, 10);
        containerToChange = uiContainer;
        lookAndFeelHistory = new ArrayList<>();
        lookAndFeelHistory.add("javax.swing.plaf.metal.MetalLookAndFeel");
        undoButton = new JButton("undo");

        /**
         * getting built in LookAndFeels
         * handling their actions
         */
        UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
        for (int i = 0, n = looks.length; i < n; i++) {
            JButton button = new JButton(looks[i].getName());
            button.setActionCommand(looks[i].getClassName());
            button.addActionListener(actionListener);
            add(button);
        }
        /**
         * adding undo button
         * handling it's actionListener
         */
        add(undoButton);
        undoButton.addActionListener(actionListener);

        /**
         * Manage Frame's Layout
         */
        setLayout(gridLayout);
        setSize(600, 150);
        setVisible(true);
    }

    private ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            /**
             * undoButton's actionListener
             */
            if (event.getSource() == undoButton) {
                if (lookAndFeelHistory.size() != 0) {
                    lookAndFeelHistory.remove(lookAndFeelHistory.size() - 1);
                }
            }
            /**
             * Look&Feels actionListener
             */
            else {
                String lookAndFeelClassName = event.getActionCommand();
                lookAndFeelHistory.add(lookAndFeelClassName);
                System.out.println(lookAndFeelClassName);
            }
            /**
             * Change Look And Feel
             */
            try {
                UIManager.setLookAndFeel(lookAndFeelHistory.get(lookAndFeelHistory.size() - 1));
                SwingUtilities.updateComponentTreeUI(containerToChange);
            } catch (Exception exception) {
                /**
                 * undo arrayList is empty
                 */
                if (lookAndFeelHistory.size() == 0) {
                    JOptionPane.showMessageDialog(containerToChange, "Can't Undo More Than This", "Invalid Undo Operation",
                            JOptionPane.WARNING_MESSAGE);
                }
                /**
                 * something Went wrong :D
                 */
                else {
                    JOptionPane.showMessageDialog(containerToChange, "Can't change look and feel", "Invalid Operation",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };
}

