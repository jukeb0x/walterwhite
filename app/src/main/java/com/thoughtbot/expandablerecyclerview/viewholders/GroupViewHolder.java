package com.thoughtbot.expandablerecyclerview.viewholders;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;


public abstract class GroupViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

  private OnGroupClickListener listener;

  public GroupViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (listener != null) {
      listener.onGroupClick(getAdapterPosition());
    }
  }

  public void setOnGroupClickListener(OnGroupClickListener listener) {
    this.listener = listener;
  }

  public void expand() {}

  public void collapse() {}
}
