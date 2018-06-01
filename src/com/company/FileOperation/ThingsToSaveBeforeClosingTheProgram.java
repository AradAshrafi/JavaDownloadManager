package com.company.FileOperation;

import com.company.UI.BetweenClassesRelation.StaticData;
import com.company.UI.Body.DownloadItem;

import java.util.ArrayList;

public class ThingsToSaveBeforeClosingTheProgram {
    public static void thingsToSaveBeforeClosingTheProgram(int simultaneousDownloads, ArrayList<DownloadItem> downloadItems) {
        ArrayList<String> settings = new ArrayList<String>();
        settings.add("Location : " + StaticData.getLocation());
        settings.add("SimultaneousDownloads : " + Integer.toString(simultaneousDownloads));
        SettingsJDM.saveSettings(settings);
        ListQueueJDM.saveDownloadItemsSituationBeforeClosing(downloadItems);
    }
}
