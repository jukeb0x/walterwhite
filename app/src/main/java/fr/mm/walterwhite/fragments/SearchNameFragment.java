package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.SearchCommonListViewAdapter;
import fr.mm.walterwhite.adaptaters.SearchListViewAdapter;
import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.api.models.Search;
import fr.mm.walterwhite.fragments.managers.AddConsoFromIngredientManager;
import fr.mm.walterwhite.fragments.managers.AddConsoFromProductManager;
import fr.mm.walterwhite.fragments.managers.AddConsoFromRecipeManager;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Ingredient;
import fr.mm.walterwhite.models.Recipe;
import fr.mm.walterwhite.viewmodels.SearchViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SearchNameFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView searchInput;
    private ListView searchList;
    private ListView searchListIng;
    private ListView searchListRec;
    private SearchListViewAdapter adapter;
    private SearchCommonListViewAdapter<Ingredient> adapterIng;
    private SearchCommonListViewAdapter<Recipe> adapterRec;
    private AddConsoFromIngredientManager manager;
    private AddConsoFromProductManager productManager;
    private AddConsoFromRecipeManager recipeManager;
    private SearchViewModel itemViewModel;

    public SearchNameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureViewModel();
        searchInput=getView().findViewById(R.id.search_area);
        searchInput.setOnQueryTextListener(this);

        searchList=getView().findViewById(R.id.search_list);
        this.adapter = new SearchListViewAdapter(getActivity(), initModelsList());
        searchList.setAdapter(adapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.w("Mathilde","click event");
                Product entry = (Product) arg0.getItemAtPosition(position);
                Log.w("Mathilde","selected entry"+entry);
                productManager.showPoundValuePickerDialog(entry);
            }
        });

        searchListIng=getView().findViewById(R.id.searchIng_list);
        this.adapterIng = new SearchCommonListViewAdapter<Ingredient>(getActivity(), initIngList());
        searchListIng.setAdapter(adapterIng);
        searchListIng.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Ingredient entry = (Ingredient) arg0.getItemAtPosition(position);
                manager.showPoundValuePickerDialog(entry);
            }
        });

        searchListRec=getView().findViewById(R.id.searchRec_list);
        this.adapterRec = new SearchCommonListViewAdapter<Recipe>(getActivity(), initRecList());
        searchListRec.setAdapter(adapterRec);
        searchListRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Recipe entry = (Recipe) arg0.getItemAtPosition(position);
                recipeManager.showPoundValuePickerDialog(entry);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_name, container, false);
    }

    protected void configureViewModel(){
        manager=new AddConsoFromIngredientManager(this);
        productManager=new AddConsoFromProductManager(this);
        recipeManager=new AddConsoFromRecipeManager(this);
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel.class);
        itemViewModel.init();
        itemViewModel.getSearchResponseLiveData().observe(getActivity(), new Observer<Search>() {
            @Override
            public void onChanged(Search volumesResponse) {
                if (volumesResponse != null) {
                     adapter.updateData(volumesResponse.getProducts());
                }else{
                    adapter.updateData(initModelsList());
            }
            }
        });

        // this.itemViewModel.init(USER_ID);
    }

    public void performSearch(String name) {
        if(getActivity() != null) {
            itemViewModel.searchProducts(name);
            itemViewModel.getSearchIngredientResponseLiveData(name).observe(getActivity(), new Observer<List<Ingredient>>() {
                @Override
                public void onChanged(List<Ingredient> volumesResponse) {
                    if (volumesResponse != null) {
                        adapterIng.updateData(volumesResponse);
                    }else{
                        adapterIng.updateData(initIngList());
                    }
                }
            });
            itemViewModel.getSearchRecipeResponseLiveData(name).observe(getActivity(), new Observer<List<Recipe>>() {
                @Override
                public void onChanged(List<Recipe> volumesResponse) {
                    if (volumesResponse != null) {
                        adapterRec.updateData(volumesResponse);
                    }else{
                        adapterRec.updateData(initRecList());
                    }
                }
            });
        }
    }


    public List<Product> initModelsList() {
        List<Product> listMModels =new ArrayList<>();
        return listMModels;
    }
    public List<Ingredient> initIngList() {
        List<Ingredient> listMModels =new ArrayList<>();
        return listMModels;
    }
    public List<Recipe> initRecList() {
        List<Recipe> listMModels =new ArrayList<>();
        return listMModels;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        performSearch(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}