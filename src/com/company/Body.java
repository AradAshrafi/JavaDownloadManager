package com.company;

import javax.swing.*;
import java.awt.*;

public class Body extends JPanel {
    public Body() {
        this.setLayout(new FlowLayout());
        setOpaque(true);
        setBackground(Color.CYAN);
        JRadioButton test = new JRadioButton("test");
        JRadioButton test1 = new JRadioButton("test");
        JRadioButton test2 = new JRadioButton("test");
        JRadioButton test3 = new JRadioButton("test");

        add(test);
        add(test1);
        add(test2);
        add(test3);

        setVisible(true);
    }
}
