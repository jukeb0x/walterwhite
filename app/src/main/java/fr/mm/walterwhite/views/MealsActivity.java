package fr.mm.walterwhite.views;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.DayRecyclerViewAdapter;
import fr.mm.walterwhite.adaptaters.MealRecyclerViewAdapter;
import fr.mm.walterwhite.fragments.MealFragment;
import fr.mm.walterwhite.fragments.SettingsFragment;
import fr.mm.walterwhite.fragments.WeightFragment;
import fr.mm.walterwhite.models.Consommation;

public class MealsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listConsos;
    private RecyclerView listMeals;
    //private RecyclerView listDays;
    private RecyclerView listDays;
    private final List<Consommation> consoList = new ArrayList<>();
    private ArrayAdapter<Consommation> listViewAdapter;
    private MealRecyclerViewAdapter listViewAdapterMeals;
    //private DayRecyclerViewAdapter listViewAdapterDays;
    private DatePickerDialog MainDatePicker;
    private TextView MainDateTxtView;
    private DayRecyclerViewAdapter listViewAdapterDays;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private final String[] FRAGMENTTAGSARRAY = {"WEIGHTFRAGMENT","SETTINGSFRAGMENT","RECIPESFRAGMENT"};

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        //handleDays();
    /*    Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       configureDrawerLayout();
        configureNavigationView();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame, new MealFragment()).commit();



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
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (isActiveFragmentDifferentFromMain()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame, new MealFragment()).commit();
        } else{
            super.onBackPressed();
        }
    }

    private boolean isActiveFragmentDifferentFromMain(){
        for (String mFragName:FRAGMENTTAGSARRAY) {
            Fragment mFragment = getSupportFragmentManager().findFragmentByTag(mFragName);
            if (null != mFragment && mFragment.isVisible()) {
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame, new SettingsFragment(),"SETTINGSFRAGMENT").commit();
                break;
            case R.id.action_weight:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame, new WeightFragment(),"WEIGHTFRAGMENT").commit();
                break;
            case R.id.action_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame, new MealFragment()).commit();
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


}