package com.tanjiali.blogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.log.VisitLogMapper;
import com.tanjiali.blogadmin.pojo.log.ScheduleJobLog;
import com.tanjiali.blogadmin.pojo.log.VisitLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:51
 * @Version 1.0
 **/
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {
    @Override
    public Page<VisitLog> getVisitLogList(LogVO vo) {
        Page<VisitLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<VisitLog> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(VisitLog::getCreateTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    public Boolean deleteVisitLogById(Long id) {
        return removeById(id);
    }
}
