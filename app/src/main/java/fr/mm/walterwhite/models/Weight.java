package fr.mm.walterwhite.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Weight implements Serializable  {

        @PrimaryKey(autoGenerate = true)
        private int weightId;
        @ColumnInfo(name = "date")
        private String weightDate;
        @ColumnInfo(name = "pounds")
        private double weight;



    public Weight(String weightDate, double weight) {
            this.weightDate= weightDate;
            this.weight=weight;
    }

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public String getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(String weightDate) {
        this.weightDate = weightDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public String toString()  {
        return this.weight+"kg";
    }
}
