package com.tanjiali.blogadmin.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.system.ScheduleJob;

import java.util.List;

/**
 * @ClassName 定时任务配置service
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/9 18:32
 * @Version 1.0
 **/

public interface ScheduleJobService extends IService<ScheduleJob> {
    /**
     * 加载定时任务列表
     * @param job
     * @return
     */
    Page<ScheduleJob> getJobList(ScheduleJob job);

    Boolean addJob(ScheduleJob job);

    Boolean editJob(ScheduleJob job);

    Boolean updateJobStatus(Long jobId, Boolean status);

    Boolean deleteJobById(Long jobId);

    Boolean runJobOnce(Long jobId);
}
