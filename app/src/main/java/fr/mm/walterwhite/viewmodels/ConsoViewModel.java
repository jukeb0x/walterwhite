package fr.mm.walterwhite.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.repositories.ConsommationRepository;

public class ConsoViewModel  extends ViewModel {

    // REPOSITORIES
    private final ConsommationRepository consoDataSource;
    private final Executor executor;




    public ConsoViewModel(ConsommationRepository consoDataSource, Executor executor) {
        this.consoDataSource = consoDataSource;
        this.executor = executor;
    }


    // -------------
    // FOR ITEM
    // -------------

    public LiveData<List<Consommation>> getConsommations(String date) {
        return consoDataSource.getConsommations(date);
    }

    public LiveData<List<Consommation>> getPeriodConsommations(String startDate,String endDate) {
        return consoDataSource.getPeriodConsommations(startDate,endDate);
    }

    public void createConsommation(Consommation item) {
        executor.execute(() -> {
            consoDataSource.createConsommation(item);
        });
    }

    public void deleteConsommation(Consommation item) {
        executor.execute(() -> {
            consoDataSource.deleteConsommation(item);
        });
    }

    public void updateConsommation(Consommation item) {
        executor.execute(() -> {
            consoDataSource.updateConsommation(item);
        });
    }


}
