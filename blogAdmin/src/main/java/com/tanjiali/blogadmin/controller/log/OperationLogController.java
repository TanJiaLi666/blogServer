package com.tanjiali.blogadmin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.LoginLog;
import com.tanjiali.blogadmin.pojo.log.OperationLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.LoginLogService;
import com.tanjiali.blogadmin.service.log.OperationLogService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 操作日志控制
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:12
 * @Version 1.0
 **/
@RestController
@Api(value = "登录日志管理")
@RequestMapping("back/log")
@CrossOrigin
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation("加载操作日志信息")
    @GetMapping("/operationLogs")
    public PublicResult<PublicPage<OperationLog>> getOperationLogList(LogVO vo){
        Page<OperationLog> logList = operationLogService.getOperationLogList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无日志信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除操作日志信息")
    @DeleteMapping("/operationLog")
    public PublicResult<Boolean> deleteOperationLogById(@RequestParam("id") Long id){
        Boolean delete = operationLogService.deleteOperationLogById(id);
        if (!delete) {
            return PublicResult.failed("暂无日志信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
