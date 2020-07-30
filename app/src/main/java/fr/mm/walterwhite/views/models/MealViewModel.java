package fr.mm.walterwhite.views.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MealViewModel  extends ExpandableGroup<ConsommationViewModel> {

    private String points;
    private String name;
    private List<ConsommationViewModel> consos;

    public MealViewModel(String name, List<ConsommationViewModel> items, String points) {
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

    public List<ConsommationViewModel> getConsos() {
        return consos;
    }

    public void setConsos(List<ConsommationViewModel> consos) {
        this.consos = consos;
    }
}


