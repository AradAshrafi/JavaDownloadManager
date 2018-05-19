package com.company.UI.Body;

import com.company.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AddRemoveFromQueue extends JFrame {
    private SpringLayout addRemoveOnQueueLayout;
    private JLabel queueNameLabel;
    private JTextField queueName;
    private JButton queueSubmit;

    public AddRemoveFromQueue(DownloadItemsConnection downloadItemsConnection, DownloadItem downloadItem, String operation) {
        super(operation + "operation on queue");
        /**
         * initialization
         */
        addRemoveOnQueueLayout = new SpringLayout();
        setLayout(addRemoveOnQueueLayout);
        setPreferredSize(new Dimension(700, 300));

        /**
         * adding components to layout
         */
        queueNameLabel = new JLabel("Queue's Name");
        queueName = new JTextField();
        if (operation.equals("add"))
            queueSubmit = new JButton("Add To Current Queues/Make One");
        else
            queueSubmit = new JButton("Remove From Current Queue");

        add(queueSubmit);
        add(queueNameLabel);
        add(queueName);

        /**
         * components arrangement (with SpringLayouts constraint)
         */
        //JLabel queueNameLabel
        addRemoveOnQueueLayout.putConstraint(SpringLayout.NORTH, queueNameLabel, 15, SpringLayout.NORTH, this);
        addRemoveOnQueueLayout.putConstraint(SpringLayout.WEST, queueNameLabel, 20, SpringLayout.WEST, this);

        //JTextField queueName
        addRemoveOnQueueLayout.putConstraint(SpringLayout.NORTH, queueName, 15, SpringLayout.NORTH, this);
        addRemoveOnQueueLayout.putConstraint(SpringLayout.WEST, queueName, 20, SpringLayout.EAST, queueNameLabel);
        addRemoveOnQueueLayout.putConstraint(SpringLayout.EAST, queueName, -20, SpringLayout.EAST, this);

        //Submit Button
        addRemoveOnQueueLayout.putConstraint(SpringLayout.NORTH, queueSubmit, 15, SpringLayout.SOUTH, queueNameLabel);
        addRemoveOnQueueLayout.putConstraint(SpringLayout.WEST, queueSubmit, 250, SpringLayout.WEST, this);

        /**
         * adding ActionListener to them
         */
        addRemoveOnQueueLayoutHandler addRemoveOnQueueLayoutHandler = new addRemoveOnQueueLayoutHandler(downloadItemsConnection, downloadItem, operation);
        queueSubmit.addActionListener(addRemoveOnQueueLayoutHandler);

        pack();
        setVisible(true);
    }

    private class addRemoveOnQueueLayoutHandler implements ActionListener {
        private DownloadItemsConnection downloadItemsConnection;
        private HashMap<String, DownloadQueue> downloadQueues;
        private DownloadItem selectedItem;
        private String operation;

        public addRemoveOnQueueLayoutHandler(DownloadItemsConnection downloadItemsConnection, DownloadItem downloadItem, String operation) {
            this.downloadItemsConnection = downloadItemsConnection;
            this.operation = operation;
            downloadQueues = downloadItemsConnection.getDownloadQueues();
            selectedItem = downloadItem;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            switch (operation) {
                case "add": {
                    if (event.getSource() == queueSubmit) {
                        String selectedQueue = queueName.getText();
                        if (downloadQueues.keySet().contains(selectedQueue)) {
                            if (!downloadQueues.get(selectedQueue).getQueue().contains(selectedItem)) {
                                downloadQueues.get(selectedQueue).getQueue().add(selectedItem);
                                dispose();

                            }
                        } else {
                            downloadItemsConnection.addNewQueue(selectedQueue, selectedItem);
                            dispose();
                        }
                    }
                    break;
                }
                case "remove": {
                    if (event.getSource() == queueSubmit) {
                        String selectedQueue = queueName.getText();
                        if (!downloadQueues.keySet().contains(selectedQueue)) {
                            dispose();

                        } else if (downloadQueues.get(selectedQueue).getQueue().contains(selectedItem)) {
                            downloadQueues.get(selectedQueue).getQueue().remove(selectedItem);
                            dispose();
                        } else {
                            dispose();
                        }
                    }
                }
            }
            downloadItemsConnection.reloadBody();
        }
    }

}
