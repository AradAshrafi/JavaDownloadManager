package com.company.DownloadItemData;

import java.util.HashMap;

public class DownloadItemData {
    private String Id;
    private HashMap<String, String> data;

    public DownloadItemData(String id, String title, String url, String status, String locationOfStorage, int size, int percentage, String date) {
        data = new HashMap<>();
        Id = id;
        data.put("title", title);
        data.put("url", url);
        data.put("status", status);
        data.put("locationOfStorage", locationOfStorage);
        data.put("size", Integer.toString(size));
        data.put("percentage", Integer.toString(percentage));
        data.put("date", date);
    }

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
        System.out.println(data.get("status"));
        data.put("status", status);
        System.out.println(data.get("status"));
    }
}
