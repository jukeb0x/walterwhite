package fr.mm.walterwhite.fragments;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.apache.commons.lang.StringUtils;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.utils.DateUtils;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ExpressFragment extends Fragment {

    private ConsoViewModel itemViewModel;
    private TextView expressPoints;
    private TextView expressName;
    private Spinner expressMeal;

    public ExpressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        expressPoints=getView().findViewById(R.id.express_points);
        expressName=getView().findViewById(R.id.express_name);
        expressMeal=getView().findViewById(R.id.express_meals);
        configureViewModel();
        handleSpinner();
        handleButton();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_express, container, false);
    }

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ConsoViewModel.class);
        // this.itemViewModel.init(USER_ID);
    }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.AddExpressIngredientButton);
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

    protected void handleSpinner() {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Constants.MEALS);
        expressMeal.setAdapter(adapter);

    }

    private boolean checkDataComplete()
    {
        return StringUtils.isNotBlank(expressName.getText().toString())
                && StringUtils.isNotBlank(expressMeal.getSelectedItem().toString())
                && StringUtils.isNotBlank(expressPoints.getText().toString());
    }



    private void createItem(){
        android.icu.text.SimpleDateFormat dateFormat = new android.icu.text.SimpleDateFormat("dd-MM-yyyy");
        int year = Calendar.getInstance().get(Calendar.YEAR);  // 2012
        int month = Calendar.getInstance().get(Calendar.MONTH);  // 9 - October!!!
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);  // 5
        String chosenDate= DateUtils.formateDate(year, month, day);
        Log.w("Mathilde", "chosenDate=" + chosenDate);
         Consommation item = new Consommation(this.expressName.getText().toString(),expressMeal.getSelectedItem().toString(),
                 Integer.parseInt(this.expressPoints.getText().toString()),chosenDate,0);
         this.itemViewModel.createConsommation(item);
        Toast toast = Toast.makeText(getActivity(), "Consommation ajoutée !", Toast.LENGTH_SHORT);
        toast.show();
    }
}