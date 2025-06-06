package com.hzx.lesson.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 * @author 15203
 * @since 2020/3/26 16:03
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 编码：1成功，0和其它数字为失败
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
