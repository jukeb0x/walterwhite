package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.WeightRecyclerViewAdapter;
import fr.mm.walterwhite.dao.impl.WeightDao;
import fr.mm.walterwhite.fragments.models.WeightViewModel;
import fr.mm.walterwhite.models.Weight;

public class WeightFragment extends Fragment {

    private WeightViewModel mViewModel;
    private RecyclerView gridView;

    public static WeightFragment newInstance() {
        return new WeightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weight_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WeightViewModel.class);
        handleGrid();
        handleButton();
        // TODO: Use the ViewModel
    }


    private void handleGrid(){
        gridView = getActivity().findViewById(R.id.listweights);

        WeightDao db = new WeightDao(getActivity());
            List<Weight> list = db.getWeights();
            List<WeightViewModel> listCModels =new  ArrayList<>();
            for(Weight conso:list){
                WeightViewModel cm=new WeightViewModel(conso.getWeightDate(),conso.getWeight()+"kg");
                listCModels.add(cm);

            }


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        WeightRecyclerViewAdapter adapter = new WeightRecyclerViewAdapter(getActivity(), listCModels);
        gridView.setLayoutManager(layoutManager);
        gridView.setAdapter(adapter);

        }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.weight_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onBackPressed() {

    }







}