package com.company.FileOperation;

import java.io.*;

public class ListJDM {
    private static final String path = "com/company/FileOperation/list.jdm";

    public static void getDownloadsList() {
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String currentLineString = "";
            while ((currentLineString = bufferedReader.readLine()) != null) {
                if (true)
                    System.out.println("do sth beyotch");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ! ");
        } catch (IOException e) {
            System.out.println("something went wrong with reading from");
        }

    }

    public static void newDownload() {
        File file = new File(path);
    }

    public static void removeDownload() {
        File file = new File(path);

    }
}
