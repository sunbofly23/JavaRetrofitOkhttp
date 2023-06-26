package com.tiancity.dom.myapplication;
import com.tiancity.dom.myapplication.bean.BaseBean;
import com.tiancity.dom.myapplication.bean.CommentsBean;
import com.tiancity.dom.myapplication.bean.PhotoBean;
import com.tiancity.dom.myapplication.bean.StatsBean;
import com.tiancity.dom.myapplication.bean.TodoBean;
import com.tiancity.dom.myapplication.bean.UserBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BaseService {
    //路径参数
    @GET("posts/{postId}/comments")
    Call<List<CommentsBean>> getComments(
            @Path("postId") int postId
    );

    @GET("photos")
    Call<List<PhotoBean>> getPhoto(
            @Query("id") int id
    );

    //请求参数
    @GET("users")
    Call<List<UserBean>> getUser(
            @Query("id") int id);

    //Post请求
    @POST("todos")
    Call<TodoBean>postTodo();

    //包含请求头的POST请求
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("api/sdk/stat/v1/launch/{gameId}/{channelId}")
    Call<StatsBean>postLaunchGame(
          @Path("gameId") String gameId,
          @Path("channelId") String channelId,
          @Body RequestBody responseBody
    );

    //包含json的POST请求
    //上传跑步数据
    @FormUrlEncoded
    @POST("Record/uploadRunningRecord")
    Call<BaseBean> uploadRunningRecord(@Field("Json") String route);

    //上传跑步地图截屏
    @Multipart
    @POST("Record/uploadRunningImage")
    Call<BaseBean> uploadRunningImage(@Part MultipartBody.Part file,
                                      @Query("sessionId") String sessionId,
                                      @Query("studentId") String studentId);

}
