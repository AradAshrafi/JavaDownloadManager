package com.company.UI.BetweenClassesRelation;

import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;

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

    void cancelSelectedItem(DownloadItem selectedItem);

    //<--

    //-->
    HashMap<String, DownloadQueue> getDownloadQueues();

    void addNewQueue(String Key, DownloadItem downloadItem);

    //<--

    int getSimultaneousDownloads();

    void setSimultaneousDownloads(int simultaneousDownloads);

    ArrayList<DownloadItem> getDownloadItems();

    void addToDownloadItems(DownloadItem downloadItem);

    void removeFromDownloadItems(DownloadItem downloadItem);

    Container getUiContainer();

    void reloadBody();

}
