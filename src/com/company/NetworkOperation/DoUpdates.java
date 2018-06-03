package com.company.NetworkOperation;

import com.company.UI.Body.DownloadItem;

import java.util.ArrayList;

class DoUpdates extends Thread {
    private static DownloadItem downloadItem;
    private static ArrayList<Integer> downloadedSizeParts;
    private static int totalSize;

    DoUpdates(DownloadItem downloadItem, ArrayList<Integer> downloadedSizeParts, int totalSize) {
        DoUpdates.downloadItem = downloadItem;
        DoUpdates.downloadedSizeParts = downloadedSizeParts;
        DoUpdates.totalSize = totalSize;
    }

    static void doUpdates() {
        int downloadedSize = 0;
        while (downloadedSize < totalSize) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                System.out.println("can't sleep");
            }
            for (Integer downloadedSizePart : downloadedSizeParts) {
                downloadedSize += downloadedSizePart;
            }
            System.out.println(downloadedSize);
            downloadItem.setDownloadedSize(downloadedSize);
            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
            downloadItem.setDownloadedSizeParts(downloadedSizeParts);
            downloadItem.getDownloadItemData().setDownloadedSizeParts(downloadedSizeParts);
            downloadItem.updateProgressbar();
            downloadItem.revalidate();
            downloadItem.repaint();
        }
    }

    public void run() {
        doUpdates();
    }

}
