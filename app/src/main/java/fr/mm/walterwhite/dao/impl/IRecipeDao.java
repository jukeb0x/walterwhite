package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.models.Weight;

public interface IRecipeDao {

    public List<Weight> getRecipes();
    public void addRecipe(Recipe name);

}
