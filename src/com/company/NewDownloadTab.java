package com.company;

import jdk.jshell.spi.SPIResolutionException;

import javax.swing.*;
import java.awt.*;

public class NewDownloadTab extends JFrame {
    private SpringLayout newDownloadTabLayout;
    private JLabel url;
    private JTextField link;
    private JFileChooser locationChooser;
    private JButton submit;

    public NewDownloadTab() {
        /**
         * handling Layout
         */
        newDownloadTabLayout = new SpringLayout();
        setLayout(newDownloadTabLayout);
        setPreferredSize(new Dimension(700, 300));
        setSize(850, 750);

        /**
         * making new Components
         */
        url = new JLabel();
        link = new JTextField();
        locationChooser = new JFileChooser();
        submit = new JButton("Start Download");

        /**
         * components configuration
         */
        url.setText("URL : ");
        submit.setPreferredSize(new Dimension(130, 20));

        /**
         * adding components to layout
         */
        add(url);
        add(link);
        add(locationChooser);
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
        //JFileChooser locationChooser
        newDownloadTabLayout.putConstraint(SpringLayout.NORTH, locationChooser, 10, SpringLayout.SOUTH, url);


        setVisible(true);
    }
//    private String
}
