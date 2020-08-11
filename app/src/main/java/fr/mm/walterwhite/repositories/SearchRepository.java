package fr.mm.walterwhite.repositories;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.mm.walterwhite.api.CommonApiManager;
import fr.mm.walterwhite.api.ProductsAPI;
import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.api.models.Search;
import fr.mm.walterwhite.api.utils.ApiUtils;
import fr.mm.walterwhite.dao.impl.IIngredientDao;
import fr.mm.walterwhite.dao.impl.IRecipeDao;
import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.models.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SearchRepository {

    @NonNull
    private final ProductsAPI api;
    private final IIngredientDao ingDao;
   // private final IIngredientDao zeroDao;
    private final IRecipeDao recDao;

    private MutableLiveData<Search> searchResponseLiveData=new MutableLiveData<>();
    private LiveData<List<Ingredient>> ingredientsResponseLiveData;
    private LiveData<List<Recipe>> recipesResponseLiveData;
    private MutableLiveData<Product> productResponseLiveData=new MutableLiveData<Product>();
    private static final String FIELDS_TO_FETCH_FACETS = "brands,product_name_fr,quantity,nutriments,code,serving_size,serving_quantity";
    public static final String HEADER_USER_AGENT_SEARCH = "Search";
    public static final String HEADER_USER_AGENT_SCAN = "Scan";
    @NonNull
    private final Context context;


    public SearchRepository(@NonNull Context context1, @Nullable String customApiUrl, IIngredientDao ingDao1, IRecipeDao recDao1) {
        context = context1;
        if (customApiUrl != null) {
            api = new Retrofit.Builder()
                    .baseUrl(customApiUrl)
                    .client(ApiUtils.httpClientBuilder())
                   /* .addConverterFactory(ScalarsConverterFactory.create())*/
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
                    .create(ProductsAPI.class);
        } else {
            api = CommonApiManager.getInstance().getProductsApi();
        }
        recDao=recDao1;
        ingDao=ingDao1;


    }

    // --- GET ---

    public void searchProducts(String name) {
        searchProducts(name, 1);
       // searchZeroList(name);
    }




    public  LiveData<List<Ingredient>> searchIngredients(String name) {
        Log.w("Mathilde","ing "+"%"+name+"%");
        return ingDao.getIngredients("%"+name+"%");
    }

    public  LiveData<List<Recipe>> searchRecipes(String name) {
        return recDao.getRecipesByName("%"+name+"%");
    }



    private void searchProducts(String name, int page) {
        api.searchProductByName(FIELDS_TO_FETCH_FACETS,name, page)
                .enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        if (response.body() != null) {
                            searchResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {
                        searchResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Search> getSearchResponseLiveData() {
        return searchResponseLiveData;
    }







    private boolean checkNet(@NonNull Context context)
    {
        return ApiUtils.isNetworkConnected(context);
    }

}
