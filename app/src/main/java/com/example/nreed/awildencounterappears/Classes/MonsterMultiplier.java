package com.example.nreed.awildencounterappears.Classes;

/**
 * Created by Niki on 7/8/2017.
 */

public class MonsterMultiplier {

    public static double GetMonsterMultiplier(int numberOfMonsters, int numberOfPartyMembers){
        if(numberOfMonsters==0) numberOfMonsters=1;
        double multiplier = 1;

        if(numberOfMonsters == 1) multiplier = 1;
        else if(numberOfMonsters == 2) multiplier = 1.5;
        else if(3 <= numberOfMonsters && numberOfMonsters <= 6) multiplier = 2;
        else if(7 <= numberOfMonsters && numberOfMonsters <= 10) multiplier = 2.5;
        else if(11 <= numberOfMonsters && numberOfMonsters <= 14) multiplier = 3;
        else multiplier = 4;

        if(numberOfPartyMembers < 3 && numberOfMonsters < 11) multiplier -= .5;
        else if (numberOfPartyMembers <3) multiplier--;
        else if (numberOfPartyMembers > 5 && numberOfMonsters < 11) multiplier+=.5;
        else if(numberOfPartyMembers > 5) multiplier++;

        return multiplier;
    }

    public static double GetMonsterMultiplier(int numberOfMonsters){
        double multiplier = 1;

        if(numberOfMonsters == 1) multiplier = 1;
        else if(numberOfMonsters == 2) multiplier = 1.5;
        else if(3 <= numberOfMonsters && numberOfMonsters <= 6) multiplier = 2;
        else if(7 <= numberOfMonsters && numberOfMonsters <= 10) multiplier = 2.5;
        else if(11 <= numberOfMonsters && numberOfMonsters <= 14) multiplier = 3;
        else multiplier = 4;

        return multiplier;
    }
}
