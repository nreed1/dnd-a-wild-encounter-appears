package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.GroupTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.PlayerTable;
import com.example.nreed.awildencounterappears.Classes.Objects.Group;
import com.example.nreed.awildencounterappears.Classes.Objects.PlayerCharacter;
import com.example.nreed.awildencounterappears.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 7/16/2017.
 */

public class PlayerDataAdapter {
    public List<PlayerCharacter> GetPlayersByGroup(int groupId){
        List<PlayerCharacter> playerCharacterList = new ArrayList<>();
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getReadableDatabase();
            String[] returnColumns = PlayerTable.AllColumns();
            String query = PlayerTable.COLUMN_GROUPID +"= ?";
            String[] arguments = {String.valueOf(groupId)};


            Cursor cursor = monsterDatabase.query(GroupTable.TABLE_GROUP,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    null );

            while(cursor.moveToNext()){
                PlayerCharacter playerCharacter = new PlayerCharacter();
                playerCharacter.setLevel(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_LEVEL)));
                playerCharacter.setCharacterName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_CHARACTER_NAME)));
                playerCharacter.setGroupId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_GROUPID)));
                playerCharacter.setDnDClass(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_CLASS)));
                playerCharacter.setArmorClass(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_AC)));
                playerCharacter.setPlayerName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_PLAYER_NAME)));

                playerCharacterList.add(playerCharacter);

            }
            cursor.close();
        }catch (Exception e){

        }
        return playerCharacterList;
    }
}
