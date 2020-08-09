package fr.mm.walterwhite.repositories;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import fr.mm.walterwhite.api.CommonApiManager;
import fr.mm.walterwhite.api.ProductsAPI;
import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.api.models.ProductState;
import fr.mm.walterwhite.api.models.Search;
import fr.mm.walterwhite.api.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OpenfoodfactsRepository {

    @NonNull
    private final ProductsAPI api;
    private MutableLiveData<Search> searchResponseLiveData=new MutableLiveData<>();
    private MutableLiveData<Product> productResponseLiveData=new MutableLiveData<Product>();
    private static final String FIELDS_TO_FETCH_FACETS = "brands,product_name_fr,quantity,nutriments,code";
    public static final String HEADER_USER_AGENT_SEARCH = "Search";
    public static final String HEADER_USER_AGENT_SCAN = "Scan";
    @NonNull
    private final Context context;


    public OpenfoodfactsRepository(@NonNull Context context, @Nullable String customApiUrl) {
        this.context = context;
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

    }

    // --- GET ---

    public void searchProducts(String name, int page) {
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

    public void getProductByBarcode(String code) {
        Log.w("Mathilde repo", "code=" + code);
        if(checkNet(context)) {
            api.getProductByBarcode(code, FIELDS_TO_FETCH_FACETS, HEADER_USER_AGENT_SCAN)
                    .enqueue(new Callback<ProductState>() {
                        @Override
                        public void onResponse(Call<ProductState> call, Response<ProductState> response) {
                            Log.w("Mathilde", "response=" + response.body());
                            if (response.body() != null) {
                                ProductState state = response.body();
                                if(state.getStatus()==1) {
                                    productResponseLiveData.postValue(state.getProduct());
                                } else  productResponseLiveData.postValue(null);
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductState> call, Throwable t) {
                            Log.w("Mathilde", "error=" + t.getMessage());
                            productResponseLiveData.postValue(null);

                        }
                    });
        } else  productResponseLiveData.postValue(null);
    }

    public LiveData<Product> getProductResponseLiveData() {
        return productResponseLiveData;
    }


    private boolean checkNet(@NonNull Context context)
    {
        return ApiUtils.isNetworkConnected(context);
    }

}
