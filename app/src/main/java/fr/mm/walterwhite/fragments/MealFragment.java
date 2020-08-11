package fr.mm.walterwhite.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
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
    private final List<Consommation> consoList = new ArrayList<>();
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    private DatePickerDialog MainDatePicker;
    private TextView MainDateTxtView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ConsoViewModel itemViewModel;
    private MealRecyclerViewAdapter adapter;
    private UpdateConsoManager updateManager;

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
        configureViewModel();
        handleMainDatePicker();
        handleMeals();
        handleButton();

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
                                String chosenDate= DateUtils.formateDate(year, monthOfYear, dayOfMonth);
                                MainDateTxtView.setText(chosenDate);
                                getItems(chosenDate);
                            }
                        }, year, month, day);
                MainDatePicker.show();
            }
        });
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
   /* protected void handleMeals() {
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



    }*/

       protected void handleMeals() {
        // Get ListView object from xml
        this.listMeals= getView().findViewById(R.id.listMeals);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        listMeals.setLayoutManager(layoutManager);



        //instantiate your adapter with the list of genres
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

    // -------------------
    // UI
    // -------------------
    /*

    private void configureRecyclerView(){
        this.adapter = new ItemAdapter(this);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(recyclerView, R.layout.activity_todo_list_item)
                .setOnItemClickListener((recyclerView1, position, v) -> this.updateItem(this.adapter.getItem(position)));
    }

    private void updateHeader(User user){
        this.profileText.setText(user.getUsername());
        Glide.with(this).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(this.profileImage);
    }
*/
    private void updateItemsList(List<Consommation> items){
        Log.w("Mathilde", "items size=" + items.size());
     //   this.adapter = new MealRecyclerViewAdapter(getActivity(), createModelsList(items));
     //   listMeals.setAdapter(adapter);
        this.adapter.updateData(createModelsList(items));

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
                Log.w("Mathilde", "meal=" + mealSel + " nb points " + result.get(mealSel));
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
        Log.w("Mathilde", "date=" + meal);
        this.itemViewModel.getConsommations(MainDateTxtView.getText().toString()).observe(getActivity(), this::updateItemsList);
    }










}