package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

import fr.mm.walterwhite.models.Weight;
import fr.mm.walterwhite.repositories.WeightRepository;

public class WeightViewModel extends ViewModel {

    // REPOSITORIES
    private final WeightRepository dataSource;
    private final Executor executor;




    public WeightViewModel(WeightRepository consoDataSource, Executor executor) {
        this.dataSource = consoDataSource;
        this.executor = executor;
    }


    // -------------
    // FOR ITEM
    // -------------

    public LiveData<List<Weight>> getWeights() {
        return dataSource.getWeights();
    }

    public LiveData<Weight> getLastWeight() {
        return dataSource.getLastWeight();
    }

    public LiveData<Weight> getFirstWeight() {
        return dataSource.getFirstWeight();
    }

    public void createWeight(Weight item) {
        executor.execute(() -> {
            dataSource.createWeight(item);
        });
    }




}
