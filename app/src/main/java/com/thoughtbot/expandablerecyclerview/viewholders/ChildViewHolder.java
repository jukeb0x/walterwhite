package com.thoughtbot.expandablerecyclerview.viewholders;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtbot.expandablerecyclerview.listeners.OnChildClickListener;


public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

  private OnChildClickListener lsitener;


  public ChildViewHolder(View itemView) {
    super(itemView);
    itemView.setOnLongClickListener(this);
  }




  public void setOnChildClickListener(OnChildClickListener listener) {
    this.lsitener = listener;
  }

  @Override
  public boolean onLongClick(View v) {
    if (lsitener != null) {
      lsitener.onChildClick(getAdapterPosition());
    }return true;
  }
}
