package com.company.UI.Body;

import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.BetweenClassesRelation.StaticData;
import com.company.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Flow;


public class DownloadsPanel extends JPanel {
    private DownloadQueue mainQueue;
    private FlowLayout downloadsPanelLayout;
    private DownloadItemsConnection downloadItemsConnection;
    private HashSet<DownloadItem> selectedItems;

    public DownloadQueue getMainQueue() {
        return mainQueue;
    }

    public DownloadsPanel(DownloadItemsConnection downloadItemsConnection) {
        /**
         * some initialization
         * casting ui to DownloadItemsConnection for some access
         */
        mainQueue = new DownloadQueue();
        this.downloadItemsConnection = downloadItemsConnection;
        selectedItems = downloadItemsConnection.getSelectedItems();
        mainQueue.setQueue(downloadItemsConnection.getDownloadItems());
        downloadsPanelLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(downloadsPanelLayout);
        setPreferredSize(new Dimension(1100, 500));
        setForeground(Color.decode("#81A3A7"));
        setBackground(Color.decode("#8ED3F4"));

        DownloadItem sample1 = new DownloadItem("test", "failed", "https://", 20, 100, 0, StaticData.getLocation(), downloadItemsConnection);
        DownloadItem sample2 = new DownloadItem("test", "failed", "https://", 20, 0, 0, "d://", downloadItemsConnection);
        DownloadItem sample3 = new DownloadItem("test", "failed", "https://", 20, 1000, 0, "d://", downloadItemsConnection);

        mainQueue.operationOnDownloadQueue(sample1, "add");
        mainQueue.operationOnDownloadQueue(sample2, "add");
        mainQueue.operationOnDownloadQueue(sample3, "add");

        Iterator<DownloadItem> it = mainQueue.getQueue().iterator();
        /**
         * Adding items to Layout and handle their actionListener
         */
        DownloadItemMouseHandler handler = new DownloadItemMouseHandler(downloadItemsConnection);
        while (it.hasNext()) {
            System.out.println("check");
            DownloadItem item = it.next();
            item.addMouseListener(handler);
            if (selectedItems.contains(item)) {
                setBackground(Color.red);
            }
            add(item);
        }
        setVisible(true);
    }
}
