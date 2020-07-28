package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.models.RecipeContent;
import fr.mm.walterwhite.models.Weight;

public class RecipeContentDao extends DatabaseHelper implements IRecipeContentDao{


    public RecipeContentDao(Context context) {
        super(context);
    }


    @Override
    public List<RecipeContent> getRecipeContents(int recipeId) {
        return null;
    }

    @Override
    public void addRecipeContent(RecipeContent rec) {

    }

    @Override
    public void deleteRecipeContent(RecipeContent rec) {

    }
}
