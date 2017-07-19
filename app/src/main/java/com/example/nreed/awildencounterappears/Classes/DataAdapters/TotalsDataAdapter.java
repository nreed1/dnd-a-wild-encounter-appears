package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.GroupTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.TotalTable;
import com.example.nreed.awildencounterappears.Classes.Objects.Group;
import com.example.nreed.awildencounterappears.Classes.Objects.StatBlock;
import com.example.nreed.awildencounterappears.MainActivity;

/**
 * Created by nreed on 7/19/2017.
 */

public class TotalsDataAdapter {

    public StatBlock getStatBlock(){

        StatBlock statBlock = StatBlock.getInstance();
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getReadableDatabase();
            String[] returnColumns = TotalTable.AllColumns();
            String query = "";
            String[] arguments = {};


            Cursor cursor = monsterDatabase.query(TotalTable.TABLE_TOTALS,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    null );

            while(cursor.moveToNext()){
                statBlock.setTotalEncounters(cursor.getInt(cursor.getColumnIndex(TotalTable.COLUMN_TOTAL_ENCOUNTERS)));
                statBlock.setTotalMonsters(cursor.getInt(cursor.getColumnIndex(TotalTable.COLUMN_TOTAL_MONSTERS)));
                statBlock.setTotalXP(cursor.getInt(cursor.getColumnIndex(TotalTable.COLUMN_TOTAL_XP)));
                break;
            }
            cursor.close();
        }catch (Exception e){

        }
        return statBlock;
    }

    public Boolean updateXP(int oldTotal, int newTotal){
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TotalTable.COLUMN_TOTAL_XP, newTotal);
            String query = TotalTable.COLUMN_TOTAL_XP + " = ?";
            String[] args = {String.valueOf(oldTotal)};
            int rowsAffected = monsterDatabase.update(TotalTable.TABLE_TOTALS,contentValues, query,args);
            if(rowsAffected > 0 ){
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }

    public Boolean updateTotalMonsters(int oldTotal, int newTotal){
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TotalTable.COLUMN_TOTAL_MONSTERS, newTotal);
            String query = TotalTable.COLUMN_TOTAL_MONSTERS + " = ?";
            String[] args = {String.valueOf(oldTotal)};
            int rowsAffected = monsterDatabase.update(TotalTable.TABLE_TOTALS,contentValues, query,args);
            if(rowsAffected > 0 ){
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }

    public Boolean updateTotalEncounters(int oldTotal, int newTotal){
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TotalTable.COLUMN_TOTAL_ENCOUNTERS, newTotal);
            String query = TotalTable.COLUMN_TOTAL_ENCOUNTERS + " = ?";
            String[] args = {String.valueOf(oldTotal)};
            int rowsAffected = monsterDatabase.update(TotalTable.TABLE_TOTALS,contentValues, query,args);
            if(rowsAffected > 0 ){
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }
}
