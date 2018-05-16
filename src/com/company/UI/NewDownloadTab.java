package com.company.UI;

import com.company.BetweenClassesRelation.NewDownloadItemConnection;
import com.company.BetweenClassesRelation.StaticData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDownloadTab extends JFrame {
    private SpringLayout newDownloadTabLayout;
    private JLabel url;
    private JLabel nameLabel;
    private JTextField link;
    private JTextField name;
    private JButton submit;
    /**
     * using NewDownloadItemConnection interface for connection between objects certain parts
     */
    private NewDownloadItemConnection newDownloadItemConnection;

    public NewDownloadTab(NewDownloadItemConnection newDownloadItemConnection) {
        super("add a new download");
        /**
         * handling Layout
         */
        newDownloadTabLayout = new SpringLayout();
        setLayout(newDownloadTabLayout);
        setPreferredSize(new Dimension(700, 300));
        setSize(950, 200);

        /**
         * making connection to body panel to add new download progressbar:D
         */
        this.newDownloadItemConnection = newDownloadItemConnection;

        /**
         * making new Components
         */
        url = new JLabel();
        nameLabel = new JLabel();
        link = new JTextField();
        name = new JTextField();
        submit = new JButton("Start Download");

        /**
         * components configuration
         */
        url.setText("URL : ");
        nameLabel.setText("Name : ");
        submit.setPreferredSize(new Dimension(130, 20));

        /**
         * adding components to layout
         */
        add(url);
        add(link);
        add(nameLabel);
        add(name);
        add(submit);

        /**
         * components arrangement (with SpringLayouts constraint)
         */
        //JLabel url placement
        newDownloadTabLayout.putConstraint(SpringLayout.WEST, url, 0, SpringLayout.WEST, this);
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, url, 10, SpringLayout.NORTH, this);
        //JTextField link placement
        newDownloadTabLayout.putConstraint(SpringLayout.WEST, link, 10, SpringLayout.EAST, url);
        newDownloadTabLayout.putConstraint(SpringLayout.EAST, link, 10, SpringLayout.EAST, this);
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, link, 10, SpringLayout.NORTH, this);
        //JButton submit
        newDownloadTabLayout.putConstraint(SpringLayout.WEST, submit, 10, SpringLayout.EAST, link);
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, submit, 10, SpringLayout.NORTH, this);
        //JLabel nameLabel placement
        newDownloadTabLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, this);
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.SOUTH, link);
        //JTextField name placement
        newDownloadTabLayout.putConstraint(SpringLayout.WEST, name, 10, SpringLayout.EAST, nameLabel);
        newDownloadTabLayout.putConstraint(SpringLayout.EAST, name, 10, SpringLayout.EAST, this);
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, name, 10, SpringLayout.SOUTH, link);

        /**
         * handling ActionListeners
         */
        NewDownloadTabHandler newDownloadTabHandler = new NewDownloadTabHandler(this);
        submit.addActionListener(newDownloadTabHandler);

        setVisible(true);
    }

    private class NewDownloadTabHandler implements ActionListener {
        private String downloadLink;
        private String downloadName;
        private NewDownloadTab newDownloadTab;

        public NewDownloadTabHandler(NewDownloadTab newDownloadTab) {
            this.newDownloadTab = newDownloadTab;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == submit) {
                String downloadLink = link.getText();
                String downloadName = name.getText();
                DownloadItem newDownloadItem = new DownloadItem(downloadName, "In Progress", downloadLink, 0, StaticData.getLocation());
                newDownloadItemConnection.addToDownloadPanel(newDownloadItem);
                newDownloadTab.dispose();
            }
        }
    }
//    private String
}
