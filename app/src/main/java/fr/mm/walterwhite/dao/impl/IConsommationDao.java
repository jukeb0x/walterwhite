package fr.mm.walterwhite.dao.impl;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.mm.walterwhite.models.Consommation;

@Dao
public interface IConsommationDao {

    @Delete
    public void deleteConsommation(Consommation conso);

    @Update
    public void  updateConsommation(Consommation conso);

    @Query("SELECT * FROM CONSOMMATION WHERE date=:pDate")
    public LiveData<List<Consommation>> getConsommations(String pDate);

    @Insert
    public void addConsommation(Consommation conso);

    @Query("SELECT * FROM CONSOMMATION WHERE date BETWEEN :sDate AND :eDate")
    public LiveData<List<Consommation>> getPeriodConsommations(String sDate, String eDate);

}
