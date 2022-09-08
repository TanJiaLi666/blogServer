package com.tanjiali.blogpublicapi.api;

public enum ResultCode {
    SUCCESS("操作成功",200),
    FAILED("操作失败",500);
    private String msg;
    private int code;
    ResultCode() {
    }
    ResultCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }

}
