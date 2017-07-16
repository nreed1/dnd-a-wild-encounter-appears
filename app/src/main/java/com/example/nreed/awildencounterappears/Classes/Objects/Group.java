package com.example.nreed.awildencounterappears.Classes.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Niki on 7/16/2017.
 */

public class Group implements Parcelable{
    private int groupId;
    private String groupName;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Group(){

    }
    public Group(Parcel in){
        setGroupId(in.readInt());
        setGroupName(in.readString());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getGroupId());
        parcel.writeString(getGroupName());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
