package com.tiancity.dom.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentsBean implements Parcelable {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    protected CommentsBean(Parcel in) {
        postId = in.readInt();
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }

    public static final Creator<CommentsBean> CREATOR = new Creator<CommentsBean>() {
        @Override
        public CommentsBean createFromParcel(Parcel in) {
            return new CommentsBean(in);
        }

        @Override
        public CommentsBean[] newArray(int size) {
            return new CommentsBean[size];
        }
    };

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(body);
    }
}
