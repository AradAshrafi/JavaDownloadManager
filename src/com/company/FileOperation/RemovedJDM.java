package com.company.FileOperation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RemovedJDM {
    private static final String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\Removed.jdm";

    public static void addToRecycleBin(String removedLine) {
        File file = new File(path);
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(removedLine);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("can't find file,try again");
        }
    }
}
