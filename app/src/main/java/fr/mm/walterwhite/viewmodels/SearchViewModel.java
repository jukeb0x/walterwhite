package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;

import fr.mm.walterwhite.api.models.Search;
import fr.mm.walterwhite.repositories.OpenfoodfactsRepository;

public class SearchViewModel extends ViewModel {

    // REPOSITORIES
    private final OpenfoodfactsRepository consoDataSource;
    private final Executor executor;
    private LiveData<Search> searchResponseLiveData;




    public SearchViewModel(OpenfoodfactsRepository consoDataSource, Executor executor) {
        this.consoDataSource = consoDataSource;
        this.executor = executor;
    }

    public void init() {
        searchResponseLiveData = consoDataSource.getSearchResponseLiveData();
    }


    // -------------
    // FOR ITEM
    // -------------


    public LiveData<Search> getSearchResponseLiveData() {
        return searchResponseLiveData;
    }


    public void searchProducts(String name, int page) {
        executor.execute(() -> {
            consoDataSource.searchProducts(name, page);
        });
    }







}
