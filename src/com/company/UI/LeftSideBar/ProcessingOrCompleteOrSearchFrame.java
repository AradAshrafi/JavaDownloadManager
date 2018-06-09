package com.company.UI.LeftSideBar;

import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProcessingOrCompleteOrSearchFrame extends JFrame {
    private ArrayList<DownloadItem> downloadItems;
    private GridLayout processingOrCompleteFrame;
    private String state;
    private DownloadItemsConnection downloadItemsConnection;

    public ProcessingOrCompleteOrSearchFrame(ArrayList<DownloadItem> downloadItems, String state, DownloadItemsConnection downloadItemsConnection) {
        /**
         * initialization
         */
        this.downloadItems = downloadItems;
        this.downloadItemsConnection = downloadItemsConnection;
        this.state = state;
        processingOrCompleteFrame = new GridLayout(0, 1);
        setLayout(processingOrCompleteFrame);
        System.out.println(downloadItems.size());

        Iterator<DownloadItem> it = downloadItems.iterator();
        if (state.equals("processing")) {
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                DownloadItem newDownloadItem = new DownloadItem(currentItem.getDownloadItemData(), currentItem.getDownloadSpeed(), downloadItemsConnection);
                if (currentItem.getPercentage() < 100 && !currentItem.getStatus().equals("failed")) {
                    this.add(newDownloadItem);
                }
            }
        } else if (state.equals("completed")) {
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                DownloadItem newDownloadItem = new DownloadItem(currentItem.getDownloadItemData(), currentItem.getDownloadSpeed(), downloadItemsConnection);
                if (currentItem.getPercentage() == 100 || currentItem.getStatus().equals("failed")) {
                    this.add(newDownloadItem);
                }
            }
        }else{
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                DownloadItem newDownloadItem = new DownloadItem(currentItem.getDownloadItemData(), currentItem.getDownloadSpeed(), downloadItemsConnection);
                if (currentItem.getPercentage() == 100 || currentItem.getStatus().equals("failed")) {
                    this.add(newDownloadItem);
                }
            }
        }

        setVisible(true);
    }
}
