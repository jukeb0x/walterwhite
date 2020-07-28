package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.models.Ingredient;

public interface IIngredientDao {

    public void deleteIngredient(Ingredient ing);
    public int updateIngredient(Ingredient ing);
    public List<Ingredient> getIngredients(String name);
    public void addIngredient(Ingredient ing);

}
