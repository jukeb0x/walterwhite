package fr.mm.walterwhite.dao.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.models.Consommation;

public class ParametersDao extends DatabaseHelper implements IParametersDao{


    public ParametersDao(Context context) {
        super(context);
    }


    @Override
    public double getAllowedPoints() {
        return 0;
    }

    @Override
    public double gettWeeklyAllowedPoints() {
        return 0;
    }

    @Override
    public String getFirstDay() {
        return null;
    }
}
