package com.tiancity.dom.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoBean implements Parcelable {
    private int id;

    protected TodoBean(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<TodoBean> CREATOR = new Creator<TodoBean>() {
        @Override
        public TodoBean createFromParcel(Parcel in) {
            return new TodoBean(in);
        }

        @Override
        public TodoBean[] newArray(int size) {
            return new TodoBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
