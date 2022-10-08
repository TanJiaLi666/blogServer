package com.tanjiali.blogadmin.pojo.page.site.VO;

import lombok.Data;

import java.util.List;

/**
 * @ClassName 数据输出到页面
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
