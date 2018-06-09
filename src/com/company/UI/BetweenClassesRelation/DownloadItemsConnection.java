package com.company.UI.BetweenClassesRelation;

import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;
import com.company.UI.Body.DownloadsPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface DownloadItemsConnection {
    //--->
    HashSet<DownloadItem> getSelectedItems();

    void addToSelectedItem(DownloadItem selectedItem);

    void removeSelectedItem(DownloadItem selectedItem);

    void pauseSelectedItem(DownloadItem selectedItem);

    void resumeSelectedItem(DownloadItem selectedItem);

    void pauseSelectedItemInQueue(String queueName, DownloadItem selectedItem);

    void resumeSelectedItemInQueue(String queueName, DownloadItem selectedItem);

    void cancelSelectedItem(DownloadItem selectedItem);
    
    //<--

    //-->
    HashMap<String, DownloadQueue> getDownloadQueues();

    HashMap<String, DownloadQueue> getDownloadQueuesClone();

    void addNewQueue(String Key, DownloadItem downloadItem);

    void addToCurrentQueue(String Key, DownloadItem downloadItem);

    void removeFromCurrentQueue(String key, DownloadItem downloadItem);

    //<--

    int getSimultaneousDownloads();

    void setSimultaneousDownloads(int simultaneousDownloads);

    ArrayList<DownloadItem> getDownloadItems();

    DownloadsPanel getDownloadsPanel();

    Container getUiContainer();

    void reloadUI();

}
