package fr.mm.walterwhite.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mm.walterwhite.dao.impl.IRecipeContentDao;
import fr.mm.walterwhite.models.RecipeContent;

public class RecipeContentRepository {

    private final IRecipeContentDao dao;

    public RecipeContentRepository(IRecipeContentDao itemDao) { this.dao = itemDao; }

    // --- GET ---

    public LiveData<List<RecipeContent>> getRecipeContents(int id){ return this.dao.getRecipeContents(id); }

    // --- CREATE ---

    public void createRecipeContent(RecipeContent item){ dao.addRecipeContent(item); }

    // --- DELETE ---
    public void deleteRecipeContent(RecipeContent item){ dao.deleteRecipeContent(item); }

    // --- UPDATE ---
    public void updateRecipeContent(RecipeContent item){ dao.updateRecipeContent(item); }
}
