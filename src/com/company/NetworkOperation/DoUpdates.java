package com.company.NetworkOperation;

import com.company.UI.Body.DownloadItem;

import java.util.ArrayList;

class DoUpdates extends Thread {
    private DownloadItem downloadItem;
    private ArrayList<Integer> downloadedSizeParts;
    private int totalSize;
    boolean pauseOrResume = true;

    DoUpdates(DownloadItem downloadItem, ArrayList<Integer> downloadedSizeParts, int totalSize) {
        this.downloadItem = downloadItem;
        this.downloadedSizeParts = downloadedSizeParts;
        this.totalSize = totalSize;
    }

    void doUpdates() {
        int downloadedSize = 0;
        int currentPeriodDownloadedSize = 0;
        while (downloadedSize < totalSize) {
            while (!pauseOrResume) {
                try {
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("can't sleep");
                }
            }
            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException e) {
                System.out.println("can't sleep");
            }
            for (Integer downloadedSizePart : downloadedSizeParts) {
                downloadedSize += downloadedSizePart;
            }
            downloadItem.setDownloadSpeed((downloadedSize - currentPeriodDownloadedSize) / 200);
            downloadItem.setDownloadedSize(downloadedSize);
            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
            downloadItem.setDownloadedSizeParts(downloadedSizeParts);
            downloadItem.getDownloadItemData().setDownloadedSizeParts(downloadedSizeParts);
            downloadItem.updateProgressbar();
            downloadItem.revalidate();
            downloadItem.repaint();
        }
    }

    void pauseUpdate() {
        pauseOrResume = false;
    }

    void resumeUpdate() {
        pauseOrResume = true;
    }

    public void run() {
        doUpdates();
    }

}
