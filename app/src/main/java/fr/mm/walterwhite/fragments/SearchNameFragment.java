package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private SearchViewModel itemViewModel;
    private SearchView searchInput;
    private ListView searchList;
    private ListView searchListIng;
    private ListView searchListRec;
    private SearchListViewAdapter adapter;
    private SearchCommonListViewAdapter<Ingredient> adapterIng;
    private SearchCommonListViewAdapter<Recipe> adapterRec;

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

        searchListIng=getView().findViewById(R.id.searchIng_list);
        this.adapterIng = new SearchCommonListViewAdapter<Ingredient>(getActivity(), initIngList());
        searchListIng.setAdapter(adapterIng);

        searchListRec=getView().findViewById(R.id.searchRec_list);
        this.adapterRec = new SearchCommonListViewAdapter<Recipe>(getActivity(), initRecList());
        searchListRec.setAdapter(adapterRec);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_name, container, false);
    }

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel.class);
        itemViewModel.init();
        itemViewModel.getSearchResponseLiveData().observe(getActivity(), new Observer<Search>() {
            @Override
            public void onChanged(Search volumesResponse) {
                if (volumesResponse != null) {
                    Log.w("Mathilde", "data changed" + volumesResponse.toString());
                     adapter.updateData(volumesResponse.getProducts());
                }
            }
        });
        itemViewModel.getSearchIngredientResponseLiveData().observe(getActivity(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> volumesResponse) {
                if (volumesResponse != null) {
                    Log.w("Mathilde", "data changed" + volumesResponse.toString());
                    adapterIng.updateData(volumesResponse);
                }
            }
        });
        itemViewModel.getSearchRecipeResponseLiveData().observe(getActivity(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> volumesResponse) {
                if (volumesResponse != null) {
                    Log.w("Mathilde", "data changed" + volumesResponse.toString());
                    adapterRec.updateData(volumesResponse);
                }
            }
        });
        // this.itemViewModel.init(USER_ID);
    }

    public void performSearch(String name) {
        if(getActivity() != null) {
            Log.w("Mathilde", "name=" + name);
            itemViewModel.searchProducts(name);
        }else  Log.w("Mathilde", "pas d'activity");
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