package com.example.nreed.awildencounterappears.Classes.Calculators;

import com.example.nreed.awildencounterappears.Classes.DataAdapters.XPThresholdByCharacterDataAdapter;
import com.example.nreed.awildencounterappears.Classes.Objects.DifficultyEnum;
import com.example.nreed.awildencounterappears.Classes.Objects.XPThresholdByCharacterLevel;

/**
 * Created by nreed on 7/6/2017.
 */

public class XPCalculator {
    private DifficultyEnum _difficulty;
    private double xpPool;

    public double getXpPool(){
        return xpPool;
    }

    public XPCalculator(DifficultyEnum difficulty){
        _difficulty = difficulty;
    }

    public double CalculatePartyXP(int numberOfPartyMembers, int partyLevel, double numberOfMonstersModifier){
        XPThresholdByCharacterDataAdapter dataAdapter = new XPThresholdByCharacterDataAdapter();
        XPThresholdByCharacterLevel xpThresholdByCharacterLevel = dataAdapter.GetXPThresholdByCharacterLevel(partyLevel);
        int partyXPLevel = 1 ;
        switch (_difficulty){
            case Easy:
                partyXPLevel = xpThresholdByCharacterLevel.Easy;
                break;
            case Medium:
                partyXPLevel = xpThresholdByCharacterLevel.Medium;
                break;
            case Hard:
                partyXPLevel = xpThresholdByCharacterLevel.Hard;
                break;
            case Deadly:
                partyXPLevel = xpThresholdByCharacterLevel.Deadly;
                break;
            default:
                partyXPLevel = xpThresholdByCharacterLevel.Easy;
                break;
        }

        xpPool= (numberOfPartyMembers * partyXPLevel)/numberOfMonstersModifier;
        return xpPool;
    }

    public int CalculatePartyMemberXP(int memberLevel){

        return 0;
    }

}
