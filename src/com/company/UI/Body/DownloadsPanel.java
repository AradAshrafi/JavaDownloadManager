package com.company.UI.Body;

import com.company.DownloadItemData.DownloadItemData;
import com.company.FileOperation.ListQueueJDM;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class DownloadsPanel extends JPanel {
    private DownloadQueue mainQueue;
    private FlowLayout downloadsPanelLayout;
    private DownloadItemsConnection downloadItemsConnection;
    private HashSet<DownloadItem> selectedItems;

    public DownloadsPanel(DownloadItemsConnection downloadItemsConnection) {
        /**
         * some initialization
         * casting ui to DownloadItemsConnection for some access
         */
        this.downloadItemsConnection = downloadItemsConnection;
        selectedItems = downloadItemsConnection.getSelectedItems();
        mainQueue = downloadItemsConnection.getDownloadQueues().get("main");
        System.out.println(downloadItemsConnection.getDownloadQueues().get("main").getQueue().size());
        downloadsPanelLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(downloadsPanelLayout);
        setPreferredSize(new Dimension(1100, 500));
        setForeground(Color.decode("#81A3A7"));
        setBackground(Color.decode("#8ED3F4"));

        /**
         * get savedDownloadsList from list.jdm
         */
        ArrayList<DownloadItemData> savedDownloadsList = ListQueueJDM.getDownloadsList("list");
        Iterator<DownloadItemData> savedDownloadsListIterator = savedDownloadsList.iterator();
        while (savedDownloadsListIterator.hasNext()) {
            DownloadItemData currentDownloadItemData = savedDownloadsListIterator.next();
            DownloadItem currentDownloadItem = new DownloadItem(currentDownloadItemData, 0, downloadItemsConnection);
            mainQueue.operationOnDownloadQueue(currentDownloadItem, "add");
        }
        System.out.println("hey" + downloadItemsConnection.getDownloadQueues().get("main").getQueue().size());

        /**
         * Adding items to Layout and handle their actionListener
         */
        Iterator<DownloadItem> handlerIt = mainQueue.getQueue().iterator();
        DownloadItemMouseHandler handler = new DownloadItemMouseHandler(downloadItemsConnection);
        while (handlerIt.hasNext()) {
            DownloadItem item = handlerIt.next();
            item.addMouseListener(handler);
            if (selectedItems.contains(item)) {
                setBackground(Color.red);
            }
            add(item);
        }
        setVisible(true);
    }

    public DownloadQueue getMainQueue() {
        return mainQueue;
    }
}
