package fr.mm.walterwhite.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.commons.lang.StringUtils;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.services.Calculator;
import fr.mm.walterwhite.utils.DateUtils;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;
import fr.mm.walterwhite.viewmodels.ProductViewModel;

public class ScanFragment extends Fragment {

    private ProductViewModel itemViewModel;
    private ConsoViewModel consoViewModel;
    private Dialog amountPickerDialog;
    private RadioGroup group;
    private boolean isServing=false;
    private EditText pv;
    private TextView pointsSymbol;
    private Spinner mealsSel;
    private TextView amountDate;
    private Button bs;
    private Button bc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configureViewModel();
        handleButton();



    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setPrompt("Scan a barcode").setCameraId(0).setBeepEnabled(false).setBarcodeImageEnabled(true).initiateScan();
    }

    private void displayToast(String toast) {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();

        }
    }

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductViewModel.class);
        itemViewModel.init();
        itemViewModel.getProductResponseLiveData().observe(getActivity(), new Observer<Product>() {
            @Override
            public void onChanged(Product volumesResponse) {
                if (volumesResponse != null) {
                   String toast = "Data changed"+volumesResponse.toString();
                     displayToast(toast);
                    Log.w("Mathilde", "data changed" + volumesResponse.toString());
                   showPoundValuePickerDialog(volumesResponse);
                   // adapter.setResults(volumesResponse.getItems());
                }
            }
        });

        this.consoViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ConsoViewModel.class);
        // this.itemViewModel.init(USER_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String toast = null;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
                displayToast(toast);


            }

            // At this point we may or may not have a reference to the activity
            //displayToast(toast);
            performSearch(result.getContents());
        }
    }
    public void performSearch(String code) {
        if(getActivity() != null) {
            Log.w("Mathilde", "code=" + code);
            itemViewModel.getProductByBarcode(code);
        }else  Log.w("Mathilde", "pas d'activity");
    }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.launchScanPoundsButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity(), "Veuillez saisir un poids", Toast.LENGTH_LONG);
                //toast.show();
                scanFromFragment();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleMainDatePicker() {

        android.icu.text.SimpleDateFormat dateFormat = new android.icu.text.SimpleDateFormat("dd/MM/yyyy");
        String strTodayDate = dateFormat.format(Calendar.getInstance().getTime());


        amountDate.setText(strTodayDate);
        amountDate.setOnClickListener(new View.OnClickListener() {

            private DatePickerDialog MainDatePicker;

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                MainDatePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String chosenDate= DateUtils.formateDate(year, monthOfYear, dayOfMonth);
                                amountDate.setText(chosenDate);
                            }
                        }, year, month, day);
                MainDatePicker.show();
            }
        });
    }
    protected void handleSpinner() {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Constants.MEALS);
        mealsSel.setAdapter(adapter);

    }

    private void showPoundValuePickerDialog(final Product volumesResponse){
        amountPickerDialog = new Dialog(getActivity());
        amountPickerDialog.setTitle("Entrez une quantité");
        amountPickerDialog.setContentView(R.layout.amountpickerdialog_layout);
        pointsSymbol= amountPickerDialog.findViewById(R.id.amountPointLabel);
        amountDate=amountPickerDialog.findViewById(R.id.amountDateValue);
        bc = (Button) amountPickerDialog.findViewById(R.id.amountPickerCancelButton);
        bs = (Button) amountPickerDialog.findViewById(R.id.amountPickerSetButton);
        pv = (EditText) amountPickerDialog.findViewById(R.id.amountPicker);
        group = (RadioGroup) amountPickerDialog.findViewById(R.id.radioAmountMode);
        View servingRadio = amountPickerDialog.findViewById(R.id.radioServing);

        if(volumesResponse.getServing_quantity()==0) {
            servingRadio.setVisibility(View.INVISIBLE);
        }
        else {
            servingRadio.setVisibility(View.VISIBLE);
        }
        mealsSel= amountPickerDialog.findViewById(R.id.amount_meals);
        handleSpinner();
        handleMainDatePicker();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioGr :
                        isServing=false;
                    case R.id.radioServing :
                        isServing=true;
                }
                refreshPoints(volumesResponse);

            }


        });
        bc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                amountPickerDialog.dismiss();
            }
        });
        bs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //amountPickerDialog.dismiss();
                //creer la conso
                refreshPoints(volumesResponse);
                createItem(volumesResponse);
                amountPickerDialog.dismiss();

            }
        });
        pv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    refreshPoints(volumesResponse);
                    return true;
                }
                return false;
            }
        });

        amountPickerDialog.show();
    }


    private void refreshPoints(Product volumesResponse){

        double quantity=0;
        if(StringUtils.isNotBlank(pv.getText().toString())){
            quantity= Double.parseDouble((pv.getText().toString()));
        }
        int points=0;
        if(isServing) {
            double portion=quantity*volumesResponse.getServing_quantity();
            Log.w("Mathilde", "refresh serving portion ="+portion);
            points=Calculator.computePortionPoints(volumesResponse.getNutriments().getCalorie_serving(), volumesResponse.getNutriments().getFat_serving(),
                    volumesResponse.getNutriments().getSugar_serving(), volumesResponse.getNutriments().getProteins_serving(), quantity);
        }else{
            double portion=quantity/volumesResponse.getGramme_quantity();
            Log.w("Mathilde", "refresh gr portion="+portion);
            points=Calculator.computePortionPoints(volumesResponse.getNutriments().getCalorie_100gr(), volumesResponse.getNutriments().getFat_100gr(),
                    volumesResponse.getNutriments().getSugar_100gr(), volumesResponse.getNutriments().getProteins_100gr(), portion);
        }
        if(null!=pointsSymbol) {
            pointsSymbol.setText(points+"");
        }


    }

    private void createItem(Product product){

        if (checkDataComplete()) {
            double portion=Double.parseDouble(pv.getText().toString());
            if(isServing) {
                portion=Double.parseDouble(pv.getText().toString())*product.getServing_quantity();
            }
            Consommation item = new Consommation(product.getProductName(), mealsSel.getSelectedItem().toString(),
                    Integer.parseInt(pointsSymbol.getText().toString()), amountDate.getText().toString(), portion);
            this.consoViewModel.createConsommation(item);
        }else{
            Toast toast = Toast.makeText(getActivity(), "Il manque des informations", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private boolean checkDataComplete()
    {
        return StringUtils.isNotBlank(pointsSymbol.getText().toString())
                && StringUtils.isNotBlank(mealsSel.getSelectedItem().toString())
                && StringUtils.isNotBlank(amountDate.getText().toString())
                && StringUtils.isNotBlank(pv.getText().toString());
    }

}

