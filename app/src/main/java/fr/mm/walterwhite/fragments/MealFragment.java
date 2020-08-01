package fr.mm.walterwhite.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.MealRecyclerViewAdapter;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.dao.impl.ConsommationDao;
import fr.mm.walterwhite.fragments.models.ConsommationViewModel;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.views.NewConsoActivity;


public class MealFragment extends Fragment   {

    private RecyclerView listMeals;
    private final List<Consommation> consoList = new ArrayList<>();
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    private DatePickerDialog MainDatePicker;
    private TextView MainDateTxtView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public static MealFragment newInstance() {
        return new MealFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meal_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handleMeals();
        handleButton();
        handleMainDatePicker();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleMainDatePicker() {

        android.icu.text.SimpleDateFormat dateFormat = new android.icu.text.SimpleDateFormat("dd/MM/yyyy");
        String strTodayDate = dateFormat.format(Calendar.getInstance().getTime());

        MainDateTxtView=getView().findViewById(R.id.MainDateTextView);
        MainDateTxtView.setText(strTodayDate);
        MainDateTxtView.setOnClickListener(new View.OnClickListener() {

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
                                MainDateTxtView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                MainDatePicker.show();
            }
        });
    }










    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NewConsoActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void handleMeals() {
        // Get ListView object from xml
        this.listMeals= getView().findViewById(R.id.listMeals);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        listMeals.setLayoutManager(layoutManager);


        List<fr.mm.walterwhite.fragments.models.MealViewModel> listMModels =new ArrayList<>();
        for(String mealSel: Constants.MEALS) {
            double points=0;
            ConsommationDao db = new ConsommationDao(getActivity());
            // db.createDefaultIfNeed();
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            List<Consommation> list = db.getConsommations(formattedDate, mealSel);
            List<ConsommationViewModel> listCModels =new  ArrayList<>();
            for(Consommation conso:list){
                points+= conso.getEatenPoints();
                ConsommationViewModel cm=new ConsommationViewModel(conso.getEatenName(),conso.getEatenPoints()+"",conso.getEatenPortion()+"gr");
                listCModels.add(cm);

            }
            fr.mm.walterwhite.fragments.models.MealViewModel mm=new fr.mm.walterwhite.fragments.models.MealViewModel(mealSel, listCModels, points+"");

            listMModels.add(mm);

        }


        //instantiate your adapter with the list of genres
        MealRecyclerViewAdapter adapter = new MealRecyclerViewAdapter(getActivity(), listMModels);
        listMeals.setAdapter(adapter);



    }










}