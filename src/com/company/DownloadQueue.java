package com.company;

import java.util.ArrayList;

public class DownloadQueue {
    private ArrayList<DownloadItem> queue;

    public ArrayList<DownloadItem> getQueue() {
        return queue;
    }

    public void addToQueue(DownloadItem newItem) {
        queue.add(newItem);
    }
}
