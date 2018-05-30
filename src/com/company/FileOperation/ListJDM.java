package com.company.FileOperation;

import com.company.DownloadItemData.DownloadItemData;
import com.company.UI.Body.DownloadItem;

import java.io.*;
import java.util.ArrayList;

public class ListJDM {
    private static final String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\list.jdm";

    /**
     * it'll recover saved files in List.jds file
     *
     * @return ArrayList of DownloadItemData ( each index is one object contains all data about one download )
     */
    public static ArrayList<DownloadItemData> getDownloadsList() {
        ArrayList<DownloadItemData> savedDownloadItems = new ArrayList<>();
        String lastCaughtItem = "";
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String currentLineString = "";
            while ((currentLineString = br.readLine()) != null) {
                String[] currentLineStringArray = currentLineString.trim().split(" ");
                if (currentLineStringArray[0].equals("id")) {
                    lastCaughtItem = currentLineStringArray[2];
                    DownloadItemData newDownloadItemFound = new DownloadItemData(lastCaughtItem);
                    savedDownloadItems.add(newDownloadItemFound);
                } else {
                    if (currentLineStringArray.length < 2)//it reaches empty line between different download item
                        continue;
                    System.out.println(currentLineStringArray[0] + " " + currentLineStringArray[2]);
                    savedDownloadItems.get(savedDownloadItems.size() - 1).getData().put(currentLineStringArray[0], currentLineStringArray[2]);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ! ");
        } catch (IOException e) {
            System.out.println("something went wrong with reading from");
        }
        return savedDownloadItems;
    }

    /**
     * @param downloadItemData -- an object containing all data about one download
     */
    public static void newDownload(DownloadItemData downloadItemData) {
        File file = new File(path);
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fr);
            String[] toWrite = {
                    "id : " + downloadItemData.getId(),
                    "title : " + downloadItemData.getData().get("title"),
                    "url : " + downloadItemData.getData().get("url"), "status : " + downloadItemData.getData().get("status"),
                    "location : " + downloadItemData.getData().get("locationOfStorage"),
                    "size : " + downloadItemData.getData().get("size"),
                    "percentage : " + downloadItemData.getData().get("percentage"),
                    "date : " + downloadItemData.getData().get("date")
            };
            for (int i = 0; i < toWrite.length; i++) {
                bw.flush();
                bw.write(toWrite[i]);
                bw.newLine();
            }
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("can't find file,try again");
        }
    }

    public static void removeDownload() {
        File file = new File(path);
    }
}
