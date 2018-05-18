package com.company.UI.Body;

import com.company.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;
import java.util.Map;

public class QueuesPanel extends JPanel {
    private GridLayout queuesPanelLayout;
    private HashMap<String, DownloadQueue> downloadQueues;

    public QueuesPanel(DownloadItemsConnection downloadItemsConnection) {
        queuesPanelLayout = new GridLayout(0, 1);
        this.downloadQueues = downloadItemsConnection.getDownloadQueues();
//        for (Map.Entry<String, DownloadQueue> entry : downloadQueues.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//        }
        for (String key : downloadQueues.keySet()) {
            JPanel currentRow = new JPanel();
            JLabel currentRowName = new JLabel();
            currentRowName.setText(key);
            currentRow.setLayout(new BorderLayout());
            JPanel currentRowOperation = new JPanel();
            currentRowOperation.add(new JButton("Play"));
            currentRowOperation.add(new JButton("Pause"));
            currentRow.add(currentRowName, BorderLayout.WEST);
            currentRow.add(currentRowOperation, BorderLayout.EAST);
        }
    }

}
