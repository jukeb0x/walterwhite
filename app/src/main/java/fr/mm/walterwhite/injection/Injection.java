package fr.mm.walterwhite.injection;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.repositories.ConsommationRepository;
import fr.mm.walterwhite.repositories.IngredientRepository;
import fr.mm.walterwhite.repositories.OpenfoodfactsRepository;
import fr.mm.walterwhite.repositories.RecipeContentRepository;
import fr.mm.walterwhite.repositories.RecipeRepository;
import fr.mm.walterwhite.repositories.WeightRepository;

public class Injection {

    public static IngredientRepository provideIngredientDataSource(Context context) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        return new IngredientRepository(database.ingredientDao());
    }
    public static ConsommationRepository provideConsommationDataSource(Context context) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        return new ConsommationRepository(database.consoDao());
    }
    public static RecipeRepository provideRecipeDataSource(Context context) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        return new RecipeRepository(database.recipeDao());
    }
    public static RecipeContentRepository provideRecipeContentDataSource(Context context) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        return new RecipeContentRepository(database.recipeContentDao());
    }

    public static WeightRepository provideWeightDataSource(Context context) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        return new WeightRepository(database.weightDao());
    }

    public static OpenfoodfactsRepository provideOpenFoodFactsDataSource(Context context) {
        return new OpenfoodfactsRepository(context,null);
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        WeightRepository dataSourceWeight = provideWeightDataSource(context);
        RecipeContentRepository dataSourceRecipeContent = provideRecipeContentDataSource(context);
        RecipeRepository dataSourceRecipe = provideRecipeDataSource(context);
        ConsommationRepository dataSourceConsommation = provideConsommationDataSource(context);
        IngredientRepository dataSourceIngredient = provideIngredientDataSource(context);
        OpenfoodfactsRepository dataSourceOFF = provideOpenFoodFactsDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceConsommation, dataSourceIngredient,dataSourceRecipe, dataSourceRecipeContent,dataSourceWeight ,dataSourceOFF, executor);
    }
}