package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.GroupTable;
import com.example.nreed.awildencounterappears.Classes.Objects.Group;
import com.example.nreed.awildencounterappears.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 7/16/2017.
 */

public class GroupDataAdapter {

    public List<Group> getGroups(){
        List<Group> groupList= new ArrayList<>();

        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getReadableDatabase();
            String[] returnColumns = GroupTable.AllColumns();
            String query = "";
            String[] arguments = {};


            Cursor cursor = monsterDatabase.query(GroupTable.TABLE_GROUP,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    null );

            while(cursor.moveToNext()){
                Group group= new Group();
                group.setGroupId(cursor.getInt(cursor.getColumnIndex(GroupTable.COLUMN_ID)));
                group.setGroupName(cursor.getString(cursor.getColumnIndex(GroupTable.COLUMN_GROUP_NAME)));

                groupList.add(group);
            }
        }catch (Exception e){

        }

        return groupList;
    }
}
