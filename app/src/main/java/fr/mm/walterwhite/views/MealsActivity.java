package fr.mm.walterwhite.views;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.DayRecyclerViewAdapter;
import fr.mm.walterwhite.adaptaters.MealRecyclerViewAdapter;
import fr.mm.walterwhite.dao.Constants;
import fr.mm.walterwhite.dao.DatabaseHelper;
import fr.mm.walterwhite.dao.impl.ConsommationDao;
import fr.mm.walterwhite.models.Consommation;

public class MealsActivity extends AppCompatActivity {

    private ListView listConsos;
    private RecyclerView listMeals;
    private RecyclerView listDays;
    private final List<Consommation> consoList = new ArrayList<Consommation>();
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    private DayRecyclerViewAdapter listViewAdapterDays;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handleDays();
        handleMeals();
        handleConsos();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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



    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void handleMeals() {
        // Get ListView object from xml
        this.listMeals= (RecyclerView) findViewById(R.id.listMeals);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        listMeals.setLayoutManager(layoutManager);
        HashMap<String, Double> mapMeals= new HashMap<String,Double>();
        for(String mealSel:Constants.MEALS) {
            double points=0;
            ConsommationDao db = new ConsommationDao(this);
            // db.createDefaultIfNeed();
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            List<Consommation> list = db.getConsommations(formattedDate, mealSel);
            for(Consommation conso:list){
                points+= conso.getEatenPoints();
            }
            mapMeals.put(mealSel,points);

        }


        this.listViewAdapterMeals = new MealRecyclerViewAdapter(this,  mapMeals);
        //this.listViewAdapterDays.setClickListener(this);


        // Assign adapter to ListView
        this.listMeals.setAdapter(this.listViewAdapterMeals);

        // Register the ListView for Context menu
        registerForContextMenu(this.listMeals);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void handleDays() {
        // Get ListView object from xml
        this.listDays = (RecyclerView) findViewById(R.id.listDays);

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



}