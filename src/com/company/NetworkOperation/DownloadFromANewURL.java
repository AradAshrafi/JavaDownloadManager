package com.company.NetworkOperation;

import com.company.UI.BetweenClassesRelation.StaticData;
import com.company.UI.Body.DownloadItem;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;

public class DownloadFromANewURL extends SwingWorker<Integer, Integer> {
    private DownloadItem downloadItem;
    private String URL;
    private int size = 0;

    public DownloadFromANewURL(DownloadItem downloadItem) {
        this.downloadItem = downloadItem;
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
            System.out.println("GET Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                InputStream inputStream = HttpCon.getInputStream();
                System.out.println(downloadItem.getLocationOfStorage() + fileName);
                OutputStream outputStream = new FileOutputStream(new File(downloadItem.getLocationOfStorage() + '\\' + fileName));
                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    size += bytesRead;
                    publish(size);
                    System.out.println(size);
                    downloadItem.getDownloadItemProgressbar().setValue(size);
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
            e.printStackTrace();
        }
    }

    @Override
    protected Integer doInBackground() {
        sendGETRequest();
        return 0;
    }

    @Override
    protected void done() {
        downloadItem.remove(downloadItem.getDownloadItemProgressbar());
        downloadItem.getDownloadItemData().setStatus("done");
        downloadItem.setStatus("done");
        System.out.println(downloadItem.getStatus());
        super.done();
    }

    public int getSize() {
        System.out.println("im there");
        return size;
    }

}
