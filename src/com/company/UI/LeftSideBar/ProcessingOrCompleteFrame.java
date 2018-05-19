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


        if (state.equals("processing")) {
            Iterator<DownloadItem> it = downloadItems.iterator();
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                if (currentItem.getPercentage() < 100 && !currentItem.getStatus().equals("failed")) {
                    add(currentItem);
                }
            }
        } else {
            Iterator<DownloadItem> it = downloadItems.iterator();
            while (it.hasNext()) {
                DownloadItem currentItem = it.next();
                if (currentItem.getPercentage() == 100 || currentItem.getStatus().equals("failed")) {
                    add(currentItem);
                }
            }
        }

        setVisible(true);
    }
}
