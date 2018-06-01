package com.company.UI;

import com.company.FileOperation.FilterJDM;
import com.company.FileOperation.SettingsJDM;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.Body.MyFileChooser;
import com.company.UI.LeftSideBar.LookAndFeelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Setting extends JFrame {
    private SpringLayout settingLayout;
    private JLabel simultaneousDownloadsLabel;
    private JSlider simultaneousDownloads;
    private int simultaneousDownloadsValue;
    private JButton chooseFileLocationTrigger;
    private JButton themeTrigger;
    private Container uiContainer;
    private JLabel forbiddenSitesLabel;
    private JTextField newForbiddenSite;
    private JList<String> forbiddenSites;
    private JButton addForbiddenSiteButton;
    private JButton removeForbiddenSiteButton;
//    private JFileChooser locationChooser;


    public Setting(DownloadItemsConnection downloadItemsConnection) {
        this.uiContainer = downloadItemsConnection.getUiContainer();
        this.simultaneousDownloadsValue = downloadItemsConnection.getSimultaneousDownloads();
        System.out.println(simultaneousDownloadsValue);
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
        simultaneousDownloads = new JSlider(JSlider.HORIZONTAL, 0, 50, simultaneousDownloadsValue);
        simultaneousDownloads.setForeground(Color.BLUE);
        simultaneousDownloads.setMajorTickSpacing(5);
        simultaneousDownloads.setPaintTicks(true);
        simultaneousDownloads.setPaintLabels(true);
        simultaneousDownloads.setPaintTrack(true);

        chooseFileLocationTrigger = new JButton("Choose File Location");
        themeTrigger = new JButton("theme");

        forbiddenSitesLabel = new JLabel("Forbidden Sites  : ");
        newForbiddenSite = new JTextField();
        DefaultListModel<String> forbiddenSitesName = new DefaultListModel<>();
        forbiddenSites = new JList<>(forbiddenSitesName);
        addForbiddenSiteButton = new JButton("Add");
        removeForbiddenSiteButton = new JButton("remove");

        /**
         * adding components to layout
         */
        add(simultaneousDownloadsLabel);
        add(simultaneousDownloads);
        add(chooseFileLocationTrigger);
        add(themeTrigger);
        add(forbiddenSitesLabel);
        add(newForbiddenSite);
        add(forbiddenSites);
        add(addForbiddenSiteButton);
        add(removeForbiddenSiteButton);


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
        settingLayout.putConstraint(SpringLayout.EAST, themeTrigger, 0, SpringLayout.EAST, simultaneousDownloadsLabel);

        //JLabel forbiddenSitesLabel
        settingLayout.putConstraint(SpringLayout.NORTH, forbiddenSitesLabel, 30, SpringLayout.SOUTH, themeTrigger);
        settingLayout.putConstraint(SpringLayout.WEST, forbiddenSitesLabel, 10, SpringLayout.WEST, this);
        settingLayout.putConstraint(SpringLayout.EAST, forbiddenSitesLabel, 0, SpringLayout.EAST, themeTrigger);


        //JTextField newForbiddenSie
        settingLayout.putConstraint(SpringLayout.NORTH, newForbiddenSite, 30, SpringLayout.SOUTH, themeTrigger);
        settingLayout.putConstraint(SpringLayout.WEST, newForbiddenSite, 0, SpringLayout.WEST, simultaneousDownloads);
        settingLayout.putConstraint(SpringLayout.EAST, newForbiddenSite, 350, SpringLayout.EAST, forbiddenSitesLabel);

        //JList forbiddenSites
        settingLayout.putConstraint(SpringLayout.NORTH, forbiddenSites, 30, SpringLayout.SOUTH, newForbiddenSite);
        settingLayout.putConstraint(SpringLayout.WEST, forbiddenSites, 0, SpringLayout.WEST, newForbiddenSite);
        settingLayout.putConstraint(SpringLayout.EAST, forbiddenSites, 0, SpringLayout.EAST, newForbiddenSite);


        //JButton addForbiddenSitesButton
        settingLayout.putConstraint(SpringLayout.NORTH, addForbiddenSiteButton, 30, SpringLayout.SOUTH, themeTrigger);
        settingLayout.putConstraint(SpringLayout.WEST, addForbiddenSiteButton, 15, SpringLayout.EAST, newForbiddenSite);
//        settingLayout.putConstraint(SpringLayout.EAST, addForbiddenSiteButton, 0, SpringLayout.EAST, simultaneousDownloads);

        //JButton removeForbiddenSitesButton
        settingLayout.putConstraint(SpringLayout.NORTH, removeForbiddenSiteButton, 30, SpringLayout.SOUTH, themeTrigger);
        settingLayout.putConstraint(SpringLayout.EAST, removeForbiddenSiteButton, 0, SpringLayout.EAST, simultaneousDownloads);

        /**
         * adding Handlers to components
         */
        SettingsHandler settingsHandler = new SettingsHandler(downloadItemsConnection, forbiddenSitesName);
        simultaneousDownloads.addChangeListener(settingsHandler);
        chooseFileLocationTrigger.addActionListener(settingsHandler);
        themeTrigger.addActionListener(settingsHandler);
        addForbiddenSiteButton.addActionListener(settingsHandler);
        removeForbiddenSiteButton.addActionListener(settingsHandler);

        addSavedURLs(forbiddenSitesName);
        pack();
        setVisible(true);
    }

    private void addSavedURLs(DefaultListModel<String> forbiddenSitesName) {
        ArrayList<String> savedURLs = FilterJDM.getForbiddenURLs();
        Iterator<String> it = savedURLs.iterator();
        while (it.hasNext()) {
            String currentURL = it.next();
            forbiddenSitesName.addElement(currentURL);
        }
    }

    private class SettingsHandler implements ActionListener, ChangeListener {
        private DownloadItemsConnection downloadItemsConnection;
        private DefaultListModel<String> listModel;

        public SettingsHandler(DownloadItemsConnection downloadItemsConnection, DefaultListModel<String> listModel) {
            this.downloadItemsConnection = downloadItemsConnection;
            this.listModel = listModel;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == themeTrigger) {
                LookAndFeelManager lookAndFeelManager = new LookAndFeelManager(uiContainer);
            }

            if (event.getSource() == chooseFileLocationTrigger) {
                MyFileChooser myFileChooser = new MyFileChooser();
            }

            if (event.getSource() == addForbiddenSiteButton) {
                String URL = newForbiddenSite.getText();
                FilterJDM.addForbidenUrl(URL);
                newForbiddenSite.setText("");
                if (URL.length() > 3)
                    listModel.addElement(URL);
            }

            if (event.getSource() == removeForbiddenSiteButton) {
                String URL = forbiddenSites.getSelectedValue();
                listModel.removeElement(URL);
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
