package fr.mm.walterwhite.models;

import java.io.Serializable;

public class Consommation implements Serializable  {


        private int eatenId;
        private String eatenName;
        private String eatenMeal;
        private int eatenPoints;
        private String eatenDate;
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

    public Consommation(int eatenId, String eatenName, String eatenMeal, int eatenPoints, String eatenDate, double eatenPortion) {
            this.eatenId= eatenId;
            this.eatenName= eatenName;
            this.eatenMeal= eatenMeal;
            this.eatenPoints= eatenPoints;
            this.eatenDate= eatenDate;
            this.eatenPortion= eatenPortion;
        }



        @Override
        public String toString()  {
            return this.eatenName;
        }

}
