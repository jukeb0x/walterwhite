package fr.mm.walterwhite.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ClickExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.ExpandCollapseController;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import fr.mm.walterwhite.R;
import fr.mm.walterwhite.fragments.models.MealViewModel;
import fr.mm.walterwhite.models.Consommation;
import fr.mm.walterwhite.services.Calculator;

public class MealRecyclerViewAdapter extends ClickExpandableRecyclerViewAdapter<MealRecyclerViewAdapter.MealRecyclerViewHolder, MealRecyclerViewAdapter.ConsoRecyclerViewHolder> {


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
        final Consommation artist = ((MealViewModel) group).getItems().get(childIndex);
        holder.getConsoName().setText(artist.getEatenName());
        holder.getAmount().setText(artist.getEatenPortion()+"gr");
        holder.getEatenPoints().setText(Calculator.computeRoundPoints(artist.getEatenPoints())+"");
    }

    @Override
    public void onBindGroupViewHolder(MealRecyclerViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setInfos(group);
        holder.setArrowDown(isGroupExpanded(group));


    }

    public void updateData(List<? extends ExpandableGroup> groups){
        this.expandableList = new ExpandableList(groups);
        this.expandCollapseController = new ExpandCollapseController(expandableList, this);
        this.notifyDataSetChanged();
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

        public void onBind(Consommation artist) {
            consoName.setText(artist.getEatenName());
            eatenPoints.setText(Calculator.computeRoundPoints(artist.getEatenPoints())+"");
            amount.setText(artist.getEatenPortion()+"gr");
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



