package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.models.Ingredient;

public class IngredientDao extends DatabaseHelper implements IIngredientDao{


    public IngredientDao(Context context) {
        super(context);
    }


    @Override
    public void deleteIngredient(Ingredient ing) {

    }

    @Override
    public int updateIngredient(Ingredient ing) {
        return 0;
    }

    @Override
    public List<Ingredient> getIngredients(String name) {
        return null;
    }

    @Override
    public void addIngredient(Ingredient ing) {

    }
}
