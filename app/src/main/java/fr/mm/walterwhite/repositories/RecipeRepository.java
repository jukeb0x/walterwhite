package fr.mm.walterwhite.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mm.walterwhite.dao.impl.IRecipeDao;
import fr.mm.walterwhite.models.Recipe;

public class RecipeRepository {

    private final IRecipeDao dao;

    public RecipeRepository(IRecipeDao itemDao) { this.dao = itemDao; }

    // --- GET ---

    public LiveData<List<Recipe>> getRecipesByName(String name){ return this.dao.getRecipesByName(name); }
    public LiveData<List<Recipe>> getRecipes(){ return this.dao.getRecipes(); }

    // --- CREATE ---

    public void createRecipe(Recipe item){ dao.addRecipe(item); }

}
