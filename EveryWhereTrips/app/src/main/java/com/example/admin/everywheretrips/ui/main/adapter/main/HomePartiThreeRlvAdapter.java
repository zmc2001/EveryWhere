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
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.util.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePartiThreeRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HomeParticularsBean.ResultBean.ReviewsBean> mList;

    public HomePartiThreeRlvAdapter(Context context, List<HomeParticularsBean.ResultBean.ReviewsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_homepar_three_rlv, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder threeHolder = (MyHolder) viewHolder;
        /*Glide.with(mContext).load(mList.get(i).getUserPhoto()).apply(requestOptions)
                .placeholder(R.drawable.icon_me_kaquan_banmi2)
                .into(threeHolder.mItemHomepartiThreeImg);*/
        ImageLoader.setCircleImage(mContext,mList.get(i).getUserPhoto()
                ,threeHolder.mItemHomepartiThreeImg
                ,R.drawable.icon_me_kaquan_banmi2);
        threeHolder.mItemHomepartiThreeName.setText(mList.get(i).getUserName());
        threeHolder.mItemHomepartiThreeTime.setText(mList.get(i).getCreatedAt());
        threeHolder.mItemHomepartiThreeCount.setText(mList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_homeparti_three_img)
        ImageView mItemHomepartiThreeImg;
        @BindView(R.id.item_homeparti_three_name)
        TextView mItemHomepartiThreeName;
        @BindView(R.id.item_homeparti_three_time)
        TextView mItemHomepartiThreeTime;
        @BindView(R.id.item_homeparti_three_count)
        TextView mItemHomepartiThreeCount;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
