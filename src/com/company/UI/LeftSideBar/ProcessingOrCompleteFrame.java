package com.company.UI.LeftSideBar;

import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProcessingOrCompleteFrame extends JFrame {
    private ArrayList<DownloadItem> downloadItems;
    private GridLayout processingOrCompleteFrame;
    private String state;

    public ProcessingOrCompleteFrame(ArrayList<DownloadItem> downloadItems, String state) {
        /**
         * initialization
         */
        this.downloadItems = downloadItems;
        this.state = state;
        processingOrCompleteFrame = new GridLayout(0, 1);
        setLayout(processingOrCompleteFrame);
        System.out.println(downloadItems.size());

        ArrayList<DownloadItem> downloadItemsClone = (ArrayList<DownloadItem>) downloadItems.clone();
        Iterator<DownloadItem> it = downloadItemsClone.iterator();
        if (state.equals("processing")) {
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                if (currentItem.getPercentage() < 100 && !currentItem.getStatus().equals("failed")) {
                    this.add(currentItem);
                }
            }
        } else {

            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                if (currentItem.getPercentage() == 100 || currentItem.getStatus().equals("failed")) {
                    this.add(currentItem);
                }
            }
        }

        setVisible(true);
    }
}
