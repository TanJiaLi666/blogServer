package com.tanjiali.blogadmin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.ExceptionLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.ExceptionLogService;
import com.tanjiali.blogpublicapi.annotation.LoginCheck;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 异常日志控制
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:40
 * @Version 1.0
 **/
@RestController
@Api(value = "异常日志管理")
@RequestMapping("back/log")
@CrossOrigin
@LoginCheck("用户需要登录验证")
public class ExceptionLogController {
    @Autowired
    private ExceptionLogService exceptionLogService;

    @ApiOperation("加载异常日志信息")
    @GetMapping("/exceptionLogs")
    public PublicResult<PublicPage<ExceptionLog>> getExceptionLogList(LogVO vo){
        Page<ExceptionLog> logList = exceptionLogService.getExceptionLogList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无日志信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除异常日志信息")
    @OperaLog("删除异常日志信息")
    @DeleteMapping("/exceptionLog")
    public PublicResult<Boolean> deleteExceptionLogById(@RequestParam("id") Long id){
        Boolean delete = exceptionLogService.deleteExceptionLogById(id);
        if (!delete) {
            return PublicResult.failed("暂无日志信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
