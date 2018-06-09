package com.company.UI.LeftSideBar;

import com.company.DownloadItemData.DownloadItemData;
import com.company.FileOperation.ListQueueJDM;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Queues extends JFrame {
    private GridLayout queuesPanelLayout;
    private HashMap<String, DownloadQueue> downloadQueues;

    public Queues(DownloadItemsConnection downloadItemsConnection) {
        queuesPanelLayout = new GridLayout(0, 1);
        setLayout(queuesPanelLayout);
        this.downloadQueues = downloadItemsConnection.getDownloadQueuesClone();

        for (String key : downloadQueues.keySet()) {
            if (key.equals("main"))
                continue;


            DownloadQueue value = downloadQueues.get(key);
            /**
             * building queue's title
             */
            JPanel currentQueue = new JPanel();
            JLabel queueName = new JLabel();
            queueName.setText(key);
            currentQueue.setLayout(new BorderLayout());
            JPanel currentRowOperation = new JPanel(new GridLayout(0, 2));
            JButton playButton = new JButton("Play");
            JButton pauseButton = new JButton("Pause");
            currentRowOperation.add(playButton);
            currentRowOperation.add(pauseButton);

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
//                queuesItem.add(downloadItem);
                DownloadItemData newQueueItemData = downloadItem.getDownloadItemData();
                newQueueItemData.getData().put("status", "Paused");
                DownloadItem newDownloadItem = new DownloadItem(newQueueItemData, downloadItem.getDownloadSpeed(), downloadItemsConnection);
                queuesItem.add(newDownloadItem);
                ListQueueJDM.addToQueue(newDownloadItem.getDownloadItemData(), key);
            }
            currentQueue.add(queuesItem, BorderLayout.SOUTH);
            QueueHandler handler = new QueueHandler(key, playButton, pauseButton, value, downloadItemsConnection);

            playButton.addActionListener(handler);
            pauseButton.addActionListener(handler);

            add(currentQueue);


        }
        pack();
        setVisible(true);
    }

    private class QueueHandler implements ActionListener {
        private JButton playButton;
        private JButton pauseButton;
        private String queueName;
        private DownloadQueue currentQueue;
        private DownloadItemsConnection downloadItemsConnection;

        QueueHandler(String queueName, JButton playButton, JButton pauseButton, DownloadQueue currentQueue, DownloadItemsConnection downloadItemsConnection) {
            this.playButton = playButton;
            this.pauseButton = pauseButton;
            this.currentQueue = currentQueue;
            this.downloadItemsConnection = downloadItemsConnection;
            this.queueName = queueName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
                for (DownloadItem queueItem : currentQueue.getQueue()) {
                    downloadItemsConnection.resumeSelectedItemInQueue(queueName, queueItem);
                }
            }
            if (e.getSource() == pauseButton) {
                for (DownloadItem queueItem : currentQueue.getQueue()) {
                    downloadItemsConnection.pauseSelectedItemInQueue(queueName, queueItem);
                }
            }
        }
    }
}
