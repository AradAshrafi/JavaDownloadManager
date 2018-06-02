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
    private int downloadedSize = 0;

    public DownloadFromANewURL(DownloadItem downloadItem) {
        this.downloadItem = downloadItem;
        this.downloadedSize = downloadItem.getDownloadedSize();
        this.fileName = downloadItem.getTitle();
        this.url = downloadItem.getUrl();
        this.locationOfStorage = downloadItem.getLocationOfStorage();
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
                Thread thread = new DownloadPartialParts(fileName, url, locationOfStorage, downloadedSize, (i - 1) * eachPartSize, i * eachPartSize);
                threads.add(thread);
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            concatSegments(eachPartSize);

        } catch (IOException e) {
            downloadItem.removeFieldsAfterFinishingDownload();
            downloadItem.getDownloadItemData().setStatus("failed");
            downloadItem.setStatus("failed");
            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
            downloadItem.setDownloadedSize(downloadedSize);
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void concatSegments(int eachPartSize) {
        String path = locationOfStorage + '\\' + fileName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            for (int j = 0; j < 3; j++) {
                File partOfDownload = new File(path + '.' + j * eachPartSize);
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

    @Override
    protected void process(List<Integer> chunks) {
        super.process(chunks);
        for (Integer chunk : chunks) {
            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
            downloadItem.setDownloadedSize(downloadedSize);
            downloadItem.revalidate();
            downloadItem.repaint();
        }
    }

    @Override
    protected void done() {
//        Thread.currentThread().join();
        if (downloadItem.getStatus().equals("In Progress")) {
            downloadItem.removeFieldsAfterFinishingDownload();
            downloadItem.getDownloadItemData().setStatus("done");
            downloadItem.setStatus("done");
            System.out.println(downloadItem.getStatus());
        }
        super.done();
    }
}
