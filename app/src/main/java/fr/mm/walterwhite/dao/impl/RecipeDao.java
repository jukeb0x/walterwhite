package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.models.Weight;

public class RecipeDao extends DatabaseHelper implements IRecipeDao{


    public RecipeDao(Context context) {
        super(context);
    }


    @Override
    public List<Weight> getRecipes() {
        return null;
    }

    @Override
    public void addRecipe(Recipe name) {

    }
}
