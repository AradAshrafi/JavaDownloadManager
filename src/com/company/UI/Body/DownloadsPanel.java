package com.company.UI.Body;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
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

    public DownloadsPanel(UI ui) {
        /**
         * some initialization
         * casting ui to DownloadItemsConnection for some access
         */
        mainQueue = new DownloadQueue();
        downloadItemsConnection = (DownloadItemsConnection) (ui);
        selectedItems = downloadItemsConnection.getSelectedItems();
        mainQueue.setQueue(downloadItemsConnection.getDownloadItems());
        downloadsPanelLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(downloadsPanelLayout);
        setPreferredSize(new Dimension(700, 500));

        DownloadItem sample1 = new DownloadItem("test", "failed", "https://", 20, 100, 0, StaticData.getLocation());
        DownloadItem sample2 = new DownloadItem("test", "failed", "https://", 20, 0, 0, "d://");
        DownloadItem sample3 = new DownloadItem("test", "failed", "https://", 20, 1000, 0, "d://");
        DownloadItem sample4 = new DownloadItem("test", "failed", "https://", 20, 120, 0, StaticData.getLocation());
        DownloadItem sample5 = new DownloadItem("test", "failed", "https://", 20, 15, 0, StaticData.getLocation());

        mainQueue.operationOnDownloadQueue(sample1, "add");
        mainQueue.operationOnDownloadQueue(sample2, "add");
//        mainQueue.operationOnDownloadQueue(sample3, "add");
//        mainQueue.operationOnDownloadQueue(sample4, "add");
//        mainQueue.operationOnDownloadQueue(sample5, "add");

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
