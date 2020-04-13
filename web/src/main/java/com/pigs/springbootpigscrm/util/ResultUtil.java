package com.pigs.springbootpigscrm.util;

import com.pigs.springbootpigscrm.entity.ResultFormat;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/17 14:53
 * 统一返回工具类
 */
public class ResultUtil {


    public static ResultFormat success(Object object) {
        ResultFormat result =new ResultFormat();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResultFormat success() {
        return success(null);
    }

    public static ResultFormat error(Integer code, String msg) {
        ResultFormat result =new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }



}
