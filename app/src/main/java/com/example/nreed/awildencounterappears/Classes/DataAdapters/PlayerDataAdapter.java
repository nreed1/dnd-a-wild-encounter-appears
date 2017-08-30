package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.GroupTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.PlayerTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.Objects.PlayerCharacter;
import com.example.nreed.awildencounterappears.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 7/16/2017.
 */

public class PlayerDataAdapter {
    public Boolean createPlayer(PlayerCharacter newPlayer){
        try{
            SQLiteDatabase database = DatabaseHelper.GetInstance(null).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(PlayerTable.COLUMN_PLAYER_NAME, newPlayer.getPlayerName());
            contentValues.put(PlayerTable.COLUMN_CHARACTER_NAME, newPlayer.getCharacterName());
            contentValues.put(PlayerTable.COLUMN_LEVEL, newPlayer.getLevel());
            contentValues.put(PlayerTable.COLUMN_AC, newPlayer.getArmorClass());
            contentValues.put(PlayerTable.COLUMN_CLASS, newPlayer.getDnDClass());
            contentValues.put(PlayerTable.COLUMN_GROUPID, newPlayer.getGroupId());

            long insertId = database.insert(PlayerTable.TABLE_PLAYER,null, contentValues);
            if(insertId>0) {
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }
    public Boolean deletePlayer(int playerId){
        try{
            SQLiteDatabase database = DatabaseHelper.GetInstance(null).getWritableDatabase();
            String query = PlayerTable.COLUMN_ID + "= ?";
            String[] args ={String.valueOf(playerId)};
            int deleted =database.delete(PlayerTable.TABLE_PLAYER, query,args);
            if(deleted > 0){
                return true;
            }
        }catch (Exception ex){

        }
        return false;
    }
    public List<PlayerCharacter> getPlayersByGroup(int groupId){
        List<PlayerCharacter> playerCharacterList = new ArrayList<>();
        try{
            SQLiteDatabase monsterDatabase = DatabaseHelper.GetInstance(null).getReadableDatabase();
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
                playerCharacter.setId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_ID)));
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
    public PlayerCharacter getPlayerById(int playerId){
        PlayerCharacter playerCharacter = null;
        try{
            SQLiteDatabase monsterDatabase = DatabaseHelper.GetInstance(null).getReadableDatabase();
            String[] returnColumns = PlayerTable.AllColumns();
            String query = PlayerTable.COLUMN_ID +"= ?";
            String[] arguments = {String.valueOf(playerId)};


            Cursor cursor = monsterDatabase.query(GroupTable.TABLE_GROUP,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    null );

            while(cursor.moveToNext()){
                playerCharacter = new PlayerCharacter();
                playerCharacter.setId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_ID)));
                playerCharacter.setLevel(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_LEVEL)));
                playerCharacter.setCharacterName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_CHARACTER_NAME)));
                playerCharacter.setGroupId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_GROUPID)));
                playerCharacter.setDnDClass(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_CLASS)));
                playerCharacter.setArmorClass(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_AC)));
                playerCharacter.setPlayerName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_PLAYER_NAME)));

            }
            cursor.close();
        }catch (Exception e){

        }
        return playerCharacter;
    }
}
