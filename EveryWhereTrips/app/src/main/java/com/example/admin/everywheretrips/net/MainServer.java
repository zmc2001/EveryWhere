package com.example.admin.everywheretrips.net;

import com.example.admin.everywheretrips.bean.AllCommentsBean;
import com.example.admin.everywheretrips.bean.CircuitBean;
import com.example.admin.everywheretrips.bean.HomeParticularsBean;
import com.example.admin.everywheretrips.bean.MainFollwBean;
import com.example.admin.everywheretrips.bean.MainTripsListBean;
import com.example.admin.everywheretrips.bean.Main_Home_ListBean;
import com.example.admin.everywheretrips.bean.MyInfoBean;
import com.example.admin.everywheretrips.bean.TripsCircuitBean;
import com.example.admin.everywheretrips.bean.TripsParticBean;
import com.example.admin.everywheretrips.bean.YiCollectBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainServer {
    String homesurl = "http://api.banmi.com/api/3.0/";
    @GET("content/routesbundles?")
    Observable<Main_Home_ListBean> getData(@Query("page") int page, @HeaderMap Map<String,String> map);

    @GET("banmi?")
    Observable<MainTripsListBean> getTrips(@Query("page") int page, @HeaderMap Map<String,String> map);

    @POST("content/routes/{routeId}/like")
    @FormUrlEncoded
    Observable<MainFollwBean> getMainFollw(@Path("routeId") int routeId, @HeaderMap Map<String,String> map);

    //路线详情
    @GET("content/routes/{cid}")
    Observable<HomeParticularsBean> getHomeParticulars(@Path("cid") int cid, @HeaderMap Map<String,String> map);

    //https://api.banmi.com/api/3.0/banmi/57?page=1
    @GET("account/collectedRoutes")
    Observable<YiCollectBean> getYiCollect(@Header("banmi-app-token") String token);

    //线路收藏
    @POST("content/routes/{cid}/like")
    Observable<CircuitBean> getCircuit(@Path("cid") int cid,@HeaderMap Map<String,String> map);

    //api/3.0/content/routes/{routeId}/dislike  取消线路收藏
    @POST("content/routes/{cid}/dislike")
    Observable<CircuitBean> getUnlike(@Path("cid") int cid,@HeaderMap Map<String,String> map);

    @GET("banmi/{cid}?")
    Observable<TripsParticBean> getTripsPartic(@Path("cid") int cid,
                                               @Query("page") int page,
                                               @HeaderMap Map<String,String> map);

    @GET("banmi/{cid}/routes?")
    Observable<TripsCircuitBean> getTripsCircuit(@Path("cid") int cid,
                                                 @Query("page") int page,
                                                 @HeaderMap Map<String,String> map);

    //http://api.banmi.com/api/3.0/content/routes/196/reviews?page=1
    @GET("content/routes/{cid}/reviews?")
    Observable<AllCommentsBean> getAllComments(@Path("cid") int cid,
                                               @Query("page") int page,
                                               @HeaderMap Map<String,String> map);

    //修改个人信息
    @POST("account/updateInfo")
    @FormUrlEncoded
    Observable<ResponseBody> upDateInfo(@Field("photo") String photo,
                                        @Field("userName") String userName,
                                        @Field("gender") String gender,
                                        @Field("description") String description,
                                        @HeaderMap Map<String,String> map);

    //用户信息
    @GET("account/info")
    Observable<MyInfoBean> getInfo(@HeaderMap Map<String,String> map);

}
