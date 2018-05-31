package com.company.FileOperation;

import com.company.DownloadItemData.DownloadItemData;

import java.io.*;
import java.util.ArrayList;

public class FilterJDM {
    private static final String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\filter.jdm";

    public static ArrayList<String> getForbiddenURLs() {
        ArrayList<String> savedForbiddenURLs = new ArrayList<>();
        String lastCaughtItem = "";
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String currentLineString = "";
            while ((currentLineString = br.readLine()) != null) {
                savedForbiddenURLs.add(currentLineString);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ! ");
        } catch (IOException e) {
            System.out.println("something went wrong with reading from");
        }
        return savedForbiddenURLs;
    }

    public static void addForbidenUrl(String url) {
        File file = new File(path);
        try {
            if (url.length() > 3) {
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fr);
                bw.write(url);
                bw.newLine();
                bw.close();
            }
        } catch (IOException e) {
            System.out.println("can't find file,try again");
        }
    }

    public void removeDorbidenUrl(String url) {

    }

}


