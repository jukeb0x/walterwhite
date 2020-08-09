package fr.mm.walterwhite.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mm.walterwhite.dao.impl.IIngredientDao;
import fr.mm.walterwhite.models.Ingredient;

public class IngredientRepository {

    private final IIngredientDao dao;

    public IngredientRepository(IIngredientDao itemDao) { this.dao = itemDao; }

    // --- GET ---

    public LiveData<List<Ingredient>> getIngredients(String name){ return this.dao.getIngredients("%" + name+ "%"); }

    // --- CREATE ---

    public void createIngredient(Ingredient item){ dao.addIngredient(item); }

    // --- DELETE ---
    public void deleteIngredient(Ingredient item){ dao.deleteIngredient(item); }

    // --- UPDATE ---
    public void updateIngredient(Ingredient item){ dao.updateIngredient(item); }
}
