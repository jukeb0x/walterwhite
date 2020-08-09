package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;

import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.repositories.ProductRepository;

public class ProductViewModel extends ViewModel {

    // REPOSITORIES
    private final ProductRepository consoDataSource;
    private final Executor executor;
    private LiveData<Product> productResponseLiveData;




    public ProductViewModel(ProductRepository consoDataSource, Executor executor) {
        this.consoDataSource = consoDataSource;
        this.executor = executor;
    }

    public void init() {
        productResponseLiveData = consoDataSource.getProductResponseLiveData();
    }


    // -------------
    // FOR ITEM
    // -------------



    public LiveData<Product> getProductResponseLiveData() {
        return productResponseLiveData;
    }



    public void getProductByBarcode(String code) {
        executor.execute(() -> {
            consoDataSource.getProductByBarcode(code);
        });
    }






}
