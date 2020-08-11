package fr.mm.walterwhite.fragments.managers;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.mm.walterwhite.models.Consommation;

public class UpdateConsoManager extends AddConsoManager<Consommation>{



    public UpdateConsoManager(@NonNull Fragment frag){
        super(frag, true);
    }

    @Override
    protected void handleRadioGroup(Consommation volumesResponse) {

    }

    @Override
    protected void preload(Consommation volumesResponse) {
        amountDate.setText(volumesResponse.getEatenDate());
        ArrayAdapter myAdap = (ArrayAdapter) mealsSel.getAdapter();
        int spinnerPosition =myAdap.getPosition(volumesResponse.getEatenMeal());
        Log.w("Mathilde","position"+spinnerPosition);
        mealsSel.setSelection(spinnerPosition);
        pointsSymbol.setText(volumesResponse.getRoundEatenPoints()+"");
        pv.setText(volumesResponse.getEatenPortion()+"");


    }

    @Override
    protected double getComputedPoints(Consommation volumesResponse, double quantity) {
        if(volumesResponse.getEatenPortion()>-1) {
            return quantity / volumesResponse.getEatenPortion() * volumesResponse.getEatenPoints();
        }else{
            return quantity*volumesResponse.getEatenPoints();
        }
    }

    @Override
    protected void createItem(Consommation product){

        if (checkDataComplete()) {
            double portion=Double.parseDouble(pv.getText().toString());
            product.setEatenPortion(portion);
            product.setEatenMeal(mealsSel.getSelectedItem().toString());
            product.setEatenPoints(getComputedPoints(product,portion));
            product.setEatenDate(amountDate.getText().toString());

            this.consoViewModel.updateConsommation(product);
            Log.w("Mathilde","update an item"+product.toString());
        }else{
            Toast toast = Toast.makeText(frag.getActivity(), "Il manque des informations", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected String buildItemName(Consommation product) {
        return null;
    }

    @Override
    protected double buildItemPortion(Consommation product, double quantity) {
        return 0;
    }




}

