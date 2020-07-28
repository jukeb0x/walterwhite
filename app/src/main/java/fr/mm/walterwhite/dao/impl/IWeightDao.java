package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.models.Weight;

public interface IWeightDao {

    public List<Weight> getWeights();
    public Weight getLastWeight();
    public void addWeight(Weight weight);

}
