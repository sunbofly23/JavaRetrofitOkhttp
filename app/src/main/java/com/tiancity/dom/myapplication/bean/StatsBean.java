package com.tiancity.dom.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class StatsBean implements Parcelable {

    private int code;
    private String msg;
    private long ts;
    private Object data;

    protected StatsBean(Parcel in) {
        code = in.readInt();
        msg = in.readString();
        ts = in.readLong();
    }

    public static final Creator<StatsBean> CREATOR = new Creator<StatsBean>() {
        @Override
        public StatsBean createFromParcel(Parcel in) {
            return new StatsBean(in);
        }

        @Override
        public StatsBean[] newArray(int size) {
            return new StatsBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
        dest.writeLong(ts);
    }
}
