package com.company.FileOperation;

import java.io.*;

public class Id {
    private static final String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\IdGenerator.jdm";

    public static void increment() {
        File file = new File(path);
        int Id = read();
        try (FileWriter fileWriter = new FileWriter(file)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write((Id + 1) + "");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int read() {
        File file = new File(path);
        String Id = "";
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            Id = br.readLine();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("can't Open File ! ");
        } catch (IOException e) {
            System.out.println("can't Read Id ! ");
        }
        System.out.println(Id);
        return Integer.parseInt(Id);
    }
}
