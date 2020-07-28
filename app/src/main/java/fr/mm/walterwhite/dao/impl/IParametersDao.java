package fr.mm.walterwhite.dao.impl;

import java.util.List;

import fr.mm.walterwhite.models.Weight;

public interface IParametersDao {

    public double getAllowedPoints();
    public double gettWeeklyAllowedPoints();
    public String getFirstDay();

}
