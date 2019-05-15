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
import com.example.admin.everywheretrips.bean.YiCollectBean;
import com.example.admin.everywheretrips.util.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollwAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<YiCollectBean.ResultBean.CollectedRoutesBean> mList;

    public FollwAdapter(Context context, List<YiCollectBean.ResultBean.CollectedRoutesBean> list) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder holder = (MyHolder) viewHolder;
        ImageLoader.setImage(mContext,
                mList.get(i).getCardURL(),
                holder.mTripsImg,
                R.drawable.icon_me_kaquan_banmi2);
        holder.mTripsName.setText(mList.get(i).getTitle());
        holder.mTripsAutor.setText(mList.get(i).getIntro());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMyOnClick != null){
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
        @BindView(R.id.tripsfollw_img)
        ImageView mTripsImg;
        @BindView(R.id.tripsfollw_name)
        TextView mTripsName;
        @BindView(R.id.tripsfollw_autor)
        TextView mTripsAutor;

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
