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
    private double calorie_100gr;
    @JsonProperty(ApiFields.Keys.SATURATED_FAT_100GR)
    private double fat_100gr;
    @JsonProperty(ApiFields.Keys.SUGARS_100gr)
    private double sugar_100gr;
    @JsonProperty(ApiFields.Keys.PROTEINS_100GR)
    private double proteins_100gr;
    @JsonProperty(ApiFields.Keys.ENERGY_KCAL_SERVING)
    private double calorie_serving;
    @JsonProperty(ApiFields.Keys.SATURATED_FAT_SERVING)
    private double fat_serving;
    @JsonProperty(ApiFields.Keys.SUGARS_SERVING)
    private double sugar_serving;
    @JsonProperty(ApiFields.Keys.PROTEINS_SERVING)
    private double proteins_serving;



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calorie", calorie_100gr)
                .append("fat", fat_100gr)
                .append("sugar", sugar_100gr)
                .append("proteins", proteins_100gr)
                .toString();
    }


    public double getCalorie_100gr() {
        return calorie_100gr;
    }

    public void setCalorie_100gr(double calorie_100gr) {
        this.calorie_100gr = calorie_100gr;
    }

    public double getFat_100gr() {
        return fat_100gr;
    }

    public void setFat_100gr(double fat_100gr) {
        this.fat_100gr = fat_100gr;
    }

    public double getSugar_100gr() {
        return sugar_100gr;
    }

    public void setSugar_100gr(double sugar_100gr) {
        this.sugar_100gr = sugar_100gr;
    }

    public double getProteins_100gr() {
        return proteins_100gr;
    }

    public void setProteins_100gr(double proteins_100gr) {
        this.proteins_100gr = proteins_100gr;
    }

    public double getCalorie_serving() {
        return calorie_serving;
    }

    public void setCalorie_serving(double calorie_serving) {
        this.calorie_serving = calorie_serving;
    }

    public double getFat_serving() {
        return fat_serving;
    }

    public void setFat_serving(double fat_serving) {
        this.fat_serving = fat_serving;
    }

    public double getSugar_serving() {
        return sugar_serving;
    }

    public void setSugar_serving(double sugar_serving) {
        this.sugar_serving = sugar_serving;
    }

    public double getProteins_serving() {
        return proteins_serving;
    }

    public void setProteins_serving(double proteins_serving) {
        this.proteins_serving = proteins_serving;
    }
}
