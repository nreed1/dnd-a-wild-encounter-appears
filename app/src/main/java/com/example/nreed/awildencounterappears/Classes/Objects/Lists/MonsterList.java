package com.example.nreed.awildencounterappears.Classes.Objects.Lists;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.nreed.awildencounterappears.Classes.Objects.Monster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Niki on 7/8/2017.
 */

public class MonsterList extends ArrayList<Monster> implements Parcelable {
    public MonsterList(){

    }

    public boolean contains(Monster o) {
        for(int i =0; i<this.size(); i++){
            if(this.get(i).getID() == o.getID()){
                return true;
            }
        }
        return false;
    }
    public MonsterList TrimListByXP(double xp){
        MonsterList monstersToRemove = new MonsterList();
        for(int i =0; i<this.size(); i++){
            if(this.get(i).getXP() > xp){
                monstersToRemove.add(this.get(i));
            }
        }

        this.removeAll(monstersToRemove);
        return this;
    }


    public Monster getById(int id){
        for(int i =0; i<this.size(); i++){
            if(this.get(i).getID() == id){
                return this.get(i);
            }
        }
        return null;
    }

    public Monster getByClosestXP(double xp){
        if(xp < 25) return null ;
        Monster bestBelow = new Monster();

        for(Monster m: this){
            if(m.getXP() == xp){
                return m;
            }
            if(m.getXP() < xp && m.getXP() > bestBelow.getXP()){
                bestBelow = m;
            }

        }
        return bestBelow;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public MonsterList(Parcel in){
        in.readTypedList(this, Monster.CREATOR);
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MonsterList createFromParcel(Parcel in) {
            return new MonsterList(in);
        }

        public MonsterList[] newArray(int size) {
            return new MonsterList[size];
        }
    };
}
