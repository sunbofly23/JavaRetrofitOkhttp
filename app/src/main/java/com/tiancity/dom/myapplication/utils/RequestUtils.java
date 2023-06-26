package com.tiancity.dom.myapplication.utils;

import android.text.TextUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.FormBody;

public class RequestUtils {

    //签名规则:值排序拼接key,md5转大写
    public static String getSign(FormBody formBody){
        String key = "******************************";
        SortedMap<String,String> sortedMap = new TreeMap<>();
        for(int i = 0 ;i<formBody.size();i++){
            if(formBody.encodedName(i).equals("sign"))
                continue;
            if(TextUtils.isEmpty(formBody.encodedValue(i)))
                continue;
            sortedMap.put(formBody.encodedName(i),formBody.encodedValue(i));
        }
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String> entry : sortedMap.entrySet()){
            System.out.println(entry.getValue());
            builder.append(entry.getValue()).append("&");
        }
        String result =  builder.append(MD5(key).toUpperCase()).toString();
        return MD5(result).toUpperCase() ;
    }

    //md5加密
    private static String MD5(String key) {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(key.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if(messageDigest!=null){
            byte[] byteArray = messageDigest.digest();
            StringBuilder md5StrBuff = new StringBuilder();
            for (byte b : byteArray) {
                if (Integer.toHexString(0xFF & b).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & b));
            }
            return md5StrBuff.toString();
        }
        return "";
    }

    public static FormBody joinEnd(FormBody.Builder builder) {
        builder.add("ts", String.valueOf(System.currentTimeMillis()))
                .add("nonce", RandomUtil.genEightToSixteenStr())
                .add("sign", getSign(builder.build()));
        return builder.build();
    }


}
