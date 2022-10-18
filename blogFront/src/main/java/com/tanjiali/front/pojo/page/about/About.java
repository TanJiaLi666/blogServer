package com.tanjiali.front.pojo.page.about;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *@ClassName Tanjiali
 *@Description TODO
 *@Author eflyings
 *@Date 2022/9/29 15:57
 *@Version 1.0
 **/
@Data
@TableName("about")
public class About {
    private Long id;
    @TableField("name_en")
    private String nameEn;
    @TableField("name_zh")
    private String nameZh;
    private String value;
}
