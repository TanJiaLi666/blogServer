package com.tanjiali.blogadmin.controller.statistics;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.log.ExceptionLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.pojo.statistics.Visitor;
import com.tanjiali.blogadmin.service.log.ExceptionLogService;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import com.tanjiali.blogadmin.service.statistics.VisitorService;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName 访客信息管理
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 17:08
 * @Version 1.0
 **/
@RestController
@Api(value = "访客信息管理")
@RequestMapping("back/statistics")
@CrossOrigin
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @ApiOperation("加载访客信息")
    @GetMapping("/visitors")
    public PublicResult<PublicPage<Visitor>> getVisitorList(LogVO vo){
        Page<Visitor> logList = visitorService.getVisitorList(vo);
        if (logList == null) {
            return PublicResult.failed("暂无访客信息");
        }
        return PublicResult.success(PublicPage.restPage(logList),"查询成功!!");
    }

    @ApiOperation("删除访客信息")
    @DeleteMapping("/visitor")
    public PublicResult<Boolean> deleteVisitor(@RequestParam("id") Long id,
                                               @RequestParam("uuid") String uuid){
        Boolean delete = visitorService.deleteVisitor(id,uuid);
        if (!delete) {
            return PublicResult.failed("暂无访客信息删除");
        }
        return PublicResult.success("清除成功!!");
    }
}
