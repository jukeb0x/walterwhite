package fr.mm.walterwhite.api.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import fr.mm.walterwhite.api.ApiFields;


/**
 * JSON representation of the product nutriments entry
 *
 * @see <a href="http://en.wiki.openfoodfacts.org/API#JSON_interface">JSON Structure</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutriments implements Serializable {
    private static final long serialVersionUID = 1L;



    @JsonProperty(ApiFields.Keys.ENERGY_KCAL_100GR)
    private double calorie;
    @JsonProperty(ApiFields.Keys.SATURATED_FAT_100GR)
    private double fat;
    @JsonProperty(ApiFields.Keys.SUGARS_100gr)
    private double sugar;
    @JsonProperty(ApiFields.Keys.PROTEINS_100GR)
    private double proteins;

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calorie", calorie)
                .append("fat", fat)
                .append("sugar", sugar)
                .append("proteins", proteins)
                .toString();
    }
}
