package com.company.UI.Body;

import javax.swing.*;
import java.awt.*;

public class AddToQueue extends JFrame {
    private SpringLayout addToQueue;
    private JLabel queueNameLabel;
    private JTextField queueName;

    public AddToQueue() {
        /**
         * initialization
         */
        addToQueue = new SpringLayout();
        queueNameLabel = new JLabel("Queue's Name");
        setPreferredSize(new Dimension(700, 300));
        setLayout(addToQueue);

        /**
         * adding components to layout
         */
        add(queueNameLabel);
        add(queueName);

        /**
         * components arrangement (with SpringLayouts constraint)
         */
        //JLabel queueNameLabel
        addToQueue.putConstraint(SpringLayout.NORTH, queueNameLabel, 10, SpringLayout.NORTH, this);
        addToQueue.putConstraint(SpringLayout.WEST, queueNameLabel, 20, SpringLayout.WEST, this);


        //JTextField queueName
        addToQueue.putConstraint(SpringLayout.NORTH, queueName, 10, SpringLayout.NORTH, this);
        addToQueue.putConstraint(SpringLayout.WEST, queueName, 20, SpringLayout.EAST, queueNameLabel);

        setVisible(true);
    }
}
