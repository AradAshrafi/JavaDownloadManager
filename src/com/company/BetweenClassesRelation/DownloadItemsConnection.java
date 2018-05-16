package com.company.BetweenClassesRelation;


import com.company.UI.DownloadItem;
import com.company.UI.DownloadsPanel;

import javax.swing.*;
import java.util.HashSet;

public interface DownloadItemsConnection {
    HashSet<DownloadItem> getSelectedItems();

    void addToSelectedItems(DownloadItem selectedItem);

    void removeFromSelectedItems(DownloadItem selectedItem);

}
