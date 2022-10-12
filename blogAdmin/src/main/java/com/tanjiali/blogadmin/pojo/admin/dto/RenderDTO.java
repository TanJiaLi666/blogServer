package com.tanjiali.blogadmin.pojo.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 18:41
 * @Version 1.0
 **/
@Data
public class RenderDTO {
    private List<String> legend;
    private List<DashBoardDTO> series;
}
