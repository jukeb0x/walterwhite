package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.models.RecipeContent;
import fr.mm.walterwhite.models.Weight;

public interface IRecipeContentDao {

    public List<RecipeContent> getRecipeContents(int recipeId);
    public void addRecipeContent(RecipeContent rec);
    public void deleteRecipeContent(RecipeContent rec);

}
