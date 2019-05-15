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
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeListRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Main_Home_ListBean.ResultBean.BannersBean> mBannersBeans;
    private ArrayList<Main_Home_ListBean.ResultBean.RoutesBean> mRoutesBeans;

    public MainHomeListRlvAdapter(Context context, ArrayList<Main_Home_ListBean.ResultBean.BannersBean> bannersBeans,
                                  ArrayList<Main_Home_ListBean.ResultBean.RoutesBean> routesBeans) {
        mContext = context;
        mBannersBeans = bannersBeans;
        mRoutesBeans = routesBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main_home_banner, null);
            holder = new BannerHolder(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main_home_list, null);
            holder = new ListHolder(inflate);
        } else if (i == 3) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main_home_one_list, null);
            holder = new ListOneHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            BannerHolder bannerHolder = (BannerHolder) viewHolder;
            bannerHolder.mMainBanner.setImages(mBannersBeans);
            bannerHolder.mMainBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            bannerHolder.mMainBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Main_Home_ListBean.ResultBean.BannersBean path1 = (Main_Home_ListBean.ResultBean.BannersBean) path;
                    Glide.with(mContext).load(path1.getImageURL()).into(imageView);
//                    com.example.admin.everywheretrips.util.ImageLoader.setImage(mContext,
//                            path1.getImageURL(),imageView,R.drawable.zhanweitu_xianlu_jingdian);
                }
            }).start();
        } else if (itemViewType == 2) {
            ListHolder listHolder = (ListHolder) viewHolder;
            listHolder.mYinzuo.setText(mRoutesBeans.get(i).getTitle());
            listHolder.mDongjing.setText(mRoutesBeans.get(i).getCity());
            listHolder.mShopping.setText(mRoutesBeans.get(i).getIntro());
            listHolder.mGoumai.setText(mRoutesBeans.get(i).getPurchasedTimes() + "人购买");
            Glide.with(mContext).load(mRoutesBeans.get(i).getCardURL())
                    .into(listHolder.mImg);
//            com.example.admin.everywheretrips.util.ImageLoader.setImage(mContext
//            ,mRoutesBeans.get(i).getCardURL(),listHolder.mImg,R.drawable.zhanweitu_xianlu_qipao_big);
            listHolder.mPrice.setText("￥" + mRoutesBeans.get(i).getPrice());

            listHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mMyOnClick != null) {
                        mMyOnClick.onClick(i);
                    }
                }
            });
        }else if (itemViewType == 3){
            ListOneHolder listOneHolder = (ListOneHolder) viewHolder;
            Glide.with(mContext).load(mRoutesBeans.get(i).getCardURL())
                    .into(listOneHolder.mItemHomeOneListImg);
//            com.example.admin.everywheretrips.util.ImageLoader.setImage(mContext
//                    ,mRoutesBeans.get(i).getCardURL(),listOneHolder.mItemHomeOneListImg
//                    ,R.drawable.zhanweitu_xianlu_jingdian);zhanweitu_xianlu_jingdian
            listOneHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mMyTwoOnClick!=null){
                        mMyTwoOnClick.onClick(i);
                    }
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (mRoutesBeans.get(position).getType().equals("bundle")) {
            return 3;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return mRoutesBeans.size();
    }


    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_banner)
        Banner mMainBanner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.yinzuo)
        TextView mYinzuo;
        @BindView(R.id.weizhi)
        ImageView mWeizhi;
        @BindView(R.id.dongjing)
        TextView mDongjing;
        @BindView(R.id.shopping)
        TextView mShopping;
        @BindView(R.id.goumai)
        TextView mGoumai;
        @BindView(R.id.img)
        ImageView mImg;
        @BindView(R.id.price)
        Button mPrice;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MyOnClick {
        void onClick(int position);
    }

    private MyOnClick mMyOnClick;

    public void setMyOnClick(MyOnClick myOnClick) {
        mMyOnClick = myOnClick;
    }

    class ListOneHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_home_one_list_img)
        ImageView mItemHomeOneListImg;
        public ListOneHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MyTwoOnClick {
        void onClick(int position);
    }

    private MyTwoOnClick mMyTwoOnClick;

    public void setMyTwoOnClick(MyTwoOnClick myTwoOnClick) {
        mMyTwoOnClick = myTwoOnClick;
    }
}
