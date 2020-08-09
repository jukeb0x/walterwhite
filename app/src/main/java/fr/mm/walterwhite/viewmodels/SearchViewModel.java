package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

import fr.mm.walterwhite.api.models.Search;
import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.repositories.SearchRepository;

public class SearchViewModel extends ViewModel {

    // REPOSITORIES
    private final SearchRepository consoDataSource;
    private final Executor executor;
    private LiveData<Search> searchResponseLiveData;
    private LiveData<List<Ingredient>> ingredientsResponseLiveData=new LiveData<List<Ingredient>>(){};
    private LiveData<List<Recipe>> recipesResponseLiveData=new LiveData<List<Recipe>>() {};




    public SearchViewModel(SearchRepository consoDataSource, Executor executor) {
        this.consoDataSource = consoDataSource;
        this.executor = executor;
    }

    public void init() {

        searchResponseLiveData = consoDataSource.getSearchResponseLiveData();
        ingredientsResponseLiveData = consoDataSource.getSearchIngredientResponseLiveData();
        recipesResponseLiveData = consoDataSource.getSearchRecipeResponseLiveData();
    }


    // -------------
    // FOR ITEM
    // -------------


    public LiveData<Search> getSearchResponseLiveData() {
        return searchResponseLiveData;
    }
    public LiveData<List<Ingredient>> getSearchIngredientResponseLiveData() {
        return ingredientsResponseLiveData;
    }
    public LiveData<List<Recipe>> getSearchRecipeResponseLiveData() {
        return recipesResponseLiveData;
    }


    public void searchProducts(String name) {
        executor.execute(() -> {
            consoDataSource.searchProducts(name);
        });
    }







}
