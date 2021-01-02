package com.yahya.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {

    ArrayList<Lists> lists ;
    public RecyclerViewAdapter(ArrayList<Lists> lists){
        this.lists = lists;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.items,null,false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
  Lists l = lists.get(position);
        holder.tv_name.setText(l.getTitle());
        holder.tv_count.setText(l.getCount());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_count;
        public ListViewHolder(@NonNull View itrmView){
            super(itrmView);
        }

    }
}
