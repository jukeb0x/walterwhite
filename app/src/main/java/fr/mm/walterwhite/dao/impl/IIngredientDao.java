package fr.mm.walterwhite.dao.impl;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.mm.walterwhite.models.Ingredient;

@Dao
public interface IIngredientDao {
    @Delete
    public void deleteIngredient(Ingredient ing);
    @Update
    public void  updateIngredient(Ingredient ing);
    @Query("SELECT * FROM INGREDIENT WHERE name like :pName")
    public LiveData<List<Ingredient>> getIngredients(String pName);
    @Insert
    public void addIngredient(Ingredient ing);

}
