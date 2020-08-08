package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.viewmodels.IngredientViewModel;


public class CreateIngredientFragment extends Fragment {

    private IngredientViewModel itemViewModel;

    public CreateIngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_ingredient, container, false);
    }

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(IngredientViewModel.class);
        // this.itemViewModel.init(USER_ID);
    }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.weight_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataComplete())
                    createItem();
                else {
                    Toast toast = Toast.makeText(getActivity(), "Il manque des informations", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    private boolean checkDataComplete()
    {return true;}



    private void createItem(){
      //  Ingredient item = new Ingredient(1,this.poundsDate.getText().toString(), Double.parseDouble(this.poundsInput.getText().toString()));
       // this.itemViewModel.createIngredient(item);
    }




}