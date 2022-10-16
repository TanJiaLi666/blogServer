package com.tanjiali.blogadmin.controller.page.site;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanjiali.blogadmin.pojo.page.friendList.Friend;
import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import com.tanjiali.blogadmin.pojo.page.site.VO.SiteSettingInVO;
import com.tanjiali.blogadmin.pojo.page.site.VO.SiteSettingVO;
import com.tanjiali.blogadmin.service.page.site.SiteSettingService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
import com.tanjiali.blogpublicapi.api.PublicPage;
import com.tanjiali.blogpublicapi.api.PublicResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SiteSettingController
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/30 15:50
 * @Version 1.0
 **/
@RestController
@Api(value = "站点页面管理")
@RequestMapping("back/page")
@CrossOrigin
public class SiteSettingController {
    @Autowired
    private SiteSettingService siteSettingService;

    @ApiOperation("加载列表")
    @GetMapping("/siteSettings")
    public PublicResult<SiteSettingVO> getSiteSettingData() {
        SiteSettingVO vo = siteSettingService.getSiteSettingData();
        if (vo != null) {
            return PublicResult.success(vo,"成功");
        }
        return PublicResult.failed("失败");
    }

    @ApiOperation("更新配置列表")
    @OperaLog("更新配置列表")
    @PostMapping("/siteSettings")
    public PublicResult<Boolean> updateSiteSettings(@RequestBody SiteSettingInVO inVO) {
        Boolean update = siteSettingService.updateSiteSettings(inVO);
        if (update) {
            return PublicResult.success("成功");
        }
        return PublicResult.failed("失败");
    }
    /**
     * export function getWebTitleSuffix() {
     * 	return axios({
     * 		url: 'webTitleSuffix',
     * 		method: 'GET'
     *    })
     * }
     */
}
