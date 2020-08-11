package fr.mm.walterwhite.fragments.managers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.apache.commons.lang.StringUtils;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.services.Calculator;
import fr.mm.walterwhite.utils.DateUtils;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;

public abstract class AddConsoManager<T> {

    public ConsoViewModel consoViewModel;
    protected Dialog amountPickerDialog;
    protected RadioGroup group;
    public boolean isServing=false;
    protected EditText pv;
    protected TextView pointsSymbol;
    protected Spinner mealsSel;
    protected TextView amountDate;
    protected View servingRadio;
    protected View gramRadio;
    protected Button bs;
    protected Button bc;
    protected Fragment frag;
    boolean preload=false;

    public AddConsoManager(@NonNull Fragment frag, boolean preload){
        this.frag=frag;
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(frag.getActivity());
        this.consoViewModel = ViewModelProviders.of(frag, mViewModelFactory).get(ConsoViewModel.class);
    }

    public AddConsoManager(@NonNull Fragment frag){
        this.frag=frag;
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(frag.getActivity());
        this.consoViewModel = ViewModelProviders.of(frag, mViewModelFactory).get(ConsoViewModel.class);
    }





    public void displayToast(String toast) {
        if(frag.getActivity() != null && toast != null) {
            Toast.makeText(frag.getActivity(), toast, Toast.LENGTH_SHORT).show();

        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void handleMainDatePicker() {

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
                MainDatePicker = new DatePickerDialog(frag.getActivity(),
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
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(frag.getActivity(), R.layout.support_simple_spinner_dropdown_item, Constants.MEALS);
        mealsSel.setAdapter(adapter);

    }


    protected abstract void handleRadioGroup(final T volumesResponse);
    protected abstract void preload(final T volumesResponse);

    public void showPoundValuePickerDialog(final T volumesResponse){
        amountPickerDialog = new Dialog(frag.getActivity());
        amountPickerDialog.setTitle("Entrez une quantité");
        amountPickerDialog.setContentView(R.layout.amountpickerdialog_layout);
        pointsSymbol= amountPickerDialog.findViewById(R.id.amountPointLabel);
        amountDate=amountPickerDialog.findViewById(R.id.amountDateValue);
        bc = (Button) amountPickerDialog.findViewById(R.id.amountPickerCancelButton);
        bs = (Button) amountPickerDialog.findViewById(R.id.amountPickerSetButton);
        pv = (EditText) amountPickerDialog.findViewById(R.id.amountPicker);
        group = (RadioGroup) amountPickerDialog.findViewById(R.id.radioAmountMode);
        servingRadio = amountPickerDialog.findViewById(R.id.radioServing);
        gramRadio = amountPickerDialog.findViewById(R.id.radioGr);

        handleRadioGroup(volumesResponse);
        mealsSel= amountPickerDialog.findViewById(R.id.amount_meals);
        handleSpinner();
        handleMainDatePicker();
        preload(volumesResponse);
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


    protected void refreshPoints(T volumesResponse){

      /*
        int points=0;
        if(isServing && volumesResponse instanceof Product) {
            Product product = (Product) volumesResponse;
            double portion=quantity*product.getServing_quantity();
            Log.w("Mathilde", "refresh serving portion ="+portion);
            points=Calculator.computePortionPoints(product.getNutriments().getCalorie_serving(), product.getNutriments().getFat_serving(),
                    product.getNutriments().getSugar_serving(), product.getNutriments().getProteins_serving(), quantity);
        }else if(volumesResponse instanceof Product){
            Product product = (Product) volumesResponse;
            double portion=quantity/((Product) volumesResponse).getGramme_quantity();
            Log.w("Mathilde", "refresh gr portion="+portion);
            points=Calculator.computePortionPoints(product.getNutriments().getCalorie_100gr(), product.getNutriments().getFat_100gr(),
                    product.getNutriments().getSugar_100gr(), product.getNutriments().getProteins_100gr(), portion);
        }else if(volumesResponse instanceof Ingredient){
            Ingredient product = (Ingredient) volumesResponse;
            double portion=quantity/((Ingredient) volumesResponse).getIngredientPortion();
            Log.w("Mathilde", "refresh gr portion="+portion);
            points=Calculator.computePortionPoints(product.getIngredientCalorie(), product.getIngredientFat(),
                    product.getIngredientSugar(), product.getIngredientProt(), portion);
        }else if(volumesResponse instanceof Recipe){
           //appeler methodes pour recuperer les points du contenu et ponderer avec les portions
        }*/

        if(null!=pointsSymbol) {
            int points=Calculator.computeRoundPoints(computePoints(volumesResponse));
            pointsSymbol.setText(points+"");
        }


    }

    protected double computePoints(T volumesResponse){
        double quantity=0;
        if(StringUtils.isNotBlank(pv.getText().toString())){
            quantity= Double.parseDouble((pv.getText().toString()));
        }
        return getComputedPoints(volumesResponse, quantity);
    }


    protected abstract double getComputedPoints(T volumesResponse, double quantity);



    protected void createItem(T product){

        if (checkDataComplete()) {
            /*double portion=Double.parseDouble(pv.getText().toString());
            String name="";
            if(product instanceof Product){
                Product prod= (Product) product;

                if(isServing) {
                    portion=Double.parseDouble(pv.getText().toString())*prod.getServing_quantity();
                }
                name=prod.getProductName();
            }
           else if(product instanceof Ingredient){
                Ingredient prod= (Ingredient) product;
               name=prod.getIngredientName();
            }
            else if(product instanceof Recipe){
                Recipe prod= (Recipe) product;
                name=prod.getRecipeName();
            }*/
            double portion=Double.parseDouble(pv.getText().toString());
            String name=buildItemName(product);
            portion=buildItemPortion(product, portion);
            Consommation item = new Consommation(name, mealsSel.getSelectedItem().toString(),
                    computePoints(product), amountDate.getText().toString(), portion);
            this.consoViewModel.createConsommation(item);
            Log.w("Mathilde","create an item"+item.toString());
        }else{
            Toast toast = Toast.makeText(frag.getActivity(), "Il manque des informations", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    protected abstract String buildItemName(T product);
    protected abstract double buildItemPortion(T product, double quantity);



    protected boolean checkDataComplete()
    {
        return StringUtils.isNotBlank(pointsSymbol.getText().toString())
                && StringUtils.isNotBlank(mealsSel.getSelectedItem().toString())
                && StringUtils.isNotBlank(amountDate.getText().toString())
                && StringUtils.isNotBlank(pv.getText().toString());
    }

}

