package com.example.amir.simpletodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.simpletodo.model.Todo;

import java.util.List;

/**
 * Created by amir on 3/28/20.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.View_Holder> {

    private List<String> mStatus;
    private Context mContext;

    public StatusAdapter(List<String> mStatus,Context context) {
        this.mStatus = mStatus;
        this.mContext = context;
    }


    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view =  layoutInflater.inflate(R.layout.recycler_view_status_item,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        String status = mStatus.get(position);
        holder.title.setText(status);


    }

    @Override
    public int getItemCount() {
        return mStatus.size();
    }



    public class View_Holder extends RecyclerView.ViewHolder {
        public TextView title;

        public View_Holder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.status_title);

        }
    }
}
