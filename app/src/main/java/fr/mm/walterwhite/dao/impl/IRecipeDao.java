package fr.mm.walterwhite.dao.impl;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.mm.walterwhite.models.Recipe;
@Dao
public interface IRecipeDao {

    @Query("SELECT * FROM RECIPE")
    public LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM RECIPE WHERE name like '%'+:pName+'%'")
    public LiveData<List<Recipe>> getRecipesByName(String pName);

    @Insert
    public void addRecipe(Recipe name);

}
