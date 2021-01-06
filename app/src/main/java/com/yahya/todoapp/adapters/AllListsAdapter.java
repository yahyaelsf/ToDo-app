package com.yahya.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.yahya.todoapp.R;
import com.yahya.todoapp.classes.ListClass;
import com.yahya.todoapp.classes.TaskClass;

import java.util.ArrayList;


import static com.yahya.todoapp.Lists.currentNotebookId;
import static com.yahya.todoapp.ShowTask.notes;

public class AllListsAdapter extends RecyclerView.Adapter<AllListsAdapter.ViewHolder>  {

    private ArrayList<ListClass> data;


    private OnItemClickListener mListener;
    public AllListsAdapter(ArrayList<ListClass> data){
        this.data = data;
    }

    //onItemClick
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtt, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bookName.setText(data.get(position).name);



        for(int ii=0;ii < getItemCount();ii++){
            String id11=data.get(position).id;
            currentNotebookId=id11;

            int a=0;
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(position).ListsId.equals(currentNotebookId)) {
                    a=a+1;
                }
            }

            holder.noOfTask.setText(a +" Task");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public   void filterList(ArrayList<ListClass> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView bookName;
        public TextView noOfTask;

        public ViewHolder(View itemView) {
            super(itemView);

            bookName=itemView.findViewById(R.id.name_List);
            noOfTask=itemView.findViewById(R.id.numberOfTask);
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
