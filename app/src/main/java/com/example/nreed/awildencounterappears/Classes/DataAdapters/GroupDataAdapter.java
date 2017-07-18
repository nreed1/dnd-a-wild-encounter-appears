package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.GroupTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.Objects.Group;
import com.example.nreed.awildencounterappears.MainActivity_old;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 7/16/2017.
 */

public class GroupDataAdapter {

    public Boolean createGroup(Group newGroup){
        try{
            SQLiteDatabase database = DatabaseHelper.GetInstance(null).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(GroupTable.COLUMN_GROUP_NAME,newGroup.getGroupName());
            long insertId = database.insert(GroupTable.TABLE_GROUP,null, contentValues);
            if(insertId>0) {
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }
    public List<Group> getGroups(){
        List<Group> groupList= new ArrayList<>();

        try{
            SQLiteDatabase monsterDatabase = MainActivity_old.databaseHelper.getReadableDatabase();
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
            cursor.close();
        }catch (Exception e){

        }

        return groupList;
    }
    /* Safely deletes the group and its players, if group has player they will need to have their
    * group updated or be deleted themselves
     * */
    public Boolean deleteGroup(int groupId,Boolean andPlayers){
        try{
            if(andPlayers) {
                //TODO player dataadapter delete call
            }
            SQLiteDatabase database = DatabaseHelper.GetInstance(null).getWritableDatabase();
            String query = GroupTable.COLUMN_ID + "= ?";
            String[] args ={String.valueOf(groupId)};
            int deleted =database.delete(GroupTable.TABLE_GROUP, query,args);
            if(deleted > 0){
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }
}
