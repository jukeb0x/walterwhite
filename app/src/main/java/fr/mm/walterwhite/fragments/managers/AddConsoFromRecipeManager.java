package fr.mm.walterwhite.fragments.managers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.mm.walterwhite.models.Recipe;

public class AddConsoFromRecipeManager extends AddConsoManager<Recipe> {



    public AddConsoFromRecipeManager(@NonNull Fragment frag){
        super(frag);
    }

    @Override
    protected void handleRadioGroup(Recipe volumesResponse) {
        servingRadio.setVisibility(View.VISIBLE);
        gramRadio.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void preload(Recipe volumesResponse) {

    }

    @Override
    protected double getComputedPoints(Recipe volumesResponse, double quantity) {
        //appeler methodes pour recuperer les points du contenu et ponderer avec les portions
        return 0;
    }

    @Override
    protected String buildItemName(Recipe product) {
        return product.getRecipeName();
    }

    @Override
    protected double buildItemPortion(Recipe product, double quantity) {
        return quantity;
    }


}

