package com.example.guavacache.common;

import com.example.guavacache.enums.CodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 11:31 2018/12/4
 * @Modified by:
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setMessage(CodeEnum.SUCCESS.getMessage());
        result.setData(data);

        return result;
    }

    public static <T> Result<T> failure(int code, String error) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(error);
        return result;
    }

//    public static <T> Result<T> failure(BusinessException exception) {
//        String errorMsg = exception.getMessage();
//        Result<T> result = new Result<T>();
//        result.setCode(exception.getErrorCode());
//        result.setMessage(errorMsg);
//        return result;
//    }

    public static <T> Result<T> failure(CodeEnum errorCodeEnum) {
        Result<T> result = new Result<T>();
        result.setCode(errorCodeEnum.getCode());
        result.setMessage(errorCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> failure() {
        Result<T> result = new Result<T>();
        result.setCode(CodeEnum.FAILURE.getCode());
        result.setMessage(CodeEnum.FAILURE.getMessage());
        return result;
    }
}
