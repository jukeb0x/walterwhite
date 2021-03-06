package fr.mm.walterwhite.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.fragments.CreateIngredientFragment;
import fr.mm.walterwhite.fragments.ExpressFragment;
import fr.mm.walterwhite.fragments.ScanFragment;
import fr.mm.walterwhite.fragments.SearchNameFragment;
import fr.mm.walterwhite.views.ui.main.SectionsPagerAdapter;

public class NewConsoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_conso);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        sectionsPagerAdapter.addFragment(R.string.tab_express,new ExpressFragment());
        sectionsPagerAdapter.addFragment(R.string.tab_namesearch,new SearchNameFragment());
        sectionsPagerAdapter.addFragment(R.string.tab_create,new CreateIngredientFragment());
        sectionsPagerAdapter.addFragment(R.string.tab_scan,new ScanFragment());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(3).setIcon(R.drawable.ic_scan);

    }

    public void onBackPressed() {
        Intent intent=new Intent(this, MealsActivity.class);
        startActivity(intent);
        this.finish();
    }

}