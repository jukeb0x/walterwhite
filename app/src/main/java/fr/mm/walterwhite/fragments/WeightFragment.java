package fr.mm.walterwhite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.adaptaters.WeightRecyclerViewAdapter;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.models.Weight;
import fr.mm.walterwhite.utils.DateUtils;
import fr.mm.walterwhite.viewmodels.WeightViewModel;

public class WeightFragment extends Fragment {

    private RecyclerView gridView;
    private WeightViewModel itemViewModel;
    private  WeightRecyclerViewAdapter adapter;
    private EditText poundsInput;
    private TextView poundsDate;

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
        poundsInput=getView().findViewById(R.id.pounds_input);
        poundsDate=getView().findViewById(R.id.WeightDateTextView);
        configureViewModel();
        handleGrid();
        handleMainDatePicker();
        handleButton();
    }

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(WeightViewModel.class);
        // this.itemViewModel.init(USER_ID);
    }


    private void handleGrid(){
        gridView = getActivity().findViewById(R.id.listweights);
        List<Weight> listCModels =new  ArrayList<>();
        /*WeightDao db = new WeightDao(getActivity());
            List<Weight> list = db.getWeights();

            for(Weight conso:list){
                WeightViewModel cm=new WeightViewModel(conso.getWeightDate(),conso.getWeight()+"kg");
                listCModels.add(cm);

            }*/


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        this.adapter = new WeightRecyclerViewAdapter(getActivity(), listCModels);
        gridView.setLayoutManager(layoutManager);
        gridView.setAdapter(adapter);
        getItems();

        }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.weight_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != poundsInput.getText())
                createItem();
                else {
                    Toast toast = Toast.makeText(getActivity(), "Veuillez saisir un poids", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleMainDatePicker() {

        android.icu.text.SimpleDateFormat dateFormat = new android.icu.text.SimpleDateFormat("dd/MM/yyyy");
        String strTodayDate = dateFormat.format(Calendar.getInstance().getTime());

        poundsDate=getView().findViewById(R.id.WeightDateTextView);
        poundsDate.setText(strTodayDate);
        poundsDate.setOnClickListener(new View.OnClickListener() {

            private DatePickerDialog MainDatePicker;

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
                                poundsDate.setText(chosenDate);
                            }
                        }, year, month, day);
                MainDatePicker.show();
            }
        });
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
    private void updateItemsList(List<Weight> items){
        Log.w("Mathilde", "items size=" + items.size());
        //   this.adapter = new MealRecyclerViewAdapter(getActivity(), createModelsList(items));
        //   listMeals.setAdapter(adapter);
        this.adapter.updateData(items);

    }

    private void createItem(){
        Weight item = new Weight(1,this.poundsDate.getText().toString(), Double.parseDouble(this.poundsInput.getText().toString()));
        this.itemViewModel.createWeight(item);
    }




    private void getItems(){
       // Log.w("Mathilde", "date=" + meal);
        this.itemViewModel.getWeights().observe(getActivity(), this::updateItemsList);
    }







}