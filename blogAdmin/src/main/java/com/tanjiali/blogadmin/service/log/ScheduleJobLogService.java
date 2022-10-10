package com.tanjiali.blogadmin.service.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.log.ScheduleJobLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;

/**
 * @ClassName 定时任务日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 18:37
 * @Version 1.0
 **/
public interface ScheduleJobLogService extends IService<ScheduleJobLog> {
    Page<ScheduleJobLog> getJobLogList(LogVO vo);

    Boolean deleteJobLogByLogId(Long logId);
}
