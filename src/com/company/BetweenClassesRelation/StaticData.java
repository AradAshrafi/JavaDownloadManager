package com.company.BetweenClassesRelation;

public class StaticData {
    private static StaticData ourInstance = new StaticData();

    private static String locationOfStorage = "C://Desktop";

    public static String getLocation() {
        return locationOfStorage;
    }

    public static void SetLocation(String newLocation) {
        locationOfStorage = newLocation;
    }

    private StaticData() {
    }

}
