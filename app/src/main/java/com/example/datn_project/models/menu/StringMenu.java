package com.example.datn_project.models.menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class StringMenu implements Serializable, Parcelable {
    private Monday monday;
    private Tuesday tuesday;
    private Wednesday wednesday;
    private Thursday thursday;
    private Friday friday;

    public static final Creator<StringMenu> CREATOR = new Creator<StringMenu>() {
        @Override
        public StringMenu createFromParcel(Parcel in) {
            return new StringMenu(in);
        }

        @Override
        public StringMenu[] newArray(int size) {
            return new StringMenu[size];
        }
    };

    private StringMenu(Parcel in) {
        setMonday((Monday) in.readSerializable());
        setTuesday((Tuesday) in.readSerializable());
        setWednesday((Wednesday) in.readSerializable());
        setThursday((Thursday) in.readSerializable());
        setFriday((Friday) in.readSerializable());
    }


    public StringMenu(Monday monday, Tuesday tuesday, Wednesday wednesday, Thursday thursday, Friday friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public Monday getMonday() {
        return monday;
    }

    public void setMonday(Monday monday) {
        this.monday = monday;
    }

    public Tuesday getTuesday() {
        return tuesday;
    }

    public void setTuesday(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    public Wednesday getWednesday() {
        return wednesday;
    }

    public void setWednesday(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    public Thursday getThursday() {
        return thursday;
    }

    public void setThursday(Thursday thursday) {
        this.thursday = thursday;
    }

    public Friday getFriday() {
        return friday;
    }

    public void setFriday(Friday friday) {
        this.friday = friday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(getMonday());
        dest.writeSerializable(getTuesday());
        dest.writeSerializable(getWednesday());
        dest.writeSerializable(getThursday());
        dest.writeSerializable(getFriday());
    }
}
