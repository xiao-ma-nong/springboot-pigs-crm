package com.pigs.springbootpigscrm.exception;

import com.pigs.springbootpigscrm.enums.ResultEnum;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/18 15:07
 *
 * 系统异常
 */
public class PigsException extends RuntimeException{
    private Integer code;

    public PigsException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
