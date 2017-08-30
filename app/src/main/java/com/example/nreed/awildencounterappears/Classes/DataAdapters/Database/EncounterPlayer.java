package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by nreed on 7/20/2017.
 */

public class EncounterPlayer {
    public static String TABLE_ENCOUNTER_PLAYER = "encounterplayer";
    public static String COLUMN_ID = "id";
    public static String COLUMN_ENOUNTER_ID = "encounterid";
    public static String COLUMN_PLAYER_ID = "playerid";
    public static String COLUMN_INITIATIVE = "initiative";
    public static String COLUMN_STATUS = "status";

    public static String[] allColumns(){
        return new String[] {COLUMN_ID, COLUMN_ENOUNTER_ID, COLUMN_PLAYER_ID, COLUMN_INITIATIVE, COLUMN_STATUS};
    }
}