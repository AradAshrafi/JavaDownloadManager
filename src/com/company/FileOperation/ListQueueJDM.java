package com.company.FileOperation;

import com.company.DownloadItemData.DownloadItemData;
import com.company.UI.Body.DownloadItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListQueueJDM {
    private static final String listPath = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\list.jdm";
    private static final String listPathTemp = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\list.tmp.jdm";
    private static final String queuePath = "C:\\Users\\asus\\Desktop\\WORKS\\java\\Projects\\Download Manager\\src\\com\\company\\FileOperation\\queue.jdm";

    /**
     * it'll recover saved files in List.jds file
     *
     * @return ArrayList of DownloadItemData ( each index is one object contains all data about one download )
     */
    public static ArrayList<DownloadItemData> getDownloadsList(String listOrQueue) {
        String path;
        if (listOrQueue.equals("list")) {
            path = listPath;
        } else {
            path = queuePath;
        }
        ArrayList<DownloadItemData> savedDownloadItems = new ArrayList<>();
        String lastCaughtItem = "";
        File file = new File(path);
        System.out.println("im here");
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
//                    System.out.println(currentLineStringArray[0] + " " + currentLineStringArray[2]);
                    String currentLineData = "";
                    for (int i = 0; i < currentLineStringArray.length - 2; i++) {
                        currentLineData += currentLineStringArray[i + 2] + " ";
                    }
                    currentLineData = currentLineData.trim();
                    savedDownloadItems.get(savedDownloadItems.size() - 1).getData().put(currentLineStringArray[0], currentLineData);
                }
            }
            br.close();
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
    public static void newDownload(DownloadItemData downloadItemData, String listOrQueue, boolean newDownloadOrSaveDownloadsBeforeCLose) {
        String path;
        if (listOrQueue.equals("list")) {
            if (newDownloadOrSaveDownloadsBeforeCLose)
                path = listPath;
            else
                path = listPathTemp;
        } else {
            path = queuePath;
        }
        File file = new File(path);
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fr);
            String[] toWrite = {
                    "id : " + downloadItemData.getId(),
                    "title : " + downloadItemData.getData().get("title"),
                    "url : " + downloadItemData.getData().get("url"),
                    "status : " + downloadItemData.getData().get("status"),
                    "locationOfStorage : " + downloadItemData.getData().get("locationOfStorage"),
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

    /**
     * it'll delete removed file from download panel
     *
     * @param downloadItemData
     * @param listOrQueue
     */
    public static void removeDownload(DownloadItemData downloadItemData, String listOrQueue) {
        String path;
        if (listOrQueue.equals("list")) {
            path = listPath;
        } else {
            path = queuePath;
        }
        File file = new File(path);
        File tempFile = new File(path + ".tmp");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            String keyToFind = downloadItemData.getId();
            String currentLine;
            boolean findIt = false;
            boolean removed = false;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!findIt || (findIt && removed)) {
                    String[] currentLineArray = currentLine.trim().split(" ");
                    if (currentLineArray[0].equals("id")) {
                        if (currentLineArray[2].equals(keyToFind)) {
                            findIt = true;
                            RemovedJDM.addToRecycleBin(currentLine);
                            continue;
                        }
                    }
                    bufferedWriter.flush();
                    bufferedWriter.write(currentLine + "\n");
                } else {
                    if (!removed) {
                        if (currentLine.length() < 2) {
                            removed = true;
                            bufferedWriter.flush();
                        } else {
                            RemovedJDM.addToRecycleBin(currentLine);
                        }
                    }
                }
            }
            RemovedJDM.addToRecycleBin(" ");
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();
            if (file.delete()) {
                if (tempFile.renameTo(file)) {
                    //nothing :D
                } else {
                    System.out.println("cant rename");
                }
            } else {
                System.out.println("can't delete file");
            }


        } catch (FileNotFoundException e) {
            System.out.println("can't open the source");
        } catch (IOException e) {
            System.out.println("can't open the new file");
        }

    }

    //TODO: combine saveFilesSituationBeforeClosing and Remove Download
    public static void saveDownloadItemsSituationBeforeClosing(ArrayList<DownloadItem> downloadItems) {
        for (DownloadItem downloadItem : downloadItems) {
            newDownload(downloadItem.getDownloadItemData(), "list", false);//false determines to save files in temp
        }
        File file = new File(listPath);
        File tmpFile = new File(listPathTemp);
        if (file.delete()) {
            tmpFile.renameTo(file);
            System.out.println("renamed");
        } else {
            System.out.println("cant delete");
        }
    }
}
