package com.tanjiali.blogadmin.pojo.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName 访问数据渲染信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/12 18:58
 * @Version 1.0
 **/
@Data
public class ViDTO {
    private List<String> date;
    private List<Integer> pv;
    private List<Integer> uv;
}
