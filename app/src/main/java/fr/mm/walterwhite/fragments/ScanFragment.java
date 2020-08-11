package fr.mm.walterwhite.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.api.models.Product;
import fr.mm.walterwhite.fragments.managers.AddConsoFromProductManager;
import fr.mm.walterwhite.injection.Injection;
import fr.mm.walterwhite.injection.ViewModelFactory;
import fr.mm.walterwhite.viewmodels.ConsoViewModel;
import fr.mm.walterwhite.viewmodels.ProductViewModel;

public class ScanFragment extends Fragment {

    private AddConsoFromProductManager manager;
    private ProductViewModel itemViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handleButton();



    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setPrompt("Scan a barcode").setCameraId(0).setBeepEnabled(false).setBarcodeImageEnabled(true).initiateScan();
    }




    protected void configureViewModel(){
        manager=new AddConsoFromProductManager(this);
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        itemViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductViewModel.class);
        itemViewModel.init();
        itemViewModel.getProductResponseLiveData().observe(getActivity(), new Observer<Product>() {
            @Override
            public void onChanged(Product volumesResponse) {
                if (volumesResponse != null) {
                   String toast = "Data changed"+volumesResponse.toString();
                    manager.displayToast(toast);
                    Log.w("Mathilde", "data changed" + volumesResponse.toString());
                    manager.showPoundValuePickerDialog(volumesResponse);
                   // adapter.setResults(volumesResponse.getItems());
                }
            }
        });

        manager.consoViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ConsoViewModel.class);
        // this.itemViewModel.init(USER_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String toast = null;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
                manager.displayToast(toast);


            }

            // At this point we may or may not have a reference to the activity
            //displayToast(toast);
            performSearch(result.getContents());
        }
    }
    public void performSearch(String code) {
        if(getActivity() != null) {
            Log.w("Mathilde", "code=" + code);
            itemViewModel.getProductByBarcode(code);
        }else  Log.w("Mathilde", "pas d'activity");
    }

    protected void handleButton() {
        Button myButton =  getView().findViewById(R.id.launchScanPoundsButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getActivity(), "Veuillez saisir un poids", Toast.LENGTH_LONG);
                //toast.show();
                scanFromFragment();
            }
        });
    }










}

