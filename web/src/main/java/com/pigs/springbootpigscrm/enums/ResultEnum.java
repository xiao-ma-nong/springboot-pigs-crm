package com.pigs.springbootpigscrm.enums;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/18 15:05
 *
 * 返回统一格式
 */
public enum ResultEnum {

    UNKONW_ERROR(-1,"未知错误"),

    SUCCESS(200,"成功"),

    PRIMARY_SCHOOL(100,"失败");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
