package com.tanjiali.blogadmin.pojo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("category")
@Data
public class Category {
    @TableId
    private Integer id;
    @TableField(value = "category_name")
    private String name;
}
