package com.tanjiali.blogadmin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.VisitLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 访问日志控制
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:52
 * @Version 1.0
 **/

@RestController
@Api(value = "访问日志管理")
@RequestMapping("back/log")
@CrossOrigin
public class VisitLogController {
    @Autowired
    private VisitLogService visitLogService;

    @ApiOperation("加载访问日志信息")
    @GetMapping("/visitLogs")
    public PublicResult<PublicPage<VisitLog>> getVisitLogList(LogVO vo){
        Page<VisitLog> logList = visitLogService.getVisitLogList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无日志信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除访问日志信息")
    @OperaLog("删除访问日志信息")
    @DeleteMapping("/visitLog")
    public PublicResult<Boolean> deleteVisitLogById(@RequestParam("id") Long id){
        Boolean delete = visitLogService.deleteVisitLogById(id);
        if (!delete) {
            return PublicResult.failed("暂无日志信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
