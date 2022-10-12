package com.tanjiali.blogadmin.pojo.admin.dto;

import lombok.Data;


/**
 * @ClassName 访问数据渲染信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/12 18:08
 * @Version 1.0
 **/
@Data
public class VisitDTO {
    private String date;
    private Integer pv;
    private Integer uv;
}
