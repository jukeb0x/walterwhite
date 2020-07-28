package fr.mm.walterwhite.models;

import java.io.Serializable;

public class RecipeContent implements Serializable  {

        private int recipeContentId;
        private int recipeId;
        private String ingredientName;
        private double ingredientPoints;
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
