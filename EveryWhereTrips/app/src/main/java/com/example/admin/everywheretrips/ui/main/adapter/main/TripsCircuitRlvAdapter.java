package com.example.admin.everywheretrips.ui.main.adapter.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.bean.TripsCircuitBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsCircuitRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<TripsCircuitBean.ResultBean.RoutesBean> mList;

    public TripsCircuitRlvAdapter(Context context, ArrayList<TripsCircuitBean.ResultBean.RoutesBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_trips_circuit, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder listHolder = (MyHolder) viewHolder;
        listHolder.mTripsCircuitYinzuo.setText(mList.get(i).getTitle());
        listHolder.mTripsCircuitAddress.setText(mList.get(i).getCity());
        listHolder.mTripsCircuitShopping.setText(mList.get(i).getIntro());
        listHolder.mTripsCircuitGoumai.setText(mList.get(i).getPriceInCents() + "人购买");
        Glide.with(mContext).load(mList.get(i).getCardURL())
                .into(listHolder.mTripsCircuitImg);
//            com.example.admin.everywheretrips.util.ImageLoader.setImage(mContext
//            ,mRoutesBeans.get(i).getCardURL(),listHolder.mImg,R.drawable.zhanweitu_xianlu_qipao_big);
        listHolder.mTripsCircuitPrice.setText("￥" + mList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trips_circuit_img)
        ImageView mTripsCircuitImg;
        @BindView(R.id.trips_circuit_yinzuo)
        TextView mTripsCircuitYinzuo;
        @BindView(R.id.weizhi)
        ImageView mWeizhi;
        @BindView(R.id.trips_circuit_address)
        TextView mTripsCircuitAddress;
        @BindView(R.id.trips_circuit_price)
        Button mTripsCircuitPrice;
        @BindView(R.id.trips_circuit_shopping)
        TextView mTripsCircuitShopping;
        @BindView(R.id.trips_circuit_goumai)
        TextView mTripsCircuitGoumai;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
