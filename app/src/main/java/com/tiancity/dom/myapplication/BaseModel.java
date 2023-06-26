package com.tiancity.dom.myapplication;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseModel <T>{

    public final String baseUrl = "http://jsonplaceholder.typicode.com/";
//    public final String baseUrl = "https://tsisdktest.tiancity.com/";

    public BaseService service;
    //创建Retrofit对象
    public BaseModel() {
        //1构造okhttp3,日志过滤
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            builder.addInterceptor(interceptor);
        }
        //2构造retrofit,构造请求接口
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        service =  retrofit.create(BaseService.class);
    }


    //3构建Callback
    public void callEnqueue(Call<T> call,BaseListener<T> listener){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if(response.isSuccessful()){
                    listener.onResponse(response.body());
                }else{
                    listener.onFail(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }


}
