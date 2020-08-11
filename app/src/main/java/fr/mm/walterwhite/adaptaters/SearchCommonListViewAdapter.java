package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.mm.walterwhite.R;

public class SearchCommonListViewAdapter<T> extends BaseAdapter {

    private List<T> search;
    private LayoutInflater mInflater;
    private Context context;


    // data is passed into the constructor
    public SearchCommonListViewAdapter(Context context, List<T> search) {
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
        if(position>=0 && search!=null && search.size()>0) {
            return search.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.listsimplesearch_item, parent, false);
        TextView name=convertView.findViewById(R.id.searchsimple_name);
        name.setText(search.get(position).toString());
        return convertView;
    }

    public void updateData(List<T> search){
        this.search=search;
        this.notifyDataSetChanged();
    }
}

