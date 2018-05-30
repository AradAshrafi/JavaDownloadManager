package com.company.BetweenClassesRelation;

public class StaticData {
    private static StaticData ourInstance = new StaticData();

    /**
     * location of saving files
     */
    private static String locationOfStorage = "C:\\Users\\asus\\Desktop";

    public static String getLocation() {
        return locationOfStorage;
    }

    public static void SetLocation(String newLocation) {
        locationOfStorage = newLocation;
    }

    private StaticData() {
    }

}
