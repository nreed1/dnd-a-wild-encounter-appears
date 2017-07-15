package com.example.nreed.awildencounterappears.Classes.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nreed on 7/6/2017.
 */

public class Monster  implements Parcelable{
    private int ID;
    private String Name;
    private String Size;
    private String[] Habitats;
    private int XP;
    private int ChallengeRating;
    private int Initiative;
    private int ArmorClass;
    private String Alignment;
    private String Section;
    private String Tags;
    private String Sources;
    private int Count;
    private int HP;

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getArmorClass() {
        return ArmorClass;
    }

    public void setArmorClass(int armorClass) {
        ArmorClass = armorClass;
    }

    public String getAlignment() {
        return Alignment;
    }

    public void setAlignment(String alignment) {
        Alignment = alignment;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        this.Tags = tags;
    }

    public String getSources() {
        return Sources;
    }

    public void setSources(String sources) {
        Sources = sources;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getHabitats() {
        return Habitats;
    }

    public void setHabitats(String[] habitats) {
        Habitats = habitats;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getChallengeRating() {
        return ChallengeRating;
    }

    public void setChallengeRating(int challengeRating) {
        ChallengeRating = challengeRating;
    }

    public int getInitiative() {
        return Initiative;
    }

    public void setInitiative(int initiative) {
        Initiative = initiative;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Monster(){

    }
    public Monster(Parcel in){
        setID(in.readInt());
        setXP(in.readInt());
        setName(in.readString());
        setArmorClass(in.readInt());
        setSources(in.readString());
        setChallengeRating(in.readInt());
        setHP(in.readInt());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getID());
        parcel.writeInt(getXP());
        parcel.writeString(getName());
        parcel.writeInt(getArmorClass());
        parcel.writeString(getSources());
        parcel.writeInt(getChallengeRating());
        parcel.writeInt(getHP());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Monster createFromParcel(Parcel in) {
            return new Monster(in);
        }

        public Monster[] newArray(int size) {
            return new Monster[size];
        }
    };
}
