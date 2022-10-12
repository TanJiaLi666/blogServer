package com.tanjiali.blogadmin.service.statistics;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.admin.CityVisitor;
import com.tanjiali.blogadmin.pojo.admin.dto.VisitDTO;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.pojo.statistics.Visitor;

import java.util.List;

/**
 * @ClassName 访客信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 17:07
 * @Version 1.0
 **/
public interface VisitorService extends IService<Visitor> {
    Page<Visitor> getVisitorList(LogVO vo);

    Boolean deleteVisitor(Long id, String uuid);

    List<VisitDTO> getVisitRecordList(int num);

    List<CityVisitor> getCityVisitorList();
}
