package com.example.nreed.awildencounterappears.Classes.Objects;

/**
 * Created by nreed on 7/19/2017.
 */

public class StatBlock {
    private int totalXP;
    private int totalMonsters;
    private int totalEncounters;

    private static final StatBlock ourInstance = new StatBlock();

    public static StatBlock getInstance() {
        return ourInstance;
    }

    private StatBlock() {
    }


    public int getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }

    public int getTotalMonsters() {
        return totalMonsters;
    }

    public void setTotalMonsters(int totalMonsters) {
        this.totalMonsters = totalMonsters;
    }

    public int getTotalEncounters() {
        return totalEncounters;
    }

    public void setTotalEncounters(int totalEncounters) {
        this.totalEncounters = totalEncounters;
    }
}
