package fr.mm.walterwhite.models;

import java.io.Serializable;

public class Ingredient implements Serializable  {


        private int ingredientId;
        private String ingredientName;
        private double ingredientCalorie;
        private double ingredientSugar;
        private double ingredientFat;
        private double ingredientPro;
        private double ingredientPortion;


    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientCalorie() {
        return ingredientCalorie;
    }

    public void setIngredientCalorie(double ingredientCalorie) {
        this.ingredientCalorie = ingredientCalorie;
    }

    public double getIngredientSugar() {
        return ingredientSugar;
    }

    public void setIngredientSugar(double ingredientSugar) {
        this.ingredientSugar = ingredientSugar;
    }

    public double getIngredientFat() {
        return ingredientFat;
    }

    public void setIngredientFat(double ingredientFat) {
        this.ingredientFat = ingredientFat;
    }

    public double getIngredientPro() {
        return ingredientPro;
    }

    public void setIngredientPro(double ingredientPro) {
        this.ingredientPro = ingredientPro;
    }

    public double getIngredientPortion() {
        return ingredientPortion;
    }

    public void setIngredientPortion(double ingredientPortion) {
        this.ingredientPortion = ingredientPortion;
    }

    public Ingredient(int ingredientId, String ingredientName, double ingredientCalorie, double ingredientSugar, double ingredientFat , double ingredientPro, double ingredientPortion) {
            this.ingredientId= ingredientId;
            this.ingredientName= ingredientName;
            this.ingredientCalorie= ingredientCalorie;
            this.ingredientSugar= ingredientSugar;
            this.ingredientFat= ingredientFat;
            this.ingredientPro= ingredientPro;
            this.ingredientPortion= ingredientPortion;
        }



        @Override
        public String toString()  {
            return this.ingredientName;
        }

}
