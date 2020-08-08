package fr.mm.walterwhite.api;

import com.fasterxml.jackson.databind.JsonNode;

import fr.mm.walterwhite.api.models.ProductState;
import fr.mm.walterwhite.api.models.Search;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Define our Open Food Facts API endpoints.
 * All REST methods such as GET, POST, PUT, UPDATE, DELETE can be stated in here.
 */
public interface ProductsAPI {
    @GET("api/v0/product/{barcode}.json")
    Call<ProductState> getProductByBarcode(@Path("barcode") String barcode,
                                           @Query("fields") String fields,
                                           @Header("User-Agent") String header);

    @GET("api/v0/product/{barcode}.json")
    Single<ProductState> getProductByBarcodeSingle(@Path("barcode") String barcode,
                                                   @Query("fields") String fields,
                                                   @Header("User-Agent") String header);


    @GET("api/v0/product/{barcode}.json?fields=image_small_url,product_name,brands,quantity,image_url,nutrition_grade_fr,code")
    Call<ProductState> getShortProductByBarcode(@Path("barcode") String barcode,
                                                @Header("User-Agent") String header);



    @GET("api/v0/product/{barcode}.json?fields=ingredients")
    Single<JsonNode> getIngredientsByBarcode(@Path("barcode") String barcode);

    @GET("cgi/search.pl?search_simple=1&json=1&action=process")
    Call<Search> searchProductByName(@Query("fields") String fields,
                                       @Query("search_terms") String name,
                                       @Query("page") int page);

    @GET("brand/{brand}/{page}.json")
    Single<Search> getProductByBrands(@Path("brand") String brand,
                                      @Path("page") int page,
                                      @Query("fields") String fields);

    @GET("brand/{brand}/{page}.json")
    Single<Search> getProductByBrandsSingle(@Path("brand") String brand,
                                            @Path("page") int page,
                                            @Query("fields") String fields);
}
