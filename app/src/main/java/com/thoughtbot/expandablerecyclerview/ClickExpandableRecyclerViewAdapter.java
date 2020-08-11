package com.thoughtbot.expandablerecyclerview;

import android.util.Log;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnChildClickListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public abstract class ClickExpandableRecyclerViewAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder>
    extends ExpandableRecyclerViewAdapter<GVH, CVH> implements ExpandCollapseListener, OnGroupClickListener, OnChildClickListener {

  private static final String EXPAND_STATE_MAP = "expandable_recyclerview_adapter_expand_state_map";

  private OnChildClickListener childClickListener;


  public ClickExpandableRecyclerViewAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public CVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
    CVH CCVH = onCreateChildViewHolder(parent, viewType);
    CCVH.setOnChildClickListener(this);
    return CCVH;
  }

  @Override
  public void onBindChildViewHolder(CVH holder, int flatPosition, ExpandableGroup group,
                                    int childIndex) {
    ExpandableListPosition listPosition = expandableList.getUnflattenedPosition(flatPosition);
    //holder.onBindViewHolder(flatPosition, childController.isChildChecked(listPosition));
    onBindChildViewHolder(holder, flatPosition, (ExpandableGroup) group, childIndex);
  }

  public OnChildClickListener getChildClickListener() {
    return childClickListener;
  }

  public void setChildClickListener(OnChildClickListener childClickListener) {
    this.childClickListener = childClickListener;
  }

  public ExpandableListPosition getChildIndexFromPosition(int flatPos) {
    return expandableList.getUnflattenedPosition(flatPos);
  }

  public ExpandableGroup getChildIndexFromPosition(ExpandableListPosition listPos) {
    return expandableList.groups.get(listPos.groupPos);
  }

  @Override
  public boolean onChildClick(int flatPos) {
    Log.w("mathilde","click adapter"+flatPos);
    if (childClickListener != null) {
      childClickListener.onChildClick(flatPos);
    }
    return true;
  }
}


