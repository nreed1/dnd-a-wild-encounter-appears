package com.example.nreed.awildencounterappears.Classes.DataAdapters.Database;

/**
 * Created by Niki on 7/16/2017.
 */

public class GroupTable {
    public final static String TABLE_GROUP="group";
    public final static String COLUMN_ID ="id";
    public final static String COLUMN_GROUP_NAME="groupName";


    public static String[] AllColumns(){
        return  new String[]{COLUMN_ID, COLUMN_GROUP_NAME};
    }
}
