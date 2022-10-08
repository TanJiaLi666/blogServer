package com.tanjiali.blogadmin.pojo.page.site;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName SiteSetting
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/30 15:53
 * @Version 1.0
 **/
@Data
@TableName("site_setting")
public class SiteSetting {
    private Long id;
    @TableField("name_en")
    private String nameEn;
    @TableField("name_zh")
    private String nameZh;
    private String value;
    private Integer type;
}
