package com.yahya.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yahya.todoapp.R;
import com.yahya.todoapp.classes.TaskClass;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    public ArrayList<TaskClass> data;
    private AllListsAdapter.OnItemClickListener mListener;
    public TasksAdapter(ArrayList<TaskClass> data){
        this.data = data;
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
        holder.noteTitle.setText(data.get(position).titleOfNote);
        holder.noteContext.setText(data.get(position).contextOfNote);
        holder.noteDate.setText(TaskClass.longToDate(data.get(position).dateOfNote));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView noteTitle,noteContext,noteDate;
        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle=itemView.findViewById(R.id.note_title);
            noteContext=itemView.findViewById(R.id.note_context);
            noteDate=itemView.findViewById(R.id.note_date);
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
