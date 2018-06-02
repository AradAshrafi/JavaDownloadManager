package com.company.NetworkOperation;

import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadFromANewURL extends SwingWorker<Integer, Integer> {
    private DownloadItem downloadItem;
    private String URL;
    private int downloadedSize;

    public DownloadFromANewURL(DownloadItem downloadItem) {
        this.downloadItem = downloadItem;
        this.downloadedSize = downloadItem.getDownloadedSize();
        this.URL = downloadItem.getUrl();
    }

    public void sendGETRequest() {
        try {
            URL obj = new URL(URL); //it has malformedException
            HttpURLConnection HttpCon = (HttpURLConnection) obj.openConnection();
            HttpCon.setRequestMethod("GET");

            String fileName = downloadItem.getTitle();
//            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = HttpCon.getResponseCode();
            int downloadItemSize = HttpCon.getContentLength();
            downloadItem.getDownloadItemProgressbar().setValue(downloadedSize);
            downloadItem.getDownloadItemData().setSize(Integer.toString(downloadItemSize));
            downloadItem.setsize(downloadItemSize);
            System.out.println("GET Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                InputStream inputStream = HttpCon.getInputStream();
                System.out.println(downloadItem.getLocationOfStorage() + fileName);
                OutputStream outputStream = new FileOutputStream(new File(downloadItem.getLocationOfStorage() + '\\' + fileName));
                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    downloadedSize += bytesRead;
                    publish(downloadedSize);
//                    System.out.println(size);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
//                FileUtils.writeByteArrayToFile(new File("pathname"), myByteArray)
            } else {
                System.out.println("GET request not worked (check URL )");
            }
            HttpCon.disconnect();

        } catch (IOException e) {
            downloadItem.removeFieldsAfterFinishingDownload();
            downloadItem.getDownloadItemData().setStatus("failed");
            downloadItem.setStatus("failed");
            downloadItem.getDownloadItemData().setDownloadedSize(Integer.toString(downloadedSize));
            downloadItem.setDownloadedSize(downloadedSize);
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
        if (downloadItem.getStatus().equals("In Progress")) {
            downloadItem.removeFieldsAfterFinishingDownload();
            downloadItem.getDownloadItemData().setStatus("done");
            downloadItem.setStatus("done");
            System.out.println(downloadItem.getStatus());
        }
        super.done();
    }
}
