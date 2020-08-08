package fr.mm.walterwhite.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mm.walterwhite.dao.impl.IWeightDao;
import fr.mm.walterwhite.models.Weight;

public class WeightRepository {

    private final IWeightDao dao;

    public WeightRepository(IWeightDao itemDao) { this.dao = itemDao; }

    // --- GET ---

    public LiveData<List<Weight>> getWeights(){ return this.dao.getWeights(); }
    public LiveData<Weight> getLastWeight(){ return this.dao.getLastWeight();}
    public LiveData<Weight> getFirstWeight(){ return this.dao.getFirstWeight();}


    // --- CREATE ---

    public void createWeight(Weight item){ dao.addWeight(item); }


}
