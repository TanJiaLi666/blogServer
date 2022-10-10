package com.tanjiali.blogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.log.ScheduleJobLogMapper;
import com.tanjiali.blogadmin.pojo.log.ScheduleJobLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.ScheduleJobLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 18:38
 * @Version 1.0
 **/
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

    @Override
    public Page<ScheduleJobLog> getJobLogList(LogVO vo) {
        Page<ScheduleJobLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<ScheduleJobLog> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(ScheduleJobLog::getCreateTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    public Boolean deleteJobLogByLogId(Long logId) {
        return removeById(logId);
    }
}
