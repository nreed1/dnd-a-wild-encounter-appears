package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by Niki on 7/16/2017.
 */

public class PlayerTable {
    public static final String TABLE_PLAYER="player";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_CHARACTER_NAME="characterName";
    public static final String COLUMN_CLASS="class";
    public static final String COLUMN_LEVEL="level";
    public static final String COLUMN_AC="armorClass";

    public static String[] AllColumns(){
        return new String[]{COLUMN_ID, COLUMN_CHARACTER_NAME, COLUMN_CLASS, COLUMN_LEVEL, COLUMN_AC};
    }
}
