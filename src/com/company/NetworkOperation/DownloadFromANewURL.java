package com.company.NetworkOperation;

import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadFromANewURL extends SwingWorker<Integer, Integer> {
    private DownloadItem downloadItem;
    private String url;
    private String fileName;
    private String locationOfStorage;
    private int downloadedSize;
    private ArrayList<Integer> downloadedSizeParts;

    public DownloadFromANewURL(DownloadItem downloadItem) {
        this.downloadItem = downloadItem;
        this.downloadedSize = downloadItem.getDownloadedSize();
        this.fileName = downloadItem.getTitle();
        this.url = downloadItem.getUrl();
        this.locationOfStorage = downloadItem.getLocationOfStorage();
        this.downloadedSizeParts = downloadItem.getDownloadedSizeParts();
    }

    public void sendGETRequest() {
        try {
            URL obj = new URL(url); //it has malformedException
            HttpURLConnection HttpCon = (HttpURLConnection) obj.openConnection();
            HttpCon.setRequestMethod("GET");
            int downloadItemSize = HttpCon.getContentLength();
            downloadItem.getDownloadItemProgressbar().setValue(downloadedSize);
            downloadItem.getDownloadItemData().setSize(Integer.toString(downloadItemSize));
            downloadItem.setsize(downloadItemSize);
            /// /i have 3 parts for each download so i divide it to 3
            int eachPartSize = downloadItemSize / 3;
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                Thread thread;
                if (i == 3) {
                    thread = new DownloadPartialParts(downloadItem, fileName, url, locationOfStorage, downloadedSizeParts, (i - 1), (i - 1) * eachPartSize, downloadItemSize);
                } else {
                    //-1 is to pretend double writing of last bit in each part
                    thread = new DownloadPartialParts(downloadItem, fileName, url, locationOfStorage, downloadedSizeParts, (i - 1), (i - 1) * eachPartSize, i * eachPartSize - 1);
                }
                threads.add(thread);
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            concatSegments(eachPartSize, downloadItemSize);

        } catch (IOException e) {
            downloadItem.getDownloadItemData().setStatus("failed");
            downloadItem.setStatus("failed");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void concatSegments(int eachPartSize, int byteFinal) {
        String path = locationOfStorage + '\\' + fileName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            File partOfDownload;
            for (int j = 1; j <= 3; j++) {
                if (j == 3) {
                    partOfDownload = new File(path + '.' + byteFinal);
                } else
                    partOfDownload = new File(path + '.' + (j * eachPartSize - 1));
                FileInputStream fileInputStream = new FileInputStream(partOfDownload);
                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                fileInputStream.close();
                partOfDownload.delete();
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Integer doInBackground() {
        sendGETRequest();
        return 0;
    }

//    @Override
//    protected void process(List<Integer> chunks) {
////        for (Integer chunk : chunks) {
////            System.out.println("processing:D");
//////            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
//////            downloadItem.setDownloadedSize(downloadedSize);
//////            downloadItem.revalidate();
//////            downloadItem.repaint();
////        }
//    }

    @Override
    protected void done() {
//        Thread.currentThread().join();
        if (downloadItem.getStatus().equals("In Progress")) {
            downloadItem.getDownloadItemData().setStatus("done");
            downloadItem.setStatus("done");
            doDaUpdates();
        }
        super.done();
    }

    void doDaUpdates() {
        downloadedSize = 0;
        for (Integer downloadSizePart : downloadedSizeParts) {
            downloadedSize += downloadSizePart;
        }
        downloadItem.setDownloadedSize(downloadedSize);
        downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
        downloadItem.setDownloadedSizeParts(downloadedSizeParts);
        downloadItem.getDownloadItemData().setDownloadedSizeParts(downloadedSizeParts);
        downloadItem.updateProgressbar();
        downloadItem.removeFieldsAfterFinishingDownload();
        downloadItem.revalidate();
        downloadItem.repaint();
    }
}
