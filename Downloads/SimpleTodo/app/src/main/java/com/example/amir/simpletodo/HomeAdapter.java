package com.example.amir.simpletodo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.simpletodo.model.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by amir on 3/14/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private List<Task> data;
    private Context context;

    public HomeAdapter(List<Task> data,Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {

        Task task = data.get(position);
        holder.textView.setText(task.getDescription());
        

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void addItem(Task task) {
        data.add(task);
        notifyItemInserted(0);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.task_title);
            cardView = itemView.findViewById(R.id.card);

        }
    }
}
