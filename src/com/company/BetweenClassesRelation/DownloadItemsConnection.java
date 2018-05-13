package com.company.BetweenClassesRelation;

import com.company.DownloadItem;

import java.util.HashSet;

public interface DownloadItemsConnection {
    HashSet<DownloadItem> getSelectedItems();

    void addToSelectedItems(DownloadItem selectedItem);

    void removeFromSelectedItems(DownloadItem selectedItem);

}
