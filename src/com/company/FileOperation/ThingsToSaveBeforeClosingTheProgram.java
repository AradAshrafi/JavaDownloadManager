package com.company.FileOperation;

import com.company.UI.BetweenClassesRelation.StaticData;

import java.util.ArrayList;

public class ThingsToSaveBeforeClosingTheProgram {
    public static void thingsToSaveBeforeClosingTheProgram(int simultaneousDownloads) {
        ArrayList<String> settings = new ArrayList<String>();
        settings.add("Location : " + StaticData.getLocation());
        settings.add("SimultaneousDownloads : " + Integer.toString(simultaneousDownloads));
        SettingsJDM.saveSettings(settings);
    }
}
