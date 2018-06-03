package com.company.NetworkOperation;

import com.company.UI.Body.DownloadItem;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class DownloadPartialParts extends Thread {
    private String fileName;
    private String url;
    private String locationOfStorage;
    private ArrayList<Integer> downloadedSizeParts;
    private DownloadItem downloadItem;
    private int downloadPart;
    private int byteStart;
    private int byteFinal;

    DownloadPartialParts(DownloadItem downloadItem, String fileName, String url, String locationOfStorage, ArrayList<Integer> downloadedSizeParts, int index, int byteStart, int byteFinal) {
        this.downloadItem = downloadItem;
        this.fileName = fileName;
        this.url = url;
        this.locationOfStorage = locationOfStorage;
        this.downloadedSizeParts = downloadedSizeParts;
        this.downloadPart = index;
        this.byteStart = byteStart;
        this.byteFinal = byteFinal;
    }

    private void downloadPartialParts() {
        try {
            URL obj = new URL(url);//if malformedException occurred its for this line
            HttpURLConnection HttpCon = (HttpURLConnection) obj.openConnection();
            HttpCon.setRequestProperty("Range", "bytes=" + (byteStart + downloadedSizeParts.get(downloadPart)) + "-" + byteFinal);
            HttpCon.setRequestMethod("GET");
            HttpCon.connect();
            int responseCode = HttpCon.getResponseCode();
            System.out.println("GET Response Code : " + responseCode);

            if (responseCode == 206) { // success
                InputStream inputStream = HttpCon.getInputStream();
                OutputStream outputStream = new FileOutputStream(new File(locationOfStorage + '\\' + fileName + '.' + byteFinal));
                int bytesRead;
                byte[] buffer = new byte[1024];
                //use thread here to not update UI every single moment:D
                Thread doUpdates = new DoUpdates(downloadItem, downloadedSizeParts, downloadItem.getSizeOfDownload());
                doUpdates.start();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    downloadedSizeParts.set(downloadPart, (downloadedSizeParts.get(downloadPart) + bytesRead));

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
    public void run() {
        downloadPartialParts();

    }
}

