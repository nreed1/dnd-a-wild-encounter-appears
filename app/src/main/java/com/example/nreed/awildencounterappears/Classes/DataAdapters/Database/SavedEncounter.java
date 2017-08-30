package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by nreed on 7/20/2017.
 */

public class SavedEncounter {
    public static String TABLE_SAVED_ENCOUNTER = "savedencounter";
    public static String COLUMN_ID = "id";
    public static String COLUMN_ENCOUNTER_NAME = "encountername";
    public static String COLUMN_GROUP_ID = "groupid";

    public String[] allColumns(){
        return new String[]{COLUMN_ID, COLUMN_ENCOUNTER_NAME, COLUMN_GROUP_ID};
    }
}
