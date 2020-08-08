package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.api.models.Product;

public class SearchListViewAdapter extends BaseAdapter {

    private List<Product> search;
    private LayoutInflater mInflater;
    private Context context;


    // data is passed into the constructor
    public SearchListViewAdapter(Context context, List<Product> search) {
        this.mInflater = LayoutInflater.from(context);
        this.search = search;
        this.context=context;

    }


    @Override
    public int getCount() {
        return search.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.listsearch_item, parent, false);
        TextView brand=convertView.findViewById(R.id.search_brand);
        TextView name=convertView.findViewById(R.id.search_name);
        Log.w("Mathilde", "update ui name=" + search.get(position).getProductName());
        name.setText(search.get(position).getProductName());
        brand.setText(search.get(position).getBrands());

        return convertView;
    }

    public void updateData(List<Product> search){
        this.search=search;
        this.notifyDataSetChanged();
    }
}

