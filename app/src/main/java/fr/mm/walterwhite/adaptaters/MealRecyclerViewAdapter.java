package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.fragments.models.ConsommationViewModel;
import fr.mm.walterwhite.fragments.models.MealViewModel;

public class MealRecyclerViewAdapter extends ExpandableRecyclerViewAdapter<MealRecyclerViewAdapter.MealRecyclerViewHolder, MealRecyclerViewAdapter.ConsoRecyclerViewHolder> {


    private LayoutInflater mInflater;

    public MealRecyclerViewAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MealRecyclerViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclermeal_item, parent, false);
        return new MealRecyclerViewHolder(view);
    }

    @Override
    public ConsoRecyclerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclermeal_conso_item, parent, false);
        return new ConsoRecyclerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ConsoRecyclerViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final ConsommationViewModel artist = ((MealViewModel) group).getItems().get(childIndex);
        holder.getConsoName().setText(artist.getName());
        holder.getAmount().setText(artist.getAmount());
        holder.getEatenPoints().setText(artist.getPoints());
    }

    @Override
    public void onBindGroupViewHolder(MealRecyclerViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setInfos(group);
        holder.setArrowDown(isGroupExpanded(group));


    }


    public class ConsoRecyclerViewHolder extends ChildViewHolder {

        public TextView getConsoName() {
            return consoName;
        }

        public void setConsoName(TextView consoName) {
            this.consoName = consoName;
        }

        public TextView consoName;

        public TextView getEatenPoints() {
            return eatenPoints;
        }

        public void setEatenPoints(TextView eatenPoints) {
            this.eatenPoints = eatenPoints;
        }

        public TextView getAmount() {
            return amount;
        }

        public void setAmount(TextView amount) {
            this.amount = amount;
        }

        public TextView eatenPoints;
        public TextView amount;

        public ConsoRecyclerViewHolder(View itemView) {
            super(itemView);

            consoName = itemView.findViewById(R.id.consoNameItem);
            eatenPoints = itemView.findViewById(R.id.consoPointItem);
            amount = itemView.findViewById(R.id.consoAmountItem);
        }

        public void onBind(ConsommationViewModel artist) {
            consoName.setText(artist.getName());
            eatenPoints.setText(artist.getPoints());
            amount.setText(artist.getAmount());
        }
    }


    public class MealRecyclerViewHolder extends GroupViewHolder {

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getPoints() {
            return points;
        }

        public void setPoints(TextView points) {
            this.points = points;
        }

        private TextView name;
        private TextView points;
        private ImageView arrow;

        public MealRecyclerViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.mealItem);
            points =  itemView.findViewById(R.id.mealPointItem);
            arrow =  itemView.findViewById(R.id.conso_arrow_expand);
        }

        public void setInfos(ExpandableGroup group) {
            name.setText(((MealViewModel)group).getName());
            points.setText(((MealViewModel)group).getPoints());
        }


        public void setArrowDown(boolean expanded) {
           if (expanded) {
               arrow.setImageResource(R.drawable.ic_arrow_down);
           }else{
               arrow.setImageResource(R.drawable.ic_arrow_right);
           }
        }
    }
}



