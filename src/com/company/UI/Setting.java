package com.company.UI;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.Body.MyFileChooser;
import com.company.UI.LeftSideBar.LookAndFeelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setting extends JFrame {
    private SpringLayout settingLayout;
    private JLabel simultaneousDownloadsLabel;
    private JSlider simultaneousDownloads;
    private int simultaneousDownloadsValue;
    private JButton chooseFileLocationTrigger;
    private JButton themeTrigger;
    private Container uiContainer;
//    private JFileChooser locationChooser;


    public Setting(Container container, DownloadItemsConnection downloadItemsConnection) {
        this.uiContainer = container;
        this.simultaneousDownloadsValue = downloadItemsConnection.getSimultaneousDownloads();
        /**
         * handling Layout
         */
        settingLayout = new SpringLayout();
        setLayout(settingLayout);
        setPreferredSize(new Dimension(700, 300));
        setSize(850, 750);

        /**
         * making new components
         * config simultaneousDownloads
         */
        simultaneousDownloadsLabel = new JLabel();
        simultaneousDownloadsLabel.setText("Simultaneous Downloads : ");
        simultaneousDownloads = new JSlider(JSlider.HORIZONTAL, 0, 500, 500);
        simultaneousDownloads.setForeground(Color.BLUE);
//        simultaneousDownloads.setMinorTickSpacing(100);
        simultaneousDownloads.setMajorTickSpacing(50);
        simultaneousDownloads.setPaintTicks(true);
        simultaneousDownloads.setPaintLabels(true);
        simultaneousDownloads.setPaintTrack(true);

        chooseFileLocationTrigger = new JButton("Choose File Location");
        themeTrigger = new JButton("theme");


        /**
         * adding components to layout
         */
        add(simultaneousDownloadsLabel);
        add(simultaneousDownloads);
        add(chooseFileLocationTrigger);
        add(themeTrigger);

        /**
         * components arrangement (with SpringLayouts constraint)
         */
        //JLabel simultaneousDownloadsLabel
        settingLayout.putConstraint(SpringLayout.NORTH, simultaneousDownloadsLabel, 10, SpringLayout.NORTH, this);
        settingLayout.putConstraint(SpringLayout.WEST, simultaneousDownloadsLabel, 10, SpringLayout.WEST, this);
        //JSlider simultaneousDownloads
        settingLayout.putConstraint(SpringLayout.NORTH, simultaneousDownloads, 10, SpringLayout.NORTH, this);
        settingLayout.putConstraint(SpringLayout.WEST, simultaneousDownloads, 40, SpringLayout.EAST, simultaneousDownloadsLabel);
        settingLayout.putConstraint(SpringLayout.EAST, simultaneousDownloads, -20, SpringLayout.EAST, this);

        //JButton chooseFileLocationTrigger
        settingLayout.putConstraint(SpringLayout.NORTH, chooseFileLocationTrigger, 30, SpringLayout.SOUTH, simultaneousDownloadsLabel);
        settingLayout.putConstraint(SpringLayout.WEST, chooseFileLocationTrigger, 10, SpringLayout.WEST, this);
        //JButton themeTrigger
        settingLayout.putConstraint(SpringLayout.NORTH, themeTrigger, 30, SpringLayout.SOUTH, chooseFileLocationTrigger);
        settingLayout.putConstraint(SpringLayout.WEST, themeTrigger, 10, SpringLayout.WEST, this);

        /**
         * adding Handlers to components
         */
        SettingsHandler settingsHandler = new SettingsHandler(downloadItemsConnection);
        simultaneousDownloads.addChangeListener(settingsHandler);
        chooseFileLocationTrigger.addActionListener(settingsHandler);
        themeTrigger.addActionListener(settingsHandler);

        pack();
        setVisible(true);
    }

    private class SettingsHandler implements ActionListener, ChangeListener {
        private DownloadItemsConnection downloadItemsConnection;

        public SettingsHandler(DownloadItemsConnection downloadItemsConnection) {
            this.downloadItemsConnection = downloadItemsConnection;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == themeTrigger) {
                LookAndFeelManager lookAndFeelManager = new LookAndFeelManager(uiContainer);

            }
            if (event.getSource() == chooseFileLocationTrigger) {
                MyFileChooser myFileChooser = new MyFileChooser();
            }
        }

        @Override
        public void stateChanged(ChangeEvent event) {
            if (event.getSource() == simultaneousDownloads) {
                simultaneousDownloadsValue = simultaneousDownloads.getValue();
                downloadItemsConnection.setSimultaneousDownloads(simultaneousDownloadsValue);
            }

        }
    }
}
