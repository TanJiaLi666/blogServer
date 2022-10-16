package com.tanjiali.blogadmin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.system.ScheduleJob;
import com.tanjiali.blogadmin.service.system.ScheduleJobService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
  *@ClassName 定时任务管理
  *@Description TODO
  *@Author Tanjiali
  *@Date 2022/10/9 18:35
  *@Version 1.0
  **/
@RestController
@Api(value = "定时任务管理")
@RequestMapping("back/system")
@CrossOrigin
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService jobService;

    @ApiOperation("加载定时任务列表")
    @GetMapping("/jobs")
    public PublicResult<PublicPage<ScheduleJob>> getJobList(ScheduleJob job){
        Page<ScheduleJob> list = jobService.getJobList(job);
        if (list != null) {
            return PublicResult.success(PublicPage.restPage(list),"定时任务加载完成");
        }
        return PublicResult.failed("加载失败");
    }
    @ApiOperation("添加定时任务")
    @OperaLog("添加定时任务")
    @PostMapping("/job")
    public PublicResult<Boolean> addJob(@RequestBody ScheduleJob job){
        Boolean save = jobService.addJob(job);
        if (save) {
            return PublicResult.success("定时任务添加完成");
        }
        return PublicResult.failed("定时任务添加失败");
    }
    @ApiOperation("修改定时任务")
    @OperaLog("修改定时任务")
    @PutMapping("/job")
    public PublicResult<Boolean> editJob(@RequestBody ScheduleJob job){
        Boolean update = jobService.editJob(job);
        if (update) {
            return PublicResult.success("定时任务修改完成");
        }
        return PublicResult.failed("定时任务修改失败");
    }
    @ApiOperation("修改定时任务状态")
    @OperaLog("修改定时任务状态")
    @PutMapping("/job/status")
    public PublicResult<Boolean> updateJobStatus(@RequestParam Long jobId,
                                                 @RequestParam Boolean status){
        Boolean update = jobService.updateJobStatus(jobId, status);
        if (update) {
            return PublicResult.success("修改完成");
        }
        return PublicResult.failed("失败");
    }

    @ApiOperation("删除定时任务")
    @OperaLog("删除定时任务")
    @DeleteMapping("/job")
    public PublicResult<Boolean> deleteJobById(@RequestParam Long jobId){
        Boolean update = jobService.deleteJobById(jobId);
        if (update) {
            return PublicResult.success("删除完成");
        }
        return PublicResult.failed("失败");
    }
    @ApiOperation("运行定时任务")
    @OperaLog("定时任务立即执行一次")
    @PostMapping("/job/run")
    public PublicResult<Boolean> runJobOnce(@RequestParam Long jobId){
        Boolean update = jobService.runJobOnce(jobId);
        if (update) {
            return PublicResult.success("执行成功---功能待开发");
        }
        return PublicResult.failed("失败");
    }
}
