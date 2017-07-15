package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by nreed on 7/7/2017.
 */

public class MonsterTable {
    // Database table
    public static final String TABLE_MONSTER = "Monsters";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CR = "cr";
    public static final String COLUMN_SIZE= "size";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TAGS= "tags";
    public static final String COLUMN_SECTION = "section";
    public static final String COLUMN_ALIGNMENT = "alignment";
    public static final String COLUMN_ENVIRONMENT= "environment";
    public static final String COLUMN_AC = "ac";
    public static final String COLUMN_INIT= "init";
    public static final String COLUMN_SOURCES = "sources";
    public static final String COLUMN_XP ="xp";
    public static final String COLUMN_HP="hp";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MONSTER
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_CR + " decimal not null,"
            + COLUMN_SIZE + " text,"
            + COLUMN_TYPE + " text,"
            + COLUMN_TAGS + " text,"
            + COLUMN_SECTION + " text,"
            + COLUMN_ALIGNMENT + " text,"
            + COLUMN_ENVIRONMENT + " text,"
            + COLUMN_AC + " integer,"
            + COLUMN_INIT + " integer,"
            + COLUMN_SOURCES + " text, "
            + COLUMN_XP + " integer, "
            +COLUMN_HP + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(MonsterTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MONSTER);
        onCreate(database);
    }

    public static String[] AllColumns(){
        return new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_CR, COLUMN_SIZE, COLUMN_TYPE,COLUMN_TAGS,
                COLUMN_SECTION, COLUMN_ALIGNMENT,COLUMN_ENVIRONMENT, COLUMN_AC, COLUMN_INIT, COLUMN_SOURCES, COLUMN_XP, COLUMN_HP };
    }
}
