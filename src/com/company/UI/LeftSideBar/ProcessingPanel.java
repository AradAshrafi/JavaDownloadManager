package com.company.UI.LeftSideBar;

import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.util.ArrayList;

public class ProcessingPanel extends JPanel {
    private ArrayList<DownloadItem> downloadItems;

    public ProcessingPanel(ArrayList<DownloadItem> downloadItems) {
        this.downloadItems = downloadItems;
    }
}
