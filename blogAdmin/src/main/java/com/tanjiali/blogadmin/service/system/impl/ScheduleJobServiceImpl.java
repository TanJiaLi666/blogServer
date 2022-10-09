package com.tanjiali.blogadmin.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.system.ScheduleJobMapper;
import com.tanjiali.blogadmin.pojo.system.ScheduleJob;
import com.tanjiali.blogadmin.service.system.ScheduleJobService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/9 18:34
 * @Version 1.0
 **/
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
    @Override
    public Page<ScheduleJob> getJobList(ScheduleJob job) {
        Page<ScheduleJob> page = new Page<>(job.getPageNum(), job.getPageSize());
        return page(page);
    }

    @Override
    public Boolean addJob(ScheduleJob job) {
        return save(job);
    }

    @Override
    public Boolean editJob(ScheduleJob job) {
        return updateById(job);
    }

    @Override
    public Boolean updateJobStatus(Long jobId, Boolean status) {
        UpdateWrapper<ScheduleJob> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(ScheduleJob::getStatus, status)
                .eq(ScheduleJob::getJobId, jobId);
        return update(wrapper);
    }

    @Override
    public Boolean deleteJobById(Long jobId) {
        return removeById(jobId);
    }

    @Override
    public Boolean runJobOnce(Long jobId) {
        return true;
    }
}
