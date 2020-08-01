package fr.mm.walterwhite.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.DayRecyclerViewAdapter;
import fr.mm.walterwhite.adaptaters.MealRecyclerViewAdapter;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.dao.impl.ConsommationDao;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.views.models.ConsommationViewModel;
import fr.mm.walterwhite.views.models.MealViewModel;

public class MealsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listConsos;
    private RecyclerView listMeals;
    private RecyclerView listDays;
    private final List<Consommation> consoList = new ArrayList<>();
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    private DayRecyclerViewAdapter listViewAdapterDays;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
    /*    Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       configureDrawerLayout();
        configureNavigationView();
        handleMeals();
        handleConsos();
        handleButton();


    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
       Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    protected void handleConsos() {
        // Get ListView object from xml
       /* this.listConsos= (ListView) findViewById(R.id.listConsos);

        ConsommationDao db = new ConsommationDao(this);
        // db.createDefaultIfNeed();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        List<Consommation> list = db.getConsommations(formattedDate, "dej");
        this.consoList.addAll(list);


        // Define a new Adapter
        // 1 - Context
        // 2 - Layout for the row
        // 3 - ID of the TextView to which the data is written
        // 4 - the List of data

        this.listViewAdapter = new ArrayAdapter<Consommation>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.consoList);

        // Assign adapter to ListView
        this.listConsos.setAdapter(this.listViewAdapter);

        // Register the ListView for Context menu
        registerForContextMenu(this.listConsos);*/
    }

    protected void handleButton() {
        Button myButton =  findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MealsActivity.this, NewConsoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



    protected void handleMeals() {
        // Get ListView object from xml
        this.listMeals= findViewById(R.id.listMeals);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        listMeals.setLayoutManager(layoutManager);



       // this.listViewAdapterMeals = new MealRecyclerViewAdapter(this,  mapMeals);
        //this.listViewAdapterDays.setClickListener(this);


        // Assign adapter to ListView
       // this.listMeals.setAdapter(this.listViewAdapterMeals);

        // Register the ListView for Context menu
        //registerForContextMenu(this.listMeals);

        List<MealViewModel> listMModels =new  ArrayList<>();
        for(String mealSel:Constants.MEALS) {
            double points=0;
            ConsommationDao db = new ConsommationDao(this);
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
            MealViewModel mm=new  MealViewModel(mealSel, listCModels, points+"");

            listMModels.add(mm);

        }


        //instantiate your adapter with the list of genres
        MealRecyclerViewAdapter adapter = new MealRecyclerViewAdapter(this, listMModels);
        listMeals.setLayoutManager(layoutManager);
        listMeals.setAdapter(adapter);



    }


    protected void handleDays() {
        // Get ListView object from xml
        this.listDays =  findViewById(R.id.listDays);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        listDays.setLayoutManager(layoutManager);

        // Define a new Adapter
        // 1 - Context
        // 2 - Layout for the row
        // 3 - ID of the TextView to which the data is written
        // 4 - the List of data



        this.listViewAdapterDays = new DayRecyclerViewAdapter(this, Constants.DAYS);
        //this.listViewAdapterDays.setClickListener(this);


        // Assign adapter to ListView
        this.listDays.setAdapter(this.listViewAdapterDays);

        // Register the ListView for Context menu
        registerForContextMenu(this.listDays);
    }






    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


}