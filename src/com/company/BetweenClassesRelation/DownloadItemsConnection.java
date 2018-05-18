package com.company.BetweenClassesRelation;

import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface DownloadItemsConnection {
    HashSet<DownloadItem> getSelectedItems();

    void addToSelectedItem(DownloadItem selectedItem);

    void removeSelectedItem(DownloadItem selectedItem);

    void pauseSelectedItem(DownloadItem selectedItem);

    void resumeSelectedItem(DownloadItem selectedItem);

    void cancelSelectedItem(DownloadItem selectedItem);

    HashMap<String, DownloadQueue> getDownloadQueues();

    int getSimultaneousDownloads();

    void setSimultaneousDownloads(int simultaneousDownloads);

}
