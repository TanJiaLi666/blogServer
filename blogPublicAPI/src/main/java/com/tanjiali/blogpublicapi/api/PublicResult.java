package com.tanjiali.blogpublicapi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 接口统一结果对象
 */
@Api("结果集")
@Data
public class PublicResult<T> {
    private int code;
    private String msg;
    private T data;

    protected PublicResult() {
    }

    protected PublicResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功对象
     * @param data
     * @param <T>
     * @return
     */
    @ApiOperation("成功对象方法")
    public static<T> PublicResult<T> success(T data) {
        return success(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(), data);
    }
    @ApiOperation("成功对象方法")
    public static<T> PublicResult<T> success(String msg) {
        return success(ResultCode.SUCCESS.getCode(),msg, null);
    }
    @ApiOperation("成功对象方法")
    public static<T> PublicResult<T> success(T data,String msg) {
        return success(ResultCode.SUCCESS.getCode(),msg, data);
    }
    @ApiOperation("成功对象方法")
    public static<T> PublicResult<T> success(int code,String msg,T data) {
        return new PublicResult<T>(code,msg, data);
    }

    /**
     * 操作失败对象
     * @param data
     * @param <T>
     * @return
     */
    @ApiOperation("失败对象方法")
    public static<T> PublicResult<T> failed(int code,String msg,T data) {
        return new PublicResult<T>(code,msg, data);
    }
    @ApiOperation("失败对象方法")
    public static<T> PublicResult<T> failed(String msg) {
        return failed(ResultCode.FAILED.getCode(),msg, null);
    }
    @ApiOperation("失败对象方法")
    public static<T> PublicResult<T> failed(T data,String msg) {
        return failed(ResultCode.FAILED.getCode(),msg, data);
    }
    @ApiOperation("失败对象方法")
    public static<T> PublicResult<T> failed(T data) {
        return failed(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMsg(), data);
    }
}
