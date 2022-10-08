package com.tanjiali.blogadmin.service.page.site;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import com.tanjiali.blogadmin.pojo.page.site.VO.SiteSettingVO;

/**
 * @ClassName SiteSettingSeervice
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/30 15:57
 * @Version 1.0
 **/
public interface SiteSettingService extends IService<SiteSetting> {
    SiteSettingVO getSiteSettingData();

    Boolean updateSiteSettings();

}
