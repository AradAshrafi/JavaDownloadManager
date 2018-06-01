package com.company.FileOperation;

import com.company.DownloadItemData.DownloadItemData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SettingsJDM {
    private static String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\settings.jdm";

    public static ArrayList<String> getSettings() {
        ArrayList<String> savedSettings = new ArrayList<>();
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String currentLineString = "";
            while ((currentLineString = br.readLine()) != null) {
                String[] currentLineStringArray = currentLineString.trim().split(" ");
                if (currentLineStringArray[0].equals("Location")) {
                    com.company.UI.BetweenClassesRelation.StaticData.SetLocation(currentLineStringArray[2]);

                } else if (currentLineStringArray[0].equals("SimultaneousDownloads")) {
                    System.out.println("zhuuuuun");
                    savedSettings.add(currentLineStringArray[2]);

                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ! ");
        } catch (IOException e) {
            System.out.println("something went wrong with reading from");
        }
        return savedSettings;
    }

    public static void saveSettings(ArrayList<String> settings) {
        File file = new File(path);
        try {
            FileWriter fr = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fr);
//                bw.write(url);
            Iterator<String> it = settings.iterator();
            while (it.hasNext()) {
                String currentSetting = it.next();
                bw.write(currentSetting);
                bw.newLine();

            }
            bw.close();

        } catch (IOException e) {
            System.out.println("can't find file,try again");
        }
    }
}
