package fr.mm.walterwhite.models;

import java.io.Serializable;

public class Recipe implements Serializable  {

        private String recipeName;
        private int recipeId;



    public Recipe(int recipeId, String recipeName) {
            this.recipeId= recipeId;
            this.recipeName= recipeName;
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString()  {
            return this.recipeName;
        }

}
