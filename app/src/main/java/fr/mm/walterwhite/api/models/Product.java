package fr.mm.walterwhite.api.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import fr.mm.walterwhite.api.ApiFields;


class ProductStringConverter extends StdConverter<String, String> {
    public String convert(String value) {
        Log.w("Mathilde ", "convert string" + value);
        return StringEscapeUtils.unescapeHtml(value).replace("\\'", "'").replace("&quot", "'");
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiFields.Keys.NUTRIMENTS)
    private Nutriments nutriments;

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

    @JsonProperty(ApiFields.Keys.PRODUCT_NAME)
    @JsonDeserialize(converter = ProductStringConverter.class)
    private String productName;
    @JsonProperty(ApiFields.Keys.BARCODE)
    private String code;

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    @JsonProperty(ApiFields.Keys.BRANDS)
    private String brands;






    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("productName", productName)
                .append("nutriments", nutriments)
                .append("brands", brands)
                .toString();
    }
}
