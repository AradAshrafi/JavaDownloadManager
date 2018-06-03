package com.company.DownloadItemData;

import java.util.ArrayList;
import java.util.HashMap;

public class DownloadItemData {
    private String Id;
    private HashMap<String, String> data;

    public DownloadItemData(String id, String title, String url, String status, String locationOfStorage, int size, String[] downloadedSizeParts, int downloadedSize, String date) {
        data = new HashMap<>();
        Id = id;
        data.put("title", title);
        data.put("url", url);
        data.put("status", status);
        data.put("locationOfStorage", locationOfStorage);
        data.put("size", Integer.toString(size));
        data.put("downloadedSize", Integer.toString(downloadedSize));
        data.put("downloadedSizePart1", downloadedSizeParts[0]);
        data.put("downloadedSizePart2", downloadedSizeParts[1]);
        data.put("downloadedSizePart3", downloadedSizeParts[2]);
        data.put("date", date);
        System.out.println(downloadedSizeParts[0] + "  " + downloadedSizeParts[1]);

    }

//    /**
//     * for new downloading items which no parts of them is being downloaded
//     *
//     * @param id
//     * @param title
//     * @param url
//     * @param status
//     * @param locationOfStorage
//     * @param size
//     * @param percentage
//     * @param date
//     */
//    public DownloadItemData(String id, String title, String url, String status, String locationOfStorage, int size, int percentage, String date) {
//        data = new HashMap<>();
//        Id = id;
//        data.put("title", title);
//        data.put("url", url);
//        data.put("status", status);
//        data.put("locationOfStorage", locationOfStorage);
//        data.put("size", Integer.toString(size));
//        data.put("downloadedSize", Integer.toString(percentage));
//        data.put("downloadSizePart1", "0");
//        data.put("downloadSizePart2", "0");
//        data.put("downloadSizePart3", "0");
//        data.put("date", date);
//    }

    public DownloadItemData(String id) {
        data = new HashMap<>();
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public HashMap<String, String> getData() {

        return data;
    }

    public void setStatus(String status) {
        data.put("status", status);
    }

    public void setSize(String size) {
        data.put("size", size);
    }

    public void setDownloadedSize(String downloadedSize) {
        data.put("downloadedSize", downloadedSize);
    }

    public void setDownloadedSizeParts(ArrayList<Integer> downloadedSizeParts) {
        int counter = 1;
        for (Integer downloadedSizePart : downloadedSizeParts) {
            data.put("downloadedSizePart" + counter, Integer.toString(downloadedSizePart));
            counter++;
        }
    }
}
