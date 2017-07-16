package com.example.nreed.awildencounterappears.Classes.Objects;

/**
 * Created by Niki on 7/15/2017.
 */

public class PlayerCharacter {
    private String CharacterName;
    private String PlayerName;
    private int Level;
    private int ArmorClass;
    private String Class;

    public String getCharacterName() {
        return CharacterName;
    }

    public void setCharacterName(String characterName) {
        CharacterName = characterName;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getArmorClass() {
        return ArmorClass;
    }

    public void setArmorClass(int armorClass) {
        ArmorClass = armorClass;
    }


}
