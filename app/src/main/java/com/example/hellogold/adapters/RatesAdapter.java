package com.example.hellogold.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hellogold.R;

import java.util.ArrayList;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ViewHolder> {

    private ArrayList<String> timeStamp = new ArrayList<>();
    private ArrayList<String> buy = new ArrayList<>();
    private ArrayList<String> sell = new ArrayList<>();
    private ArrayList<String> spotPrice = new ArrayList<>();

    private Context mContext;

    public RatesAdapter(ArrayList<String> timeStamp, ArrayList<String> buy, ArrayList<String> sell, ArrayList<String> spotPrice, Context mContext) {
        this.timeStamp = timeStamp;
        this.buy = buy;
        this.sell = sell;
        this.spotPrice = spotPrice;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rates, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.timeStamp.setText(timeStamp.get(i));
        viewHolder.buy.setText(buy.get(i));
        viewHolder.sell.setText(sell.get(i));
        viewHolder.spotPrice.setText(spotPrice.get(i));
    }

    @Override
    public int getItemCount() {
        return timeStamp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeStamp;
        TextView buy;
        TextView sell;
        TextView spotPrice;
        ConstraintLayout childLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.tv_time_stamp_value);
            buy = itemView.findViewById(R.id.tv_buy_value);
            sell = itemView.findViewById(R.id.tv_sell_value);
            spotPrice = itemView.findViewById(R.id.tv_spot_price_value);
        }
    }
}
