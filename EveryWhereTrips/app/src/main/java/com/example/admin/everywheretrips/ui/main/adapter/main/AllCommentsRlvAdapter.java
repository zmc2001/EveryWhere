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
import com.example.admin.everywheretrips.bean.AllCommentsBean;
import com.example.admin.everywheretrips.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCommentsRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<AllCommentsBean.ResultBean.ReviewsBean> mList;

    public AllCommentsRlvAdapter(Context context, ArrayList<AllCommentsBean.ResultBean.ReviewsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_allcomments_list, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder holder = (MyHolder) viewHolder;
        ImageLoader.setCircleImage(mContext,mList.get(i).getUserPhoto(),holder.mItemAllcommentsPhoto,
                R.mipmap.ic_launcher);
        holder.mItemAllcommentsName.setText(mList.get(i).getUserName());
        holder.mItemAllcommentsTime.setText(mList.get(i).getCreatedAt());
        holder.mItemAllcommentsContent.setText(mList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_allcomments_photo)
        ImageView mItemAllcommentsPhoto;
        @BindView(R.id.item_allcomments_name)
        TextView mItemAllcommentsName;
        @BindView(R.id.item_allcomments_time)
        TextView mItemAllcommentsTime;
        @BindView(R.id.item_allcomments_spack)
        TextView mItemAllcommentsSpack;
        @BindView(R.id.item_allcomments_ping)
        ImageView mItemAllcommentsPing;
        @BindView(R.id.item_allcomments_zan)
        TextView mItemAllcommentsZan;
        @BindView(R.id.item_allcomments_zanimg)
        ImageView mItemAllcommentsZanimg;
        @BindView(R.id.item_allcomments_content)
        TextView mItemAllcommentsContent;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
