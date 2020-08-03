package fr.mm.walterwhite.dao.impl;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.mm.walterwhite.models.RecipeContent;

@Dao
public interface IRecipeContentDao {

    @Query("SELECT * FROM RECIPECONTENT WHERE recipeId=:pRecipeId")
    public LiveData<List<RecipeContent>> getRecipeContents(int pRecipeId);
    @Insert
    public void addRecipeContent(RecipeContent rec);
    @Delete
    public void deleteRecipeContent(RecipeContent rec);

    @Update
    public void updateRecipeContent(RecipeContent rec);

}
