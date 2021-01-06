package com.yahya.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.yahya.todoapp.R;
import com.yahya.todoapp.classes.TaskClass;

import java.util.ArrayList;

public class AllTasksAdapter  extends RecyclerView.Adapter<AllTasksAdapter.ViewHolder>{

    private ArrayList<TaskClass> data;
    private AllListsAdapter.OnItemClickListener mListener;

    public AllTasksAdapter(ArrayList<TaskClass> data){
        this.data = data;
    }


    //onItemClick
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(AllListsAdapter.OnItemClickListener listener){
        mListener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.taskTitle.setText(data.get(position).titleOfNote);
        holder.taskContext.setText(data.get(position).contextOfNote);
        holder.taskDate.setText(TaskClass.longToDate(data.get(position).dateOfNote));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void filterList(ArrayList<TaskClass> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView taskTitle,taskContext,taskDate;
        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle=itemView.findViewById(R.id.note_title);
            taskContext=itemView.findViewById(R.id.note_context);
            taskDate=itemView.findViewById(R.id.note_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
