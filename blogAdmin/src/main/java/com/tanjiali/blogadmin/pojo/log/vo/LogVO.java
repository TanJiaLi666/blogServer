package com.tanjiali.blogadmin.pojo.log.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName 前端输入信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 18:47
 * @Version 1.0
 **/
@Data
public class LogVO {
    private Integer pageNum;
    private Integer pageSize;
    private List<String> date;
}
