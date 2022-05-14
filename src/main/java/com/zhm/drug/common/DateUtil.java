package com.zhm.drug.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/11
 **/
public class DateUtil {
    /**
     * 把日期格式转换为年月日
     */
    public static String dateConvert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
