package fr.mm.walterwhite.models;

import java.io.Serializable;

public class Weight implements Serializable  {

        private int weightId;
        private String weightDate;
        private double weight;



    public Weight(int weightId, String weightDate, double weight) {
            this.weightId= weightId;
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
