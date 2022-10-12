package com.tanjiali.blogadmin.mapper.statistics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanjiali.blogadmin.pojo.admin.CityVisitor;
import com.tanjiali.blogadmin.pojo.admin.dto.VisitDTO;
import com.tanjiali.blogadmin.pojo.statistics.Visitor;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 17:06
 * @Version 1.0
 **/
public interface VisitorMapper extends BaseMapper<Visitor> {
    List<VisitDTO> getVisitRecordList(int num);

    List<CityVisitor> getCityVisitorList();
}
