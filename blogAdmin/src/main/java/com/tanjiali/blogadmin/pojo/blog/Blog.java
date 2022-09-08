package com.tanjiali.blogadmin.pojo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("blog")
public class Blog {
    @TableId
    private Integer id;
    private String title;
    @TableField("first_picture")
    private String firstPicture;
    private String content;
    private String description;
    @TableField("is_published")
    private Boolean published;
    @TableField("is_recommend")
    private Boolean recommend;
    @TableField("is_appreciation")
    private Boolean appreciation;
    @TableField("is_comment_enabled")
    private Boolean commentEnabled;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    private Integer views;
    private Integer words;
    @TableField("read_time")
    private Integer readTime;
    @TableField("category_id")
    private Integer categoryId;
    @TableField(exist = false)
    private String categoryName;
    @TableField("is_top")
    private Boolean top;
    private String password;
    @TableField("user_id")
    private Integer userId;
}
