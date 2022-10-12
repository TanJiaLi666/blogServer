package com.tanjiali.blogadmin.service.statistics.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.statistics.VisitorMapper;
import com.tanjiali.blogadmin.pojo.admin.CityVisitor;
import com.tanjiali.blogadmin.pojo.admin.dto.VisitDTO;
import com.tanjiali.blogadmin.pojo.log.ExceptionLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.pojo.statistics.Visitor;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import com.tanjiali.blogadmin.service.statistics.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 17:07
 * @Version 1.0
 **/
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {
    @Autowired
    private VisitLogService logService;
    @Override
    public Page<Visitor> getVisitorList(LogVO vo) {
        Page<Visitor> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<Visitor> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(Visitor::getLastTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    @Transactional
    public Boolean deleteVisitor(Long id, String uuid) {
        removeById(id);
        logService.deleteVisitLogByUuid(uuid);
        return true;
    }

    @Override
    public List<VisitDTO> getVisitRecordList(int num) {
        return baseMapper.getVisitRecordList(num);
    }

    @Override
    public List<CityVisitor> getCityVisitorList() {
        return baseMapper.getCityVisitorList();
    }
}
