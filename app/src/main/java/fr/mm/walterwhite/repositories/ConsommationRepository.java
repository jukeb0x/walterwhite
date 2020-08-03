package fr.mm.walterwhite.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mm.walterwhite.dao.impl.IConsommationDao;
import fr.mm.walterwhite.models.Consommation;

public class ConsommationRepository {

    private final IConsommationDao dao;

    public ConsommationRepository(IConsommationDao itemDao) { this.dao = itemDao; }

    // --- GET ---

    public LiveData<List<Consommation>> getConsommations(String date){ return this.dao.getConsommations(date); }

    // --- CREATE ---

    public void createConsommation(Consommation item){ dao.addConsommation(item); }

    // --- DELETE ---
    public void deleteConsommation(Consommation item){ dao.deleteConsommation(item); }

    // --- UPDATE ---
    public void updateConsommation(Consommation item){ dao.updateConsommation(item); }
}
