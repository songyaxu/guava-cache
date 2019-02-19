package com.example.guavacache.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 11:34 2018/12/4
 * @Modified by:
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(0,"请求成功"),
    FAILURE(1,"请求失败"),

    ;
    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;
}
