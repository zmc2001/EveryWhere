package com.example.admin.everywheretrips.ui.main.adapter.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.ui.main.activity.HomeParticularsActivity;
import com.example.admin.everywheretrips.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePartiRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public HomeParticularsBean.ResultBean mBean;

    public HomePartiRlvAdapter(Context context, HomeParticularsBean.ResultBean bean) {
        mContext = context;
        mBean = bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_homeparti_one, null);
            holder = new OneHolder(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_homeparti_two, null);
            holder = new TwoHolder(inflate);
        } else if (i == 3) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_homeparti_three, null);
            holder = new ThreeRlvHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_homeparti_four, null);
            holder = new FourHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OneHolder) {
            OneHolder oneHolder = (OneHolder) viewHolder;
            ImageLoader.setImage(mContext,mBean.getRoute().getCardURL()
                    ,oneHolder.mItemHomepartiOneImg
                    ,R.drawable.icon_me_kaquan_banmi2);
        } else if (viewHolder instanceof TwoHolder) {
            TwoHolder twoHolder = (TwoHolder) viewHolder;
            ImageLoader.setCircleImage(mContext,mBean.getBanmi().getPhoto()
                    ,twoHolder.mItemHomepartiTwoImg
                    ,R.drawable.icon_me_kaquan_banmi2);
            twoHolder.mItemHomepartiTwoName.setText(mBean.getBanmi().getName());
            twoHolder.mItemHomepartiTwoCount.setText(mBean.getBanmi().getOccupation());
            twoHolder.mItemHomepartiTwoAddress.setText(mBean.getBanmi().getLocation());
            twoHolder.mItemHomepartiTwoCountt.setText(mBean.getBanmi().getIntroduction());
        } else if (viewHolder instanceof ThreeRlvHolder) {
            ThreeRlvHolder threeHolder = (ThreeRlvHolder) viewHolder;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            threeHolder.mItemHomepartiThreeRlv.setLayoutManager(linearLayoutManager);
            HomePartiThreeRlvAdapter adapter = new HomePartiThreeRlvAdapter(mContext, mBean.getReviews());
            threeHolder.mItemHomepartiThreeRlv.setAdapter(adapter);
        } else if (viewHolder instanceof FourHolder) {
            final FourHolder fourHolder = (FourHolder) viewHolder;
            fourHolder.mItemHomepartiFourTv1.setText("查看所有"+mBean.getReviewsCount()+"条评论");

            final int cid = mBean.getRoute().getId();

            if (mBean.getRoute().isIsCollected()){
                fourHolder.mItemHomepartiFourImg.setImageResource(R.mipmap.collect_highlight);
                fourHolder.mItemHomepartiFourTv.setText("已收藏");
            }else {
                fourHolder.mItemHomepartiFourImg.setImageResource(R.mipmap.collect_default);
                fourHolder.mItemHomepartiFourTv.setText("收藏");
            }
            fourHolder.mItemHomepartiFourImg.setTag(mBean.getRoute().isIsCollected());
            final HomeParticularsActivity activity = new HomeParticularsActivity();
            fourHolder.mItemHomepartiFourLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean tag = (boolean) fourHolder.mItemHomepartiFourImg.getTag();
                    if (!tag){
                        activity.initCollect(cid);
                        fourHolder.mItemHomepartiFourImg.setImageResource(R.mipmap.collect_highlight);
                        fourHolder.mItemHomepartiFourTv.setText("已收藏");
                        fourHolder.mItemHomepartiFourImg.setTag(true);
                    }else {
                        activity.initUnLike(cid);
                        fourHolder.mItemHomepartiFourImg.setImageResource(R.mipmap.collect_default);
                        fourHolder.mItemHomepartiFourTv.setText("收藏");
                        fourHolder.mItemHomepartiFourImg.setTag(false);
                    }
                }
            });

            fourHolder.mItemHomepartiFourTv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFourTvOnClick!=null){
                        mFourTvOnClick.onClick(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        }else if (position == 2){
            return 3;
        }else {
            return 4;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class OneHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_homeparti_one_img)
        ImageView mItemHomepartiOneImg;

        public OneHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_homeparti_two_img)
        ImageView mItemHomepartiTwoImg;
        @BindView(R.id.item_homeparti_two_name)
        TextView mItemHomepartiTwoName;
        @BindView(R.id.item_homeparti_two_count)
        TextView mItemHomepartiTwoCount;
        @BindView(R.id.item_homeparti_two_address)
        TextView mItemHomepartiTwoAddress;
        @BindView(R.id.item_homeparti_two_countt)
        TextView mItemHomepartiTwoCountt;

        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ThreeRlvHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_homeparti_three_rlv)
        RecyclerView mItemHomepartiThreeRlv;

        public ThreeRlvHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FourHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_homeparti_four_img)
        ImageView mItemHomepartiFourImg;
        @BindView(R.id.item_homeparti_four_tv)
        TextView mItemHomepartiFourTv;
        @BindView(R.id.item_homeparti_four_tv1)
        TextView mItemHomepartiFourTv1;
        @BindView(R.id.item_homeparti_four_ll)
        LinearLayout mItemHomepartiFourLl;

        public FourHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FourTvOnClick{
        void onClick(int position);
    }

    private FourTvOnClick mFourTvOnClick;

    public void setFourTvOnClick(FourTvOnClick fourTvOnClick) {
        mFourTvOnClick = fourTvOnClick;
    }
}
