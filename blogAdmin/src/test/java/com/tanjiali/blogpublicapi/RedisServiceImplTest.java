package com.tanjiali.blogpublicapi;

import com.tanjiali.blogpublicapi.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/14 16:45
 * @Version 1.0
 **/
@SpringBootTest
class RedisServiceImplTest {
    @Autowired
    private RedisService service;
    @Test
    public void test() {
        service.setString("tanjiali", "hello ppp");
    }
    @Test
    public void test1() {
        String tanjiali = service.getString("tanjiali");
        System.out.println(tanjiali);
    }
}