package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by nreed on 7/20/2017.
 */

public class EncounterMonster {
    public static String TABLE_ENCOUNTER_MONSTER = "encountermonster";
    public static String COLUMN_ID = "id";
    public static String COLUMN_ENCOUNTER_ID = "encounterid";
    public static String COLUMN_MONSTER_ID = "monsterid";
    public static String COLUMN_ARMOR_CLASS = "armorclass";
    public static String COLUMN_INITIATIVE = "initiative";
    public static String COLUMN_STATUS = "status";

    public String[] allColumns(){
        return new String[]{COLUMN_ID, COLUMN_ENCOUNTER_ID, COLUMN_MONSTER_ID, COLUMN_ARMOR_CLASS, COLUMN_INITIATIVE ,COLUMN_STATUS};
    }
}
