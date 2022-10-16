package com.tanjiali.blogpublicapi.constant;

/**
 * @ClassName 用户信息相关常量
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/15 17:14
 * @Version 1.0
 **/
public enum AdminConstant {
    REQUESTHEADER("请求头jwt部分", "Authorization"),
    ADMIN("用户字段","admin"),
    USERAGENT("请求头用户代理部分", "User-Agent");

    private String key;
    private String value;

    private AdminConstant(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
