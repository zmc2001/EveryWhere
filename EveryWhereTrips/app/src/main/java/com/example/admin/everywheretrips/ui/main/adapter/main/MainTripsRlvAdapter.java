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
import com.example.admin.everywheretrips.bean.MainTripsListBean;
import com.example.admin.everywheretrips.ui.main.fragment.mainfragment.TripsFragment;
import com.example.admin.everywheretrips.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTripsRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<MainTripsListBean.ResultBean.BanmiBean> mList;

    public MainTripsRlvAdapter(Context context, ArrayList<MainTripsListBean.ResultBean.BanmiBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main_trips, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final MyHolder holder = (MyHolder) viewHolder;
        final MainTripsListBean.ResultBean.BanmiBean bean = mList.get(i);
        ImageLoader.setImage(mContext,bean.getPhoto(),holder.mTripsImg,R.drawable.icon_me_kaquan_banmi2);
        holder.mTripsName.setText(bean.getName());
        holder.mGuanzhu.setText(bean.getFollowing()+"人关注");
        holder.mTripsAddress.setText(bean.getLocation());
        holder.mTripsAutor.setText(bean.getOccupation());

        if (mList.get(i).isIsFollowed()){
            holder.mTripsScolloc.setImageResource(R.mipmap.follow);
        }else {
            holder.mTripsScolloc.setImageResource(R.mipmap.follow_unselected);
        }

        holder.mTripsScolloc.setTag(mList.get(i).isIsFollowed());
        final int cid = mList.get(i).getId();
        final TripsFragment tripsFragment = new TripsFragment();
        holder.mTripsScolloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = (boolean) holder.mTripsScolloc.getTag();
                if (!flag){
                    tripsFragment.initfollow(cid);
                    holder.mTripsScolloc.setImageResource(R.mipmap.follow);
                    holder.mTripsScolloc.setTag(true);
                }else {
                    tripsFragment.unfollow(cid);
                    holder.mTripsScolloc.setImageResource(R.mipmap.follow_unselected);
                    holder.mTripsScolloc.setTag(false);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMyOnClick!=null){
                    mMyOnClick.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.trips_img)
        ImageView mTripsImg;
        @BindView(R.id.trips_name)
        TextView mTripsName;
        @BindView(R.id.guanzhu)
        TextView mGuanzhu;
        @BindView(R.id.trips_address)
        TextView mTripsAddress;
        @BindView(R.id.trips_autor)
        TextView mTripsAutor;
        @BindView(R.id.trips_scolloc)
        ImageView mTripsScolloc;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MyOnClick{
        void onClick(int position);
    }

    private MyOnClick mMyOnClick;

    public void setMyOnClick(MyOnClick myOnClick) {
        mMyOnClick = myOnClick;
    }
}
