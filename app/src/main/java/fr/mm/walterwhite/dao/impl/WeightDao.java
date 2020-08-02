package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Weight;

public class WeightDao extends DatabaseHelper implements IWeightDao{


    public WeightDao(Context context) {
        super(context);
    }

    @Override
    public List<Weight> getWeights() {
        Weight conso=new Weight(1, "2020-07-20", 99);
        Weight conso2=new Weight(1, "2020-07-22", 98);
        Weight conso3=new Weight(1, "2020-07-24", 97);
        Weight conso4=new Weight(1, "2020-07-26", 96);
        List<Weight> consos=new ArrayList<>();
        consos.add(conso);
        consos.add(conso2);
        consos.add(conso3);
        consos.add(conso4);
        return consos;
    }

    @Override
    public Weight getLastWeight() {
        return null;
    }

    @Override
    public void addWeight(Weight weight) {

    }
}
