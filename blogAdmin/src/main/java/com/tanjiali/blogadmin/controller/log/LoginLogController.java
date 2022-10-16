package com.tanjiali.blogadmin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.LoginLog;
import com.tanjiali.blogadmin.pojo.log.ScheduleJobLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.LoginLogService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 登录日志管理
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 19:21
 * @Version 1.0
 **/
@RestController
@Api(value = "登录日志管理")
@RequestMapping("back/log")
@CrossOrigin
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation("加载登录日志信息")
    @GetMapping("/loginLogs")
    public PublicResult<PublicPage<LoginLog>> getLoginLogList(LogVO vo){
        Page<LoginLog> logList = loginLogService.getLoginLogList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无日志信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除登录日志信息")
    @OperaLog("删除登录日志信息")
    @DeleteMapping("/loginLog")
    public PublicResult<Boolean> deleteLoginLogById(@RequestParam("id") Long id){
        Boolean delete = loginLogService.deleteLoginLogById(id);
        if (!delete) {
            return PublicResult.failed("暂无日志信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
