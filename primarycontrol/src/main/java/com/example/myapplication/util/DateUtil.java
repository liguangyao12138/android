package com.example.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liguangyao on 2020/12/21.
 */
public class DateUtil {
    public static String getNowDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

}
