package com.tanjiali.blogadmin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.ScheduleJobLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.ScheduleJobLogService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 定时任务日志管理
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 18:39
 * @Version 1.0
 **/
@RestController
@Api(value = "定时任务日志管理")
@RequestMapping("back/log")
@CrossOrigin
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService jobLogService;

    @ApiOperation("加载任务日志信息")
    @GetMapping("/job/logs")
    public PublicResult<PublicPage<ScheduleJobLog>> getJobLogList(LogVO vo){
        Page<ScheduleJobLog> logList = jobLogService.getJobLogList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无日志信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除任务日志信息")
    @OperaLog("删除任务日志信息")
    @DeleteMapping("/job/log")
    public PublicResult<Boolean> deleteJobLogByLogId(@RequestParam("logId") Long logId){
        Boolean delete = jobLogService.deleteJobLogByLogId(logId);
        if (!delete) {
            return PublicResult.failed("暂无日志信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
