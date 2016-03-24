package com.erikle2.headphonetester.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.model.entities.HeadPhoneTest;

import org.w3c.dom.Text;

/**
 * Created by Erik on 24/02/2016.
 */
public class ResultAdapter extends RecyclerView.Adapter {
    public HeadPhoneTest data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
    public ResultAdapter(HeadPhoneTest data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView itemValue = (TextView)holder.itemView.findViewById(R.id.tvResultItemValue);
        itemValue.setText("" + data.getResult()[position+1]);

        TextView itemName = (TextView)holder.itemView.findViewById(R.id.tvResultItemName);
        itemName.setText("" + data.getName()[position+1]);
    }
    @Override
    public int getItemCount() {
        return data.getName().length -1;
    }
}
