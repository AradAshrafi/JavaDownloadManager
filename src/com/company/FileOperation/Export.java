package com.company.FileOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Export {
    private static String path = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\cache.zip";

    public static void ExportToZip() {

        String[] srcFiles = {"C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\list.jdm",
                "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\IdGenerator.jdm",
                "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\removed.jdm",
                "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\filter.jdm",
                "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\settings.jdm"};

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(path);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.length; i++) {

                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            System.out.println("done");
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }
}
