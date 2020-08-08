package fr.mm.walterwhite.dao.impl;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.mm.walterwhite.models.Weight;
@Dao
public interface IWeightDao {

    @Query("SELECT * FROM WEIGHT")
    public LiveData<List<Weight>> getWeights();

    @Query("SELECT * FROM WEIGHT ORDER BY weightId DESC LIMIT 1")
    public LiveData<Weight> getLastWeight();

    @Query("SELECT * FROM WEIGHT ORDER BY date ASC LIMIT 1")
    public LiveData<Weight> getFirstWeight();



    @Insert
    public void addWeight(Weight weight);

}
