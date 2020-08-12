package fr.mm.walterwhite.fragments.managers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.services.Calculator;

public class AddConsoFromProductManager extends AddConsoManager<Product>  {



    public AddConsoFromProductManager(@NonNull Fragment frag){
       super(frag);
    }





    @Override
    protected void handleRadioGroup(Product volumesResponse) {
        if(((Product) volumesResponse).getServing_quantity()!=0){
            servingRadio.setVisibility(View.VISIBLE);
            gramRadio.setVisibility(View.VISIBLE);
        }
        else {
            servingRadio.setVisibility(View.INVISIBLE);
            gramRadio.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void preload(Product volumesResponse) {

    }

    @Override
    protected double getComputedPoints(Product volumesResponse, double quantity) {
        double points=0;
        if(isServing ) {
            double portion=quantity*volumesResponse.getServing_quantity();
            points=Calculator.computePortionPoints(volumesResponse.getNutriments().getCalorie_serving(), volumesResponse.getNutriments().getFat_serving(),
                    volumesResponse.getNutriments().getSugar_serving(), volumesResponse.getNutriments().getProteins_serving(), quantity,frag.getContext());
        }else {
            double portion=quantity/((Product) volumesResponse).getGramme_quantity();
            points=Calculator.computePortionPoints(volumesResponse.getNutriments().getCalorie_100gr(), volumesResponse.getNutriments().getFat_100gr(),
                    volumesResponse.getNutriments().getSugar_100gr(), volumesResponse.getNutriments().getProteins_100gr(), portion,frag.getContext());
        }
        return points;
    }

    @Override
    protected String buildItemName(Product product) {
        return product.getProductName();
    }

    @Override
    protected double buildItemPortion(Product product, double quantity) {
        if(isServing) {
            return Double.parseDouble(pv.getText().toString())*product.getServing_quantity();
        }
        return quantity;
    }


}

