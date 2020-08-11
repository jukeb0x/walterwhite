package fr.mm.walterwhite.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.thoughtbot.expandablerecyclerview.listeners.OnChildClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.MealRecyclerViewAdapter;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.fragments.managers.UpdateConsoManager;
import fr.mm.walterwhite.fragments.models.MealViewModel;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.utils.DateUtils;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;
import fr.mm.walterwhite.views.NewConsoActivity;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;


public class MealFragment extends Fragment   {

    private RecyclerView listMeals;
    private List<Consommation> consoList;
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    private DatePickerDialog MainDatePicker;
    private TextView MainDateTxtView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ConsoViewModel itemViewModel;
    private MealRecyclerViewAdapter adapter;
    private TextView eatenPoints;
    private TextView weekPoints;
    private TextView remainingPoints;
    private int pointsBudgetPerDay;
    private int pointsBudgetPerWeek;
    private int weekStartDay;
    private UpdateConsoManager updateManager;

    public static MealFragment newInstance() {
        return new MealFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meal_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPreferenceValues();
        configureViewModel();
        handleMainDatePicker();
        handlePointsViews();
        handleButton();
        handleMeals();
    }

    private void getPreferenceValues() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        pointsBudgetPerDay = Integer.parseInt(pref.getString("sp_nb", "-1"));
        pointsBudgetPerWeek = Integer.parseInt(pref.getString("rh_nb", "-1"));
        weekStartDay = Integer.parseInt(pref.getString("day_ref", "1"));
    }

    private void handlePointsViews() {
        eatenPoints=getView().findViewById(R.id.eatenPoints);
        weekPoints=getView().findViewById(R.id.weekPoints);
        remainingPoints=getView().findViewById(R.id.allowedPoints);
    }

    @SuppressLint("ClickableViewAccessibility")
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
                showDatePickerDialog();
            }
        });
//        MainDateTxtView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
//            @Override
//            public void onSwipeRight() {
//                changeDateOnSwipe(-1);
//            }
//            @Override
//            public void onSwipeLeft() {
//                changeDateOnSwipe(1);
//            }
//            @Override
//            public void onSwipeBottom() {
//                showDatePickerDialog();
//            }
//        });
    }

    private void showDatePickerDialog() {
        if (MainDatePicker!=null && MainDatePicker.isShowing()) {MainDatePicker.dismiss();}
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        MainDatePicker = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String chosenDate= DateUtils.formateDate(year, monthOfYear, dayOfMonth);
                        MainDateTxtView.setText(chosenDate);
                        getItems(chosenDate);
                        //updateItemsView();
                    }
                }, year, month, day);
        MainDatePicker.show();
    }

    private void changeDateOnSwipe(int increment){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {cal.setTime(sdf.parse(MainDateTxtView.getText().toString()));}
        catch (ParseException e){}

        cal.add(Calendar.DATE,increment);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String chosenDate= DateUtils.formateDate(year, month, day);

        MainDateTxtView.setText(chosenDate);
        getItems(chosenDate);
    }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.MainAddIngredientButton);
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
        this.listMeals= getView().findViewById(R.id.listMeals);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listMeals.setLayoutManager(layoutManager);
        this.adapter = new MealRecyclerViewAdapter(getActivity(), initModelsList());
        this.adapter.setChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(int flatPos) {
                ExpandableListPosition listPos = adapter.getChildIndexFromPosition(flatPos);
                ExpandableGroup group =  adapter.getChildIndexFromPosition(listPos);
                Log.w("mathilde", "child clicked"+group.getItems().get(listPos.childPos));
                final Consommation conso = ((MealViewModel) group).getItems().get(listPos.childPos);
                handleConsoDialog(conso);
                return true;
            }
        });
        getItems(MainDateTxtView.getText().toString());
        //updateItemsView();
        listMeals.setAdapter(adapter);
    }

    private void handleConsoDialog(Consommation conso){
        new AlertDialog.Builder(getActivity())
                .setTitle("Editer une consommation")
                .setMessage("Souhaitez-vous modifier ou supprimer cette consommation?")
                .setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        updateManager.showPoundValuePickerDialog(conso);

                    }
                })

                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(conso);
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }




    // -------------------
    // DATA
    // -------------------

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ConsoViewModel.class);
        updateManager=new UpdateConsoManager(this);
    }

    // ---

    // ---
/*
    private void getItems(int userId){
        this.itemViewModel.getItems(userId).observe(this, this::updateItemsList);
    }

*/
    private void deleteItem(Consommation item){
        this.itemViewModel.deleteConsommation(item);
    }
/*
    private void updateItem(Consommation item){
        //modif de la donnée (portion)
        // item.setEatenPortion();
        this.itemViewModel.updateConsommation(item);
    }
*/
    private void updateItemsList(List<Consommation> items) {

        consoList = items;
        updateItemsView();
    }

    private void updateItemsView(){
        if (consoList==null){return;}
        this.adapter.updateData(createModelsList(consoList));
        int sum=0;
        for (Consommation item:consoList){
            sum+=item.getEatenPoints();
        }
        getPreferenceValues();
        eatenPoints.setText(String.valueOf(sum));
        remainingPoints.setText(String.valueOf(pointsBudgetPerDay-sum));
        //weekPoints.setText(getRemainingWeekPoints());
    }

    private String getRemainingWeekPoints() {
        LocalDate currentLDate = LocalDate.now();
        LocalDate startLDate = currentLDate.with(TemporalAdjusters.previous(DateUtils.getDayOfWeekCst(weekStartDay)));
        int weeksum = 0;
        for (LocalDate date = startLDate; date.isBefore(currentLDate); date = date.plusDays(1)) {
            getItems(date.toString());
            int daySum=0;
            for (Consommation item:consoList) {
                daySum += item.getEatenPoints();
            }
            if (pointsBudgetPerDay-daySum>4){daySum=4;}
            else {daySum=pointsBudgetPerDay-daySum;}
            weeksum+=daySum;
        }
        return Integer.toString(pointsBudgetPerWeek-weeksum);
    }

    private List<MealViewModel> createModelsList(List<Consommation> items){
        List<MealViewModel> listMModels =new ArrayList<>();
        Map<String, List<Consommation>> result = items.stream()
                .collect(groupingBy(
                        Consommation::getEatenMeal));
        Map<String, Integer> likesPerType = items.stream()
                .collect(groupingBy(Consommation::getEatenMeal, summingInt(Consommation::getRoundEatenPoints)));
        for(String mealSel: Constants.MEALS) {
            MealViewModel meal;
            if(result.containsKey(mealSel)) {
                meal = new MealViewModel(mealSel, result.get(mealSel), likesPerType.get(mealSel) + "");
            }else{
                meal=new MealViewModel(mealSel, new ArrayList<Consommation>(),0+"" );
            }
            listMModels.add(meal);
        }
        return listMModels;
    }

    private List<MealViewModel> initModelsList(){
        List<MealViewModel> listMModels =new ArrayList<>();
        for(String mealSel: Constants.MEALS) {
            MealViewModel meal=new MealViewModel(mealSel, new ArrayList<Consommation>(),0+"" );
            listMModels.add(meal);
        }
        return listMModels;
    }

    private void getItems(String meal){
            this.itemViewModel.getConsommations(MainDateTxtView.getText().toString()).observe(getActivity(), this::updateItemsList);
    }
}