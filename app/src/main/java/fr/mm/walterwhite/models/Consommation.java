package fr.mm.walterwhite.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Consommation implements Serializable, Parcelable {

        @PrimaryKey(autoGenerate = true)
        private int eatenId;
        @ColumnInfo(name = "name")
        private String eatenName;
        @ColumnInfo(name = "meal")
        private String eatenMeal;
        @ColumnInfo(name = "points")
        private int eatenPoints;
        @ColumnInfo(name = "date")
        private String eatenDate;
        @ColumnInfo(name = "portion")
        private double eatenPortion;


    public int getEatenId() {
        return eatenId;
    }

    public void setEatenId(int eatenId) {
        this.eatenId = eatenId;
    }

    public String getEatenName() {
        return eatenName;
    }

    public void setEatenName(String eatenName) {
        this.eatenName = eatenName;
    }

    public String getEatenMeal() {
        return eatenMeal;
    }

    public void setEatenMeal(String eatenMeal) {
        this.eatenMeal = eatenMeal;
    }

    public int getEatenPoints() {
        return eatenPoints;
    }

    public void setEatenPoints(int eatenPoints) {
        this.eatenPoints = eatenPoints;
    }

    public String getEatenDate() {
        return eatenDate;
    }

    public void setEatenDate(String eatenDate) {
        this.eatenDate = eatenDate;
    }

    public double getEatenPortion() {
        return eatenPortion;
    }

    public void setEatenPortion(double eatenPortion) {
        this.eatenPortion = eatenPortion;
    }

    public Consommation(String eatenName, String eatenMeal, int eatenPoints, String eatenDate, double eatenPortion) {
            this.eatenName= eatenName;
            this.eatenMeal= eatenMeal;
            this.eatenPoints= eatenPoints;
            this.eatenDate= eatenDate;
            this.eatenPortion= eatenPortion;
        }

    protected Consommation(Parcel in) {
        eatenName = in.readString();
    }

    @Override
    public String toString()  {
        return this.eatenName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eatenName);
    }
    public static final Creator<Consommation> CREATOR = new Creator<Consommation>() {
        @Override
        public Consommation createFromParcel(Parcel in) {
            return new Consommation(in);
        }

        @Override
        public Consommation[] newArray(int size) {
            return new Consommation[size];
        }
    };
}
