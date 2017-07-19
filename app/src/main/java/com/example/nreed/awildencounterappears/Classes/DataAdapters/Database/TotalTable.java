package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by nreed on 7/19/2017.
 */

public class TotalTable {
    public static String TABLE_TOTALS = "totals";
    public static String COLUMN_TOTAL_ENCOUNTERS = "totalencounters";
    public static String COLUMN_TOTAL_MONSTERS = "totalmonsters";
    public static String COLUMN_TOTAL_XP = "totalxp";

    public static String[] AllColumns(){
        return new String[]{COLUMN_TOTAL_ENCOUNTERS,COLUMN_TOTAL_MONSTERS,COLUMN_TOTAL_XP};
    }
}
