package com.tiancity.dom.myapplication;

public interface BaseListener<T> {
    void onResponse(T t);
    void onFail(String e);
}
