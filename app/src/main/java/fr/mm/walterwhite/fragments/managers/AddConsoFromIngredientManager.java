package fr.mm.walterwhite.fragments.managers;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.services.Calculator;

public class AddConsoFromIngredientManager extends AddConsoManager<Ingredient> {



    public AddConsoFromIngredientManager(@NonNull Fragment frag){
        super(frag);
    }

    @Override
    protected void handleRadioGroup(Ingredient volumesResponse) {
        servingRadio.setVisibility(View.INVISIBLE);
        gramRadio.setVisibility(View.VISIBLE);
    }

    @Override
    protected void preload(Ingredient volumesResponse) {

    }


    @Override
    protected double getComputedPoints(Ingredient volumesResponse, double quantity) {
        double points=0;
        Ingredient product = (Ingredient) volumesResponse;
        double portion=quantity/((Ingredient) volumesResponse).getIngredientPortion();
        Log.w("Mathilde", "refresh gr portion="+portion);
        points=Calculator.computePortionPoints(product.getIngredientCalorie(), product.getIngredientFat(),
                product.getIngredientSugar(), product.getIngredientProt(), portion,frag.getContext());
        return 0;
    }

    @Override
    protected String buildItemName(Ingredient product) {
        return product.getIngredientName();
    }

    @Override
    protected double buildItemPortion(Ingredient product, double quantity) {
        return quantity;
    }





}

