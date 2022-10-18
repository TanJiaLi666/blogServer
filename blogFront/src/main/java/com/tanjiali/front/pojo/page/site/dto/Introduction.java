package com.tanjiali.front.pojo.page.site.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 18:14
 * @Version 1.0
 **/
@Data
public class Introduction {
    private String avatar;
    private String name;
    private String github;
    private String telegram;
    private String qq;
    private String bilibili;
    private String netease;
    private String email;

    private List<String> rollText = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();
}
