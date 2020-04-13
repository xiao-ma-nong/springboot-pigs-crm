package com.pigs.springbootpigscrm.handle;


import com.pigs.springbootpigscrm.entity.ResultFormat;
import com.pigs.springbootpigscrm.exception.PigsException;
import com.pigs.springbootpigscrm.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/2/14 0:04
 */
@ControllerAdvice
public class ExceptionHandle{

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultFormat handle(Exception e) {
        if (e instanceof PigsException) {
            PigsException pigsException = (PigsException) e;
            return ResultUtil.error(pigsException.getCode(), pigsException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
