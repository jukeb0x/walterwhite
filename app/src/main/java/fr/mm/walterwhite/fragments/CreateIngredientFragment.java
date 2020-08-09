package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.commons.lang.StringUtils;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.services.Calculator;

public class CreateIngredientFragment extends Fragment {

    private EditText calorieEditText;
    private EditText sugarEditText;
    private EditText fatEditText;
    private EditText proteinEditText;
    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText barcodeEditText;
    private TextView pointsValue;


    public CreateIngredientFragment() {}

    public static CreateIngredientFragment newInstance() {return new CreateIngredientFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calorieEditText=getView().findViewById(R.id.calorie_amount);
        sugarEditText=getView().findViewById(R.id.sugar_amount);
        fatEditText=getView().findViewById(R.id.fat_amount);
        proteinEditText=getView().findViewById(R.id.prot_amount);
        nameEditText=getView().findViewById(R.id.new_ingredient_name);
        quantityEditText=getView().findViewById(R.id.mass_amount);
        barcodeEditText=getView().findViewById(R.id.barcode);
        pointsValue=getView().findViewById(R.id.new_ingredient_points);


        calorieEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                onIngredientCompositionChange();
                return false;
            }
        });

        sugarEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                onIngredientCompositionChange();
                return false;
            }
        });

        fatEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                onIngredientCompositionChange();
                return false;
            }
        });

        proteinEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                onIngredientCompositionChange();
                return false;
            }
        });

        quantityEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                onIngredientCompositionChange();
                return false;
            }
        });

    }

    public void onIngredientCompositionChange(){

        if (quantityEditText.getText().toString().equals("") &&
            !quantityEditText.isFocused()){quantityEditText.setText("100");}

        double energy;
        double fat;
        double sugar;
        double protein;
        if (isEditTextValueNAN(calorieEditText.getText().toString())){energy=0;}
        else{energy=Double.parseDouble(calorieEditText.getText().toString());}
        if (isEditTextValueNAN(fatEditText.getText().toString())){fat=0;}
        else{fat=Double.parseDouble(fatEditText.getText().toString());}
        if (isEditTextValueNAN(sugarEditText.getText().toString())){sugar=0;}
        else{sugar=Double.parseDouble(sugarEditText.getText().toString());}
        if (isEditTextValueNAN(proteinEditText.getText().toString())){protein=0;}
        else{protein=Double.parseDouble(proteinEditText.getText().toString());}

        pointsValue.setText(String.valueOf(Calculator.computePoints(energy,fat,sugar,protein)));

    }

    private boolean isEditTextValueNAN(String value){
        return (StringUtils.isBlank(value) ||
                StringUtils.containsOnly(value,".") ||
                StringUtils.containsOnly(value,","));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_ingredient, container, false);
    }


}