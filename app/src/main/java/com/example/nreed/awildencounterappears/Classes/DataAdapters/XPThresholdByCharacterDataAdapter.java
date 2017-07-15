package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.MonsterTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.XPThresholdsByCharacterTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.Objects.XPThresholdByCharacterLevel;
import com.example.nreed.awildencounterappears.MainActivity;

/**
 * Created by nreed on 7/7/2017.
 */

public class XPThresholdByCharacterDataAdapter {
    private Context ctx;

    public XPThresholdByCharacterDataAdapter(Context context){
        ctx = context;
    }
    public XPThresholdByCharacterDataAdapter(){

    }

    public XPThresholdByCharacterLevel GetXPThresholdByCharacterLevel(int level){
        XPThresholdByCharacterLevel xpThresholdByCharacterLevel=null;
        try{
            SQLiteDatabase xpThresholdDatabase = MainActivity.databaseHelper.getReadableDatabase();
            String[] returnColumns = XPThresholdsByCharacterTable.AllColumns();
            String query = XPThresholdsByCharacterTable.COLUMN_LEVEL + "= ?";
            String[] arguments = {String.valueOf(level)};

            Cursor cursor = xpThresholdDatabase.query(XPThresholdsByCharacterTable.TABLE_XPTHRESHOLDS,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    null);


            while(cursor.moveToNext()){
                xpThresholdByCharacterLevel = new XPThresholdByCharacterLevel();
                xpThresholdByCharacterLevel.Level = level;
                xpThresholdByCharacterLevel.Easy = cursor.getInt(cursor.getColumnIndex(XPThresholdsByCharacterTable.COLUMN_EASY));
                xpThresholdByCharacterLevel.Medium = cursor.getInt(cursor.getColumnIndex(XPThresholdsByCharacterTable.COLUMN_MEDIUM));
                xpThresholdByCharacterLevel.Hard = cursor.getInt(cursor.getColumnIndex(XPThresholdsByCharacterTable.COLUMN_HARD));
                xpThresholdByCharacterLevel.Deadly = cursor.getInt(cursor.getColumnIndex(XPThresholdsByCharacterTable.COLUMN_DEADLY));
                break;
            }

            cursor.close();

            return xpThresholdByCharacterLevel;
        }catch (Exception ex){
            return null;
        }
    }

}
