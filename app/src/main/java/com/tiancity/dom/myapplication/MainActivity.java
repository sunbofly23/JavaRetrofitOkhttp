package com.tiancity.dom.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tiancity.dom.myapplication.bean.BaseBean;
import com.tiancity.dom.myapplication.bean.CommentsBean;
import com.tiancity.dom.myapplication.bean.PhotoBean;
import com.tiancity.dom.myapplication.bean.StatsBean;
import com.tiancity.dom.myapplication.bean.TodoBean;
import com.tiancity.dom.myapplication.bean.UserBean;
import com.tiancity.dom.myapplication.utils.RequestUtils;

import java.io.File;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComment = findViewById(R.id.btn_comment);
        btnComment.setOnClickListener(v -> getComments());

        int userRes = getResources().getIdentifier("btn_user", "id", getPackageName());
        Button btnUser = findViewById(userRes);
        btnUser.setOnClickListener(v -> getUser());

        Button btnTodo = findViewById(R.id.btn_todo);
        btnTodo.setOnClickListener(v -> postTodo());

        ImageView ivImage = findViewById(R.id.iv_image);

        Button btnPhoto = findViewById(R.id.btn_photo);
        btnPhoto.setOnClickListener(v-> getPhoto(ivImage));

    }

    private void getPhoto(ImageView imageView) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载中...");
        dialog.show();
        BaseModel<List<PhotoBean>> model = new BaseModel<>();
        Call<List<PhotoBean>> call = model.service.getPhoto(3);
        model.callEnqueue(call, new BaseListener<List<PhotoBean>>() {
            @Override
            public void onResponse(List<PhotoBean> bean) {
                dialog.dismiss();
                Glide.with(MainActivity.this).load(bean.get(0).getUrl())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);
            }

            @Override
            public void onFail(String e) {
                dialog.dismiss();
                Log.d(TAG, "getPhoto onFail:" + e);
            }
        });
    }


    private void getComments() {
        BaseModel<List<CommentsBean>> model = new BaseModel<>();
        Call<List<CommentsBean>> call = model.service.getComments(2);
        model.callEnqueue(call, new BaseListener<List<CommentsBean>>() {
            @Override
            public void onResponse(List<CommentsBean> bean) {

            }

            @Override
            public void onFail(String e) {

                Log.d(TAG, "getComments onFail:" + e);
            }
        });
    }


    private void getUser() {
        BaseModel<List<UserBean>> model = new BaseModel<>();
        Call<List<UserBean>> call = model.service.getUser(3);
        model.callEnqueue(call, new BaseListener<List<UserBean>>() {
            @Override
            public void onResponse(List<UserBean> userBean) {
                Log.d(TAG, "getUser onResponse:" + userBean.get(0).getUsername());
            }

            @Override
            public void onFail(String e) {
                Log.d(TAG, "getUser onFail:" + e);
            }
        });
    }

    private void postTodo() {
        BaseModel<TodoBean> model = new BaseModel<>();
        Call<TodoBean> call = model.service.postTodo();
        model.callEnqueue(call, new BaseListener<TodoBean>() {
            @Override
            public void onResponse(TodoBean todoBean) {
                Log.d(TAG, "getTodo onResponse:" + todoBean.getId());

            }

            @Override
            public void onFail(String e) {
                Log.d(TAG, "getTodo onFail:" + e);
            }
        });
    }


    private void LauncherGame() {
        BaseModel<StatsBean> model = new BaseModel<>();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("clientId", "8Y4QFM5O5XNNWACHW1F5U581YT75LP6Y")
                .add("idfa", "3DEA635F-B24F-434B-833F-4ED028FEAEEF")
                .add("idfv", "94B854B0-AC0B-4F7F-8F0D-E32824317E83")
                .add("androidId", "9774d56d682e549c")
                .add("imei", "868024023063168")
                .add("oaid", "");
        FormBody formBody = RequestUtils.joinEnd(builder);
        Call<StatsBean> call = model.service.postLaunchGame("112", "52000", formBody);
        model.callEnqueue(call, new BaseListener<StatsBean>() {
            @Override
            public void onResponse(StatsBean statsBean) {
                Log.d(TAG, statsBean.getMsg());
            }

            @Override
            public void onFail(String e) {
                Log.d(TAG, "postLaunchGame onFail e:" + e);
            }
        });
    }

    //上传截屏文件去服务器
    private void requestUpdateImage(File file) {
        BaseModel<BaseBean> model = new BaseModel<>();
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        retrofit2.Call<BaseBean> call = model.service.uploadRunningImage(body, "session", "studentid");
        model.callEnqueue(call, new BaseListener<BaseBean>() {
            @Override
            public void onResponse(BaseBean baseBean) {

            }

            @Override
            public void onFail(String e) {

            }
        });
    }

}
