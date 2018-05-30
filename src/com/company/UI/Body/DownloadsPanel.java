package com.company.UI.Body;

import com.company.DownloadItemData.DownloadItemData;
import com.company.FileOperation.Id;
import com.company.FileOperation.ListJDM;
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

        ArrayList<DownloadItemData> savedDownloadsList = ListJDM.getDownloadsList();
        Iterator<DownloadItemData> savedDownloadsListIterator = savedDownloadsList.iterator();

        while (savedDownloadsListIterator.hasNext()) {
            DownloadItemData currentDownloadItemData = savedDownloadsListIterator.next();
            DownloadItem currentDownloadItem = new DownloadItem(currentDownloadItemData, 0, downloadItemsConnection);
            mainQueue.operationOnDownloadQueue(currentDownloadItem, "add");
        }

//        DownloadItemData newDownloadItemData1 = new DownloadItemData(Integer.toString(Id.read()), downloadName, downloadLink, "In Progress", StaticData.getLocation(), 0, 0, System.currentTimeMillis());
//        DownloadItemData newDownloadItemData2 = new DownloadItemData(Integer.toString(Id.read()), downloadName, downloadLink, "In Progress", StaticData.getLocation(), 0, 0, System.currentTimeMillis());
//        DownloadItemData newDownloadItemData3 = new DownloadItemData(Integer.toString(Id.read()), downloadName, downloadLink, "In Progress", StaticData.getLocation(), 0, 0, System.currentTimeMillis());
//
//        DownloadItem sample1 = new DownloadItem("test", "failed", "https://", 20, 100, 0, StaticData.getLocation(), downloadItemsConnection);
//        DownloadItem sample2 = new DownloadItem("test", "failed", "https://", 20, 0, 0, "d://", downloadItemsConnection);
//        DownloadItem sample3 = new DownloadItem("test", "failed", "https://", 20, 1000, 0, "d://", downloadItemsConnection);

//        mainQueue.operationOnDownloadQueue(sample1, "add");
//        mainQueue.operationOnDownloadQueue(sample2, "add");
//        mainQueue.operationOnDownloadQueue(sample3, "add");

        Iterator<DownloadItem> handlerIt = mainQueue.getQueue().iterator();
        /**
         * Adding items to Layout and handle their actionListener
         */
        DownloadItemMouseHandler handler = new DownloadItemMouseHandler(downloadItemsConnection);
        while (handlerIt.hasNext()) {
            System.out.println("check");
            DownloadItem item = handlerIt.next();
            item.addMouseListener(handler);
            if (selectedItems.contains(item)) {
                setBackground(Color.red);
            }
            add(item);
        }
        setVisible(true);
    }
}
