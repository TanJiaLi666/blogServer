package com.tanjiali.blogadmin.pojo.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName 访问者城市
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/12 19:18
 * @Version 1.0
 **/
@Data
@TableName("city_visitor")
public class CityVisitor {
    private String city;//城市名称
    private Integer uv;//独立访客数量
}
