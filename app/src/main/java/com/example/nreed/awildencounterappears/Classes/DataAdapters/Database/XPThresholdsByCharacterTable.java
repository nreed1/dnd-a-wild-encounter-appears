package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by nreed on 7/7/2017.
 */

public class XPThresholdsByCharacterTable {
    // Database table
    public static final String TABLE_XPTHRESHOLDS = "\"XP Thresholds By Character\"";
    public static final String COLUMN_LEVEL = "Level";
    public static final String COLUMN_EASY = "Easy";
    public static final String COLUMN_MEDIUM = "Medium";
    public static final String COLUMN_HARD = "Hard";
    public static final String COLUMN_DEADLY = "Deadly";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_XPTHRESHOLDS
            + "("
            + COLUMN_LEVEL + " int, "
            + COLUMN_EASY + " int,"
            + COLUMN_MEDIUM + " int,"
            + COLUMN_HARD + " int,"
            + COLUMN_DEADLY + " int"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(MonsterTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_XPTHRESHOLDS);
        onCreate(database);
    }

    public static String[] AllColumns(){
        return new String[]{ COLUMN_LEVEL, COLUMN_EASY, COLUMN_MEDIUM, COLUMN_HARD, COLUMN_DEADLY};
    }
}
