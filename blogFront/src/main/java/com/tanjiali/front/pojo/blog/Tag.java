package com.tanjiali.front.pojo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tag")
@Data
public class Tag {
    @TableId
    private Integer id;
    @TableField(value = "tag_name")
    private String name;
    private String color;
}
