package com.company.UI.Body;

import java.util.ArrayList;

public class DownloadQueue {
    private ArrayList<DownloadItem> queue;

    public ArrayList<DownloadItem> getQueue() {
        return queue;
    }
    public DownloadQueue() {
        queue = new ArrayList<>();
    }

    public void operationOnDownloadQueue(DownloadItem item, String operation) {
        switch (operation) {
            case "add":
                queue.add(item);
                break;
            case "remove":
                queue.remove(item);
                break;
            case "resume":
                item.resumeSelectedItem();
                break;

            case "pause":
                item.pauseSelectedItem();
                break;

            case "cancel":
                item.cancelSelectedItem();
                break;
        }
    }
}
