package fr.mm.walterwhite.fragments.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import fr.mm.walterwhite.models.Consommation;

public class MealViewModel  extends ExpandableGroup<Consommation> {

    private String points;
    private String name;
    private List<Consommation> consos;

    public MealViewModel(String name, List<Consommation> items, String points) {
        super(name, items);
        this.name = name;
        this.points = points;
        this.consos = items;
    }






    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Consommation> getConsos() {
        return consos;
    }

    public void setConsos(List<Consommation> consos) {
        this.consos = consos;
    }
}


