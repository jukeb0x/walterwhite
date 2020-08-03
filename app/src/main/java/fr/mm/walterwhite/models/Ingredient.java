package fr.mm.walterwhite.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Ingredient implements Serializable  {

        @PrimaryKey(autoGenerate = true)
        private int ingredientId;
        @ColumnInfo(name = "name")
        private String ingredientName;
        @ColumnInfo(name = "barcode")
        private String ingredientBarcode;
        @ColumnInfo(name = "calorie")
        private double ingredientCalorie;
        @ColumnInfo(name = "sugar")
        private double ingredientSugar;
        @ColumnInfo(name = "fat")
        private double ingredientFat;
        @ColumnInfo(name = "prot")
        private double ingredientProt;
        @ColumnInfo(name = "portion")
        private double ingredientPortion;

    public String getIngredientBarcode() {
        return ingredientBarcode;
    }

    public void setIngredientBarcode(String ingredientBarcode) {
        this.ingredientBarcode = ingredientBarcode;
    }

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

    public double getIngredientProt() {
        return ingredientProt;
    }

    public void setIngredientProt(double ingredientPro) {
        this.ingredientProt = ingredientPro;
    }

    public double getIngredientPortion() {
        return ingredientPortion;
    }

    public void setIngredientPortion(double ingredientPortion) {
        this.ingredientPortion = ingredientPortion;
    }

    public Ingredient(int ingredientId, String ingredientName, double ingredientCalorie, double ingredientSugar, double ingredientFat , double ingredientProt, double ingredientPortion) {
            this.ingredientId= ingredientId;
            this.ingredientName= ingredientName;
            this.ingredientCalorie= ingredientCalorie;
            this.ingredientSugar= ingredientSugar;
            this.ingredientFat= ingredientFat;
            this.ingredientProt= ingredientProt;
            this.ingredientPortion= ingredientPortion;
        }



        @Override
        public String toString()  {
            return this.ingredientName;
        }

}
