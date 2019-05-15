package com.example.admin.everywheretrips.net;

import com.example.admin.everywheretrips.bean.BanmiFollowBean;
import com.example.admin.everywheretrips.bean.MyFollowBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BanmiServer {
    String banmiurl = "https://api.banmi.com/api/3.0/";

    @POST("banmi/{cid}/follow")
    Observable<BanmiFollowBean> getfollow(@Path("cid") int cid, @HeaderMap Map<String,String> map);

    @POST("banmi/{cid}/unfollow")
    Observable<BanmiFollowBean> getunfollow(@Path("cid") int cid, @HeaderMap Map<String,String> map);

    @GET("account/followedBanmi?")
    Observable<MyFollowBean> getMyfollow(@Query("page") int page,@HeaderMap Map<String,String> map);
}
