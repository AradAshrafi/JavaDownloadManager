package com.company.UI.LeftSideBar;

import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Queues extends JFrame {
    private GridLayout queuesPanelLayout;
    private HashMap<String, DownloadQueue> downloadQueues;

    public Queues(DownloadItemsConnection downloadItemsConnection) {
        queuesPanelLayout = new GridLayout(0, 1);
        setLayout(queuesPanelLayout);
        this.downloadQueues = downloadItemsConnection.getDownloadQueues();

        for (Map.Entry<String, DownloadQueue> downloadQueueEntry : downloadQueues.entrySet()) {
            String key = downloadQueueEntry.getKey();
            DownloadQueue value = downloadQueueEntry.getValue();
            /**
             * building queue's title
             */
            JPanel currentQueue = new JPanel();
            JLabel queueName = new JLabel();
            queueName.setText(key);
            currentQueue.setLayout(new BorderLayout());
            JPanel currentRowOperation = new JPanel(new GridLayout(0, 2));
            currentRowOperation.add(new JButton("Play"));
            currentRowOperation.add(new JButton("Pause"));
            /**
             * queue's title added
             */
            currentQueue.add(queueName, BorderLayout.WEST);
            currentQueue.add(currentRowOperation, BorderLayout.EAST);

            /**
             * building queue's item
             */
            JPanel queuesItem = new JPanel(new GridLayout(0, 1));
            for (DownloadItem downloadItem : value.getQueue()) {
                queuesItem.add(downloadItem);
            }
            currentQueue.add(queuesItem, BorderLayout.SOUTH);
            add(currentQueue);
        }
        pack();
        setVisible(true);
    }
}
