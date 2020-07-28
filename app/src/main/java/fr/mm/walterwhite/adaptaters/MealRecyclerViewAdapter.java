package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.mm.walterwhite.R;

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealRecyclerViewAdapter.MealRecyclerViewHolder> {

    private List<String> mMeals;
    private List<Double> mPoints;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MealRecyclerViewAdapter(Context context, HashMap<String, Double> meals) {
        this.mInflater = LayoutInflater.from(context);
        this.mMeals = new ArrayList<String>();
        this.mPoints = new ArrayList<Double>();
        for(Map.Entry<String, Double> mealIt : meals.entrySet()){
            this.mMeals.add(mealIt.getKey());
            this.mPoints.add(mealIt.getValue());
        }

    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public MealRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclermeal_item, parent, false);
        return new MealRecyclerViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull MealRecyclerViewAdapter.MealRecyclerViewHolder holder, int position) {
        String animal = mMeals.get(position);
        holder.myTextView.setText(animal);
        Double points = mPoints.get(position);
        holder.myPointTextView.setText(points+ "");
    }




    // total number of rows
    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class MealRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;
        TextView myPointTextView;

        MealRecyclerViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.meal);
            myPointTextView = itemView.findViewById(R.id.mealPoints);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mMeals.get(id);
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

