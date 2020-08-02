package fr.mm.walterwhite.fragments.models;

import androidx.lifecycle.ViewModel;

public class WeightViewModel extends ViewModel {

    private String date;
    private String weight;

    public WeightViewModel(String date, String weight) {
        super();
        this.date = date;
        this.weight = weight;
    }

    public WeightViewModel() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}