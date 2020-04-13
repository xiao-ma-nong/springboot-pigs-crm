package com.pigs.springbootpigscrm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/6 18:25
 * @effect  时间 工具类
 */
public class TimeUtitl {

    public static String dateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeFormat = simpleDateFormat.format(date);
        return timeFormat;
    }

}
