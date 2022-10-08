package com.tanjiali.blogadmin.pojo.page.site.VO;

import com.tanjiali.blogadmin.pojo.page.site.SiteSetting;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SiteSettingVO
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/30 17:27
 * @Version 1.0
 **/
@Data
public class SiteSettingVO<T> {
    private List<T> type1;
    private List<T> type2;
    private List<T> type3;
}
