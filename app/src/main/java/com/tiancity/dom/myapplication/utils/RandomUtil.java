package com.tiancity.dom.myapplication.utils;


import java.util.Random;

public class RandomUtil {
    public static String genEightToSixteenStr() {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        int numbers = random.nextInt(8) + 8; // 如果想要7-16位: random.nextInt(9) + 7;
        for (int i = 0; i < numbers; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                //取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (choice + random.nextInt(26)));
            } else {
                // 数字
                val.append(random.nextInt(10));
            }
        }
        return  val.toString().toLowerCase();
    }
}
