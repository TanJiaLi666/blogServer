package com.tanjiali.blogpublicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/14 16:50
 * @Version 1.0
 **/
@SpringBootApplication
@EnableTransactionManagement
public class Api {
    public static void main(String[] args) {
        SpringApplication.run(Api.class, args);
    }
}
