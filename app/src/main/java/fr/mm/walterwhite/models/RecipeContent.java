package fr.mm.walterwhite.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(foreignKeys = @ForeignKey(entity = Recipe.class,
        parentColumns = "recipeId",
        childColumns = "recipeId"))
public class RecipeContent implements Serializable  {

        @PrimaryKey(autoGenerate = true)
        private int recipeContentId;
        @ColumnInfo(name = "recipeId")
        private int recipeId;
        @ColumnInfo(name = "name")
        private String ingredientName;
        @ColumnInfo(name = "point")
        private double ingredientPoints;
        @ColumnInfo(name = "portion")
        private double ingredientPortion;



    public RecipeContent(int recipeContentId, int recipeId, String ingredientName , double ingredientPoints, double ingredientPortion) {
            this.recipeContentId= recipeContentId;
            this.recipeId= recipeId;
            this.ingredientName= ingredientName;
            this.ingredientPoints= ingredientPoints;
            this.ingredientPortion= ingredientPortion;
    }



    @Override
    public String toString()  {
        return ""+this.recipeContentId;
    }

    public int getRecipeContentId() {
        return recipeContentId;
    }

    public void setRecipeContentId(int recipeContentId) {
        this.recipeContentId = recipeContentId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientPoints() {
        return ingredientPoints;
    }

    public void setIngredientPoints(double ingredientPoints) {
        this.ingredientPoints = ingredientPoints;
    }

    public double getIngredientPortion() {
        return ingredientPortion;
    }

    public void setIngredientPortion(double ingredientPortion) {
        this.ingredientPortion = ingredientPortion;
    }
}
