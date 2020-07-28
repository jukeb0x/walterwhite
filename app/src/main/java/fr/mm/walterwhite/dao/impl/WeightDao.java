package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.models.Weight;

public class WeightDao extends DatabaseHelper implements IWeightDao{


    public WeightDao(Context context) {
        super(context);
    }

    @Override
    public List<Weight> getWeights() {
        return null;
    }

    @Override
    public Weight getLastWeight() {
        return null;
    }

    @Override
    public void addWeight(Weight weight) {

    }
}
