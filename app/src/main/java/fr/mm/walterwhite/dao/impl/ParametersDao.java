package fr.mm.walterwhite.dao.impl;

import android.content.Context;

public class ParametersDao  implements IParametersDao{


    public ParametersDao(Context context){
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
