package com.example.nreed.awildencounterappears.Classes.DataAdapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.Database.MonsterTable;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.DatabaseHelpers.DatabaseHelper;
import com.example.nreed.awildencounterappears.Classes.Objects.DifficultyEnum;
import com.example.nreed.awildencounterappears.Classes.Objects.Lists.MonsterList;
import com.example.nreed.awildencounterappears.Classes.Objects.Monster;
import com.example.nreed.awildencounterappears.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nreed on 7/7/2017.
 */

public class MonsterDataAdapter {
    private Context ctx;
    public double XP;
    public int MonstersToKill=0;
    private MonsterList _monsters = new MonsterList();

    public MonsterDataAdapter(){ }

    public MonsterDataAdapter(Context context){
        ctx=context;
    }


    public MonsterList GetMonsterList(String environment, int maxCR, double startingXP, int numberOfMonsters, int numberOfPartyMembers,int partyLevel,  DifficultyEnum difficulty){

        if(numberOfMonsters == 0) numberOfMonsters = 1;
        XP=startingXP;
        double remainingXP = startingXP;
        try{
            ArrayList<Integer> groups = BuildGroup(numberOfMonsters);
            MonstersToKill = numberOfMonsters;

            boolean isMonsterSelected = false;

            int numberOfGroups =groups.size();
            double xpPerGroup = remainingXP / numberOfGroups;

            _monsters = getMonstersFromTable(partyLevel,xpPerGroup);
            MonsterList selectedMonsters = new MonsterList();
            for (Integer index: groups) {
                isMonsterSelected=false;
                if(remainingXP > 25) {
                    while (!isMonsterSelected) {
                        if(remainingXP < 25) break;
                        Monster m = selectBestMonster(index, xpPerGroup);
                        if(m == null) break;
                        if (m !=null && m.getXP() != 0) {
                            if (selectedMonsters.contains(m)) {
                                Monster selectedMonster = selectedMonsters.getById(m.getID());
                                selectedMonster.setCount(selectedMonster.getCount() + index);
                                remainingXP = remainingXP - (m.getXP() * index);
                            } else {
                                m.setCount(index);
                                selectedMonsters.add(m);
                                remainingXP = remainingXP - (m.getXP() * index);
                                break;
                            }
                            isMonsterSelected = true;
                        }
                    }
                }
            }

//            while(remainingXP>=25){
//                Monster m = selectBestMonster(1,remainingXP);
//                if(m.getXP() != 0){
//                    if (selectedMonsters.contains(m)) {
//                        Monster selectedMonster = selectedMonsters.getById(m.getID());
//                        selectedMonster.setCount(selectedMonster.getCount() + 1);
//                        remainingXP = remainingXP - m.getXP();
//                    } else {
//                        Double floor= Math.floor(remainingXP/m.getXP());
//                        m.setCount(Integer.parseInt(String.valueOf(floor.intValue())));
//                        selectedMonsters.add(m);
//                        remainingXP = remainingXP - (m.getXP() * m.getCount());
//                    }
//                }
//            }


            return selectedMonsters;

        }catch (Exception ex){
            return null;
        }
    }

    @NonNull
    public MonsterList getMonstersFromTable(int partyLevel, double xpPerMonster) {

        MonsterList monsters = new MonsterList();
        if(xpPerMonster < 25) xpPerMonster = 25;
        try{
            SQLiteDatabase monsterDatabase = DatabaseHelper.GetInstance(null).getReadableDatabase();
            String[] returnColumns = MonsterTable.AllColumns();
            String query = MonsterTable.COLUMN_CR + "<= ? and " + MonsterTable.COLUMN_XP +"<= ?";
            String[] arguments = {String.valueOf(partyLevel), String.valueOf(xpPerMonster)};//{String.valueOf(maxCR), String.valueOf(xp), "%"+environment+"%"};


            Cursor cursor = monsterDatabase.query(MonsterTable.TABLE_MONSTER,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    "RANDOM()" );

            while(cursor.moveToNext()){

                Monster monster = new Monster();
                monster.setXP(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_XP)));
                monster.setAlignment(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_ALIGNMENT)));
                monster.setArmorClass(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_AC)));
                monster.setChallengeRating(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_CR)));
                monster.setID(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_ID)));
                monster.setName(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_NAME)));
                monster.setSources(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_SOURCES)));
                monster.setXP(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_XP)));
                monster.setHP(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_HP)));
                monster.setCount(1);
                monsters.add(monster);
        }
            cursor.close();
            return monsters;
        }
        catch (Exception e){
            return monsters;
        }
    }

    public MonsterList getMonstersFromTable( double xpPerMonster) {

        MonsterList monsters = new MonsterList();
        if(xpPerMonster < 25) xpPerMonster = 25;
        try{
            SQLiteDatabase monsterDatabase = MainActivity.databaseHelper.getReadableDatabase();
            String[] returnColumns = MonsterTable.AllColumns();
            String query = MonsterTable.COLUMN_XP +"<= ? and " + MonsterTable.COLUMN_XP + ">= ?";
            String[] arguments = { String.valueOf(xpPerMonster), String.valueOf(xpPerMonster *1.1)};//{String.valueOf(maxCR), String.valueOf(xp), "%"+environment+"%"};


            Cursor cursor = monsterDatabase.query(MonsterTable.TABLE_MONSTER,
                    returnColumns,
                    query,
                    arguments,
                    null,
                    null,
                    "Random() limit 5" );

            while(cursor.moveToNext()){

                Monster monster = new Monster();
                monster.setXP(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_XP)));
                monster.setAlignment(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_ALIGNMENT)));
                monster.setArmorClass(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_AC)));
                monster.setChallengeRating(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_CR)));
                monster.setID(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_ID)));
                monster.setName(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_NAME)));
                monster.setSources(cursor.getString(cursor.getColumnIndex(MonsterTable.COLUMN_SOURCES)));
                monster.setXP(cursor.getInt(cursor.getColumnIndex(MonsterTable.COLUMN_XP)));
                monster.setCount(1);
                monsters.add(monster);
            }
            cursor.close();
            monsterDatabase.close();
            return monsters;
        }
        catch (Exception e){
            return monsters;
        }
    }

    private ArrayList<Integer> BuildGroup(int totalNumberOfMonsters){
        Random randomNumber = new Random(totalNumberOfMonsters+1);
        ArrayList<Integer> group = new ArrayList<>();
        while(totalNumberOfMonsters>0){
            int numberOfMonsters = randomNumber.nextInt(totalNumberOfMonsters+1);
            totalNumberOfMonsters= totalNumberOfMonsters - numberOfMonsters;

            group.add(numberOfMonsters);
        }
        return group;
    }

    private Monster selectBestMonster(int numberToSelect, double remainingXP){

        Monster monster =new Monster();
        monster = _monsters.getByClosestXP(Math.ceil(remainingXP/numberToSelect));
        return monster;
    }
}
