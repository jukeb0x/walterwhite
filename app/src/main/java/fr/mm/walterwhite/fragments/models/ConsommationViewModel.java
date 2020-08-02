package fr.mm.walterwhite.fragments.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class ConsommationViewModel implements Parcelable {

    private String name;
    private String points;
    private String amount;

    public ConsommationViewModel(String name, String points, String amount) {
        this.name = name;
        this.points = points;
        this.amount = amount;
    }

    protected ConsommationViewModel(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsommationViewModel)) return false;

        ConsommationViewModel artist = (ConsommationViewModel) o;

        return getName() != null ? getName().equals(artist.getName()) : artist.getName() == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, points, amount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConsommationViewModel> CREATOR = new Creator<ConsommationViewModel>() {
        @Override
        public ConsommationViewModel createFromParcel(Parcel in) {
            return new ConsommationViewModel(in);
        }

        @Override
        public ConsommationViewModel[] newArray(int size) {
            return new ConsommationViewModel[size];
        }
    };
}

