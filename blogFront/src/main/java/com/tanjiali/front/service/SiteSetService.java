package com.tanjiali.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.front.pojo.page.site.SiteSetting;
import com.tanjiali.front.pojo.page.site.VO.IndexSiteVO;

/**
 * @ClassName 站点个人配置信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 13:07
 * @Version 1.0
 **/
public interface SiteSetService extends IService<SiteSetting> {
    IndexSiteVO getSite();
}
