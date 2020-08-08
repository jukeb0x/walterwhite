package fr.mm.walterwhite.api;

import fr.mm.walterwhite.BuildConfig;
import fr.mm.walterwhite.api.utils.ApiUtils;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class CommonApiManager {

        private static CommonApiManager instance;
        private ProductsAPI productsApi;
        private final JacksonConverterFactory jacksonConverterFactory;

        public static CommonApiManager getInstance() {
            if (instance == null) {
                instance = new CommonApiManager();
            }

            return instance;
        }

        private CommonApiManager() {
            jacksonConverterFactory = JacksonConverterFactory.create();
        }


        /**
         * Defines and returns getOpenFoodApiService
         */
        public ProductsAPI getProductsApi() {
            if (productsApi == null) {
                productsApi = createOpenFoodApiService();
            }

            return productsApi;
        }



        /**
         * Initialising OpenFoodAPIService using Retrofit
         */
        private ProductsAPI createOpenFoodApiService() {
            productsApi = new Retrofit.Builder()
                    .baseUrl(BuildConfig.HOST)
                    .client(ApiUtils.httpClientBuilder())
                    .addConverterFactory(jacksonConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()
                    .create(ProductsAPI.class);

            return productsApi;
        }
}
