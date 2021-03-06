package com.company.UI;

import com.company.DownloadItemData.DownloadItemData;
import com.company.FileOperation.ListQueueJDM;
import com.company.FileOperation.SearchOnList;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.BetweenClassesRelation.NewDownloadItemConnection;
import com.company.UI.Body.DownloadItem;
import com.company.UI.LeftSideBar.LookAndFeelManager;
import com.company.UI.LeftSideBar.NewDownloadTab;
import com.company.UI.LeftSideBar.ProcessingOrCompleteOrSearchFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Toolbar extends JPanel {
    private FlowLayout toolbarLayout;
    private JButton newDownloadButton;
    private JButton resumeButton;
    private JButton pauseButton;
    private JButton cancelButton;
    private JButton removeButton;
    private JButton settingsButton;
    private JLabel verticalSeparator1;
    private JLabel verticalSeparator2;
    private JLabel logo;
    private JTextField search;
    private JButton submitSearch;

    public Toolbar(DownloadItemsConnection downloadItemsConnection, NewDownloadItemConnection newDownloadItemConnection) {
        Image originalImg;
        Image newImg;
        toolbarLayout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(toolbarLayout);
        setBackground(Color.decode("#B9E5F3"));

        /**
         * handling newDownloadButton and it's image
         */
        ImageIcon newDownloadIcon = new ImageIcon("newDownload.png");
        //--> resizing image
        originalImg = newDownloadIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        newDownloadIcon = new ImageIcon(newImg);
        //<--
        newDownloadButton = new JButton(newDownloadIcon);
        newDownloadButton.setBorder(null);
        newDownloadButton.setContentAreaFilled(false);


        /**
         * handling resumeButton and it's image
         */
        ImageIcon resumeIcon = new ImageIcon("resume.png");
        //--> resizing image
        originalImg = resumeIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        resumeIcon = new ImageIcon(newImg);
        //<--
        resumeButton = new JButton(resumeIcon);
        resumeButton.setBorder(null);
        resumeButton.setContentAreaFilled(false);


        /**
         * handling pauseButton and it's image
         */
        ImageIcon pauseIcon = new ImageIcon("pause.png");
        //--> resizing image
        originalImg = pauseIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        pauseIcon = new ImageIcon(newImg);
        //<--
        pauseButton = new JButton(pauseIcon);
        pauseButton.setBorder(null);
        pauseButton.setContentAreaFilled(false);


        /**
         * handling cancelButton and it's image
         */
        ImageIcon cancelIcon = new ImageIcon("cancel.png");
        //--> resizing image
        originalImg = cancelIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        cancelIcon = new ImageIcon(newImg);
        //<--
        cancelButton = new JButton(cancelIcon);
        cancelButton.setBorder(null);
        cancelButton.setContentAreaFilled(false);


        /**
         * handling removeButton and it's image
         */
        ImageIcon removeIcon = new ImageIcon("remove.png");
        //--> resizing image
        originalImg = removeIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        removeIcon = new ImageIcon(newImg);
        //<--
        removeButton = new JButton(removeIcon);
        removeButton.setBorder(null);
        removeButton.setContentAreaFilled(false);

        /**
         * handling settingsButton and it's image
         */
        ImageIcon settingsIcon = new ImageIcon("settings.png");
        //--> resizing image
        originalImg = settingsIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newImg);
        //<--
        settingsButton = new JButton(settingsIcon);
        settingsButton.setBorder(null);
        Border borderOfSettingsButton = settingsButton.getBorder();
        Border marginOfSettingsButton = new EmptyBorder(0, 0, 0, 430);
        settingsButton.setBorder(new CompoundBorder(borderOfSettingsButton, marginOfSettingsButton));
        settingsButton.setContentAreaFilled(false);


        /**
         * handling vertical separator
         */
        ImageIcon verticalSeparatorIcon = new ImageIcon("verticalSeparator.png");
        //--> resizing image
        originalImg = verticalSeparatorIcon.getImage();
        newImg = originalImg.getScaledInstance(5, 35, java.awt.Image.SCALE_SMOOTH);
        verticalSeparatorIcon = new ImageIcon(newImg);
        //<--
        verticalSeparator1 = new JLabel();
        verticalSeparator2 = new JLabel();
        verticalSeparator1.setIcon(verticalSeparatorIcon);
        verticalSeparator2.setIcon(verticalSeparatorIcon);
        Border borderOfSeparator1 = verticalSeparator1.getBorder();
        Border borderOfSeparator2 = verticalSeparator2.getBorder();
        Border marginOfSeparator = new EmptyBorder(0, 10, 0, 10);
        verticalSeparator1.setBorder(new CompoundBorder(borderOfSeparator1, marginOfSeparator));
        verticalSeparator2.setBorder(new CompoundBorder(borderOfSeparator2, marginOfSeparator));


        /**
         * handling and adding logo
         */
        ImageIcon logoIcon = new ImageIcon("logo.png");
        //--> resizing image
        originalImg = logoIcon.getImage();
        newImg = originalImg.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(newImg);
        //<--
        logo = new JLabel();
        logo.setIcon(logoIcon);
        Border borderOfLogo = logo.getBorder();
        Border marginOfLogo = new EmptyBorder(0, 0, 0, 45);
        logo.setBorder(new CompoundBorder(borderOfLogo, marginOfLogo));

        /**
         * handling search
         */
        search = new JTextField();
        search.setPreferredSize(new Dimension(220, 30));
        submitSearch = new JButton("search");

        /**
         * adding tooltip
         */
        newDownloadButton.setToolTipText("add a new download");
        resumeButton.setToolTipText("click to resume selected download");
        pauseButton.setToolTipText("click to pause selected download");
        cancelButton.setToolTipText("click to cancel selected download");
        removeButton.setToolTipText("click to remove selected download");


        /**
         * adding components to panel
         */
        add(logo);
        add(newDownloadButton);
        add(verticalSeparator1);
        add(resumeButton);
        add(pauseButton);
        add(cancelButton);
        add(verticalSeparator2);
        add(removeButton);
        add(settingsButton);
        add(search);
        add(submitSearch);

        /**
         * add handler to components
         */
        ToolBarHandler toolBarHandler = new ToolBarHandler(downloadItemsConnection, newDownloadItemConnection);
        newDownloadButton.addActionListener(toolBarHandler);
        resumeButton.addActionListener(toolBarHandler);
        pauseButton.addActionListener(toolBarHandler);
        cancelButton.addActionListener(toolBarHandler);
        removeButton.addActionListener(toolBarHandler);
        settingsButton.addActionListener(toolBarHandler);
        submitSearch.addActionListener(toolBarHandler);


        setVisible(true);
    }

    private class ToolBarHandler implements ActionListener {
        private HashSet<DownloadItem> selectedItems;
        private DownloadItemsConnection downloadItemsConnection;
        private NewDownloadItemConnection newDownloadItemConnection;


        public ToolBarHandler(DownloadItemsConnection downloadItemsConnection, NewDownloadItemConnection newDownloadItemConnection) {
            /**
             * casting ui to interface to use it's functionality
             * cast it to NewDownloadItem to change body panel
             */
            this.downloadItemsConnection = downloadItemsConnection;
            this.newDownloadItemConnection = newDownloadItemConnection;
            this.selectedItems = downloadItemsConnection.getSelectedItems();//data has a static field for HashSets
        }

        @Override
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == newDownloadButton) {
                NewDownloadTab newDownloadTab = new NewDownloadTab(newDownloadItemConnection, downloadItemsConnection); //:))))
            }
            if (event.getSource() == resumeButton) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToPauseOrResume;
                while (it.hasNext()) {
                    selectedToPauseOrResume = it.next();
                    String status = selectedToPauseOrResume.getStatus();
                    if (!status.equals("In Progress")) {
                        downloadItemsConnection.resumeSelectedItem(selectedToPauseOrResume);
                    }
                }
            }
            if (event.getSource() == pauseButton) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToPauseOrResume;
                while (it.hasNext()) {
                    selectedToPauseOrResume = it.next();
                    String status = selectedToPauseOrResume.getStatus();
                    if (status.equals("In Progress")) {
                        downloadItemsConnection.pauseSelectedItem(selectedToPauseOrResume);
                    }
                }
            }
            if (event.getSource() == cancelButton) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToCancel;
                while (it.hasNext()) {
                    selectedToCancel = it.next();
                    downloadItemsConnection.cancelSelectedItem(selectedToCancel);
                }
            }
            if (event.getSource() == removeButton) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToDelete;
                while (it.hasNext()) {
                    selectedToDelete = it.next();
                    it.remove();
                    downloadItemsConnection.removeSelectedItem(selectedToDelete);
                }
            }
            if (event.getSource() == settingsButton) {
                Setting setting = new Setting(downloadItemsConnection);
            }

            if (event.getSource() == submitSearch) {
                System.out.println("submit");
                String wordToFind = search.getText();
                ArrayList<DownloadItemData> foundItemsData = SearchOnList.searchOnList(wordToFind);
                if (foundItemsData.size() > 0) {
//                    ArrayList<DownloadItem> downloadItems, String state, DownloadItemsConnection downloadItemsConnection
                    ArrayList<DownloadItem> foundItems = new ArrayList<>();
                    for (DownloadItemData downloadItemData : foundItemsData) {
                        DownloadItem newDownloadItem = new DownloadItem(downloadItemData, 0, downloadItemsConnection);
                        foundItems.add(newDownloadItem);
                    }
                    new ProcessingOrCompleteOrSearchFrame(foundItems, "search", downloadItemsConnection);
                }
            }
        }
    }
}

