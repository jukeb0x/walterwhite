package fr.mm.walterwhite.api.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import fr.mm.walterwhite.api.ApiFields;


class ProductStringConverter extends StdConverter<String, String> {
    public String convert(String value) {
        return StringEscapeUtils.unescapeHtml(value).replace("\\'", "'").replace("&quot", "'");

    }
}

class StringToDoubleConverter extends StdConverter<String, Double> {
    public Double convert(String value) {
        Log.w("Mathilde ", "convert string" + value.replaceAll("[^\\d.]", ""));
        value=value.replaceAll("[^\\d.]", "");
        if(StringUtils.isNotBlank(value)) {
           return Double.parseDouble(value);
        }
        else return 0.0;
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiFields.Keys.NUTRIMENTS)
    private Nutriments nutriments;
    @JsonProperty(ApiFields.Keys.BRANDS)
    private String brands;
    @JsonProperty(ApiFields.Keys.PRODUCT_NAME)
    @JsonDeserialize(converter = ProductStringConverter.class)
    private String productName;
    @JsonProperty(ApiFields.Keys.BARCODE)
    private String code;
    @JsonProperty(ApiFields.Keys.SERVING_QUANTITY)
    @JsonDeserialize(converter = StringToDoubleConverter.class)
    private double serving_quantity;
    @JsonProperty(ApiFields.Keys.SERVING_SIZE)
    @JsonDeserialize(converter = StringToDoubleConverter.class)
    private double serving_size;

    private double gramme_quantity=100;

    public double getGramme_quantity() {
        return gramme_quantity;
    }

    public void setGramme_quantity(double gramme_quantity) {
        this.gramme_quantity = gramme_quantity;
    }

    public double getServing_quantity() {
        return serving_quantity;
    }

    public void setServing_quantity(double serving_quantity) {
        this.serving_quantity = serving_quantity;
    }

    public double getServing_size() {
        return serving_size;
    }

    public void setServing_size(double serving_size) {
        this.serving_size = serving_size;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }







    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("productName", productName)
                .append("nutriments", nutriments)
                .append("brands", brands)
                .append("serving_size", serving_size)
                .append("serving_quantity", serving_quantity)
                .toString();
    }
}
