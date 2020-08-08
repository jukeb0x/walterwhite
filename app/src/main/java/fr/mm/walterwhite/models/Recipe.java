package fr.mm.walterwhite.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Recipe implements Serializable  {

        @ColumnInfo(name = "name")
        private String recipeName;
        @PrimaryKey(autoGenerate = true)
        private int recipeId;



    public Recipe(String recipeName) {
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
