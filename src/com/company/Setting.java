package com.company;

import javax.swing.*;
import java.awt.*;

public class Setting extends JFrame {
    private SpringLayout settingLayout;
    private JFileChooser locationChooser;


    public Setting() {
        /**
         * handling Layout
         */
        settingLayout = new SpringLayout();
        setLayout(settingLayout);
        setPreferredSize(new Dimension(700, 300));
        setSize(850, 750);

        /**
         * making new components
         */
        locationChooser = new JFileChooser();

        /**
         * adding components to layout
         */
        add(locationChooser);


        /**
         * components arrangement (with SpringLayouts constraint)
         */
        //JFileChooser locationChooser
        settingLayout.putConstraint(SpringLayout.NORTH, locationChooser, 10, SpringLayout.NORTH, this);


        setVisible(true);
    }
}
