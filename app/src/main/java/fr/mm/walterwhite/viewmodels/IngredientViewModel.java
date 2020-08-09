package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.repositories.IngredientRepository;

public class IngredientViewModel extends ViewModel {

    // REPOSITORIES
    private final IngredientRepository dataSource;
    private final Executor executor;




    public IngredientViewModel(IngredientRepository consoDataSource, Executor executor) {
        this.dataSource = consoDataSource;
        this.executor = executor;
    }


    // -------------
    // FOR ITEM
    // -------------

    public LiveData<List<Ingredient>> getIngredient(String name) {
        return dataSource.getIngredients(name);
    }


    public void createIngredient(Ingredient item) {
        executor.execute(() -> {
            dataSource.createIngredient(item);
        });
    }

    public void updateIngredient(Ingredient item) {
        executor.execute(() -> {
            dataSource.updateIngredient(item);
        });
    }


    public void deleteIngredient(Ingredient item) {
        executor.execute(() -> {
            dataSource.deleteIngredient(item);
        });
    }




}
