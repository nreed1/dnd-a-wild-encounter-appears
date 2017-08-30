package com.example.nreed.awildencounterappears.Classes.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Niki on 7/15/2017.
 */

public class PlayerCharacter implements Parcelable{
    private int Id;
    private String CharacterName;
    private String PlayerName;
    private int Level;
    private int ArmorClass;
    private String DnDClass;
    private int GroupId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public String getDnDClass() {
        return DnDClass;
    }

    public void setDnDClass(String dnDClass) {
        DnDClass = dnDClass;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public PlayerCharacter(){

    }

    public PlayerCharacter(Parcel in){
        setId(in.readInt());
        setCharacterName(in.readString());
        setPlayerName(in.readString());
        setLevel(in.readInt());
        setArmorClass(in.readInt());
        setDnDClass(in.readString());
        setId(in.readInt());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getCharacterName());
        parcel.writeString(getPlayerName());
        parcel.writeInt(getLevel());
        parcel.writeInt(getArmorClass());
        parcel.writeString(getDnDClass());
        parcel.writeInt(getGroupId());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PlayerCharacter createFromParcel(Parcel in) {
            return new PlayerCharacter(in);
        }

        public PlayerCharacter[] newArray(int size) {
            return new PlayerCharacter[size];
        }
    };
}
