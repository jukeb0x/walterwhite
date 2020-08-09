package fr.mm.walterwhite.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;

import fr.mm.walterwhite.repositories.ConsommationRepository;
import fr.mm.walterwhite.repositories.IngredientRepository;
import fr.mm.walterwhite.repositories.ProductRepository;
import fr.mm.walterwhite.repositories.RecipeContentRepository;
import fr.mm.walterwhite.repositories.RecipeRepository;
import fr.mm.walterwhite.repositories.SearchRepository;
import fr.mm.walterwhite.repositories.WeightRepository;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;
import fr.mm.walterwhite.viewmodels.IngredientViewModel;
import fr.mm.walterwhite.viewmodels.ProductViewModel;
import fr.mm.walterwhite.viewmodels.SearchViewModel;
import fr.mm.walterwhite.viewmodels.WeightViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ConsommationRepository dataSourceConsommation;
    private final IngredientRepository dataSourceIngredient;
    private final RecipeRepository dataSourceRecipe;
    private final RecipeContentRepository dataSourceRecipeContent;
    private final WeightRepository dataSourceWeight;
    private final ProductRepository dataSourceProduct;
    private final SearchRepository dataSourceSearch;
    private final Executor executor;

    public ViewModelFactory(ConsommationRepository dataSourceConsommation, IngredientRepository dataSourceIngredient,
                                            RecipeRepository dataSourceRecipe, RecipeContentRepository dataSourceRecipeContent, WeightRepository dataSourceWeight , ProductRepository dataSourceProduct,SearchRepository dataSourceSearch,Executor executor) {
        this.dataSourceConsommation = dataSourceConsommation;
        this.dataSourceIngredient = dataSourceIngredient;
        this.dataSourceRecipe = dataSourceRecipe;
        this.dataSourceRecipeContent = dataSourceRecipeContent;
        this.dataSourceWeight = dataSourceWeight;
        this.dataSourceProduct = dataSourceProduct;
        this.dataSourceSearch = dataSourceSearch;

        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ConsoViewModel.class)) {
            return (T) new ConsoViewModel(dataSourceConsommation, executor);
        }
        else if (modelClass.isAssignableFrom(WeightViewModel.class)) {
            return (T) new WeightViewModel(dataSourceWeight, executor);
        }
        else if (modelClass.isAssignableFrom(IngredientViewModel.class)) {
            return (T) new IngredientViewModel(dataSourceIngredient, executor);
        }
        else if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(dataSourceProduct, executor);
        }
        else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(dataSourceSearch, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
