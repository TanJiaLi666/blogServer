package com.tanjiali.blogadmin.pojo.page.site.VO;

import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import lombok.Data;

import java.util.List;

/**
 * @ClassName 页面输入数据
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/8 17:11
 * @Version 1.0
 **/
@Data
public class SiteSettingInVO {
    private List<Long> deleteIds;
    private List<SiteSetting> settings;
}
