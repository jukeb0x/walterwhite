package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Consommation;

public interface IConsommationDao {

    public void deleteConsommation(Consommation conso);
    public int updateConsommation(Consommation conso);
    public List<Consommation> getConsommations(String date, String meal);
    public void addConsommation(Consommation conso);

}
