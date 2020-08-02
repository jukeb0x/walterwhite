package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.models.Weight;

public class WeightRecyclerViewAdapter extends RecyclerView.Adapter<WeightRecyclerViewAdapter.WeightRecyclerViewHolder> {

private List<Weight> mPounds;
private LayoutInflater mInflater;
private ItemClickListener mClickListener;

// data is passed into the constructor
public WeightRecyclerViewAdapter(Context context, List<Weight> mPounds) {
        this.mInflater = LayoutInflater.from(context);
        this.mPounds = mPounds;
        }

// inflates the row layout from xml when needed
@Override
@NonNull
public WeightRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tableweight_item, parent, false);
        return new WeightRecyclerViewHolder(view);
        }

// binds the data to the view and textview in each row
@Override
public void onBindViewHolder(@NonNull WeightRecyclerViewHolder holder, int position) {
    Weight animal = mPounds.get(position);
        holder.myDateTextView.setText(animal.getWeightDate());
        holder.myPoundsTextView.setText(animal.getWeight()+"kg");
        }

// total number of rows
@Override
public int getItemCount() {
        return mPounds.size();
        }


public void updateData(List<Weight> mPounds){
    this.mPounds = mPounds;
    this.notifyDataSetChanged();
}

// stores and recycles views as they are scrolled off screen
public class WeightRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView myDateTextView;
    TextView myPoundsTextView;

    WeightRecyclerViewHolder(View itemView) {
        super(itemView);
        myDateTextView =  itemView.findViewById(R.id.weightDateItem);
        myPoundsTextView =  itemView.findViewById(R.id.weightPoundsItem);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
    }
}



    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

// parent activity will implement this method to respond to click events
public interface ItemClickListener {
    void onItemClick(View view, int position);
}
}