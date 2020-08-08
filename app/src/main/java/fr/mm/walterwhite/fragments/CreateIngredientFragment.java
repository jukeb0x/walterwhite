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

import fr.mm.walterwhite.R;

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
        if (quantityEditText.getText().toString().equals("")){quantityEditText.setText("100");}


        pointsValue.setText("10");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_ingredient, container, false);
    }


}