package com.company.FileOperation;

import com.company.DownloadItemData.DownloadItemData;
import com.company.UI.Body.DownloadItem;

import java.util.ArrayList;

public class SearchOnList {
    public static ArrayList<DownloadItemData> searchOnList(String wordToFind) {
        ArrayList<DownloadItemData> downloadsList = ListQueueJDM.getDownloadsList("list");
        ArrayList<DownloadItemData> downloadsFound = new ArrayList<>();
        for (DownloadItemData downloadItem : downloadsList) {
            if (downloadItem.getData().get("title").contains(wordToFind) || downloadItem.getData().get("url").contains(wordToFind))
                downloadsFound.add(downloadItem);
        }

        return downloadsFound;
    }
}
