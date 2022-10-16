package com.tanjiali.blogpublicapi.service;

/**
 * @ClassName Redis数据库操作
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/14 15:09
 * @Version 1.0
 **/
public interface RedisService {
    //基础操作String类型
    String getString(String key);
    void setString(String key, String value);


}
