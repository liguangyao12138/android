package com.example.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liguangyao
 * @date 2020-12-21
 * @description： 获取时间的工具类
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
