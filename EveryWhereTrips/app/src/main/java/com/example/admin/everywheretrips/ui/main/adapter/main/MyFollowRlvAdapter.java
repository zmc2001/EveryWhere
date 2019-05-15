package com.example.admin.everywheretrips.ui.main.adapter.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.bean.MyFollowBean;
import com.example.admin.everywheretrips.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFollowRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<MyFollowBean.ResultBean.BanmiBean> mList;

    public MyFollowRlvAdapter(Context context, ArrayList<MyFollowBean.ResultBean.BanmiBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_follw, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder holder = (MyHolder) viewHolder;
        ImageLoader.setImage(mContext,
                mList.get(i).getPhoto(),
                holder.mTripsfollwImg,
                R.drawable.icon_me_kaquan_banmi2);
        holder.mTripsfollwName.setText(mList.get(i).getName());
        holder.mTripsfollwAutor.setText(mList.get(i).getOccupation());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tripsfollw_img)
        ImageView mTripsfollwImg;
        @BindView(R.id.tripsfollw_name)
        TextView mTripsfollwName;
        @BindView(R.id.tripsfollw_autor)
        TextView mTripsfollwAutor;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
