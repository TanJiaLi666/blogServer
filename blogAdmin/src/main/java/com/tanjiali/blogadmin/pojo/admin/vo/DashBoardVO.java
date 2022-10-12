package com.tanjiali.blogadmin.pojo.admin.vo;

import com.tanjiali.blogadmin.pojo.admin.dto.RenderDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.ViDTO;
import com.tanjiali.blogadmin.pojo.admin.dto.VisitDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;
/**
 * @author hasee
 */
@Data
public class DashBoardVO {
    private Integer pv;
    private Integer uv;
    private Integer blogCount;
    private Integer commentCount;
    private RenderDTO category;
    private RenderDTO tag;
    private ViDTO visitRecord;
    private List cityVisitor;

}
