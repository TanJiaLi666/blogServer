package com.tanjiali.blogpublicapi.constant;

import lombok.Data;

/**
 * @ClassName 站点信息常量
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 17:43
 * @Version 1.0
 **/
public enum SiteConstant {
    COPYRIGHT("版权", "copyright");
    public static final String AVATAR = "avatar";
    public static final String NAME = "name";
    public static final String GITHUB = "github";
    public static final String TELEGRAM = "telegram";
    public static final String QQ = "qq";
    public static final String BILIBILI = "bilibili";
    public static final String NETEASE = "netease";
    public static final String EMAIL = "email";
    public static final String FAVORITE = "favorite";
    public static final String ROLL_TEXT = "rollText";


    private String key;
    private String value;

    SiteConstant(String key, String value) {
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
