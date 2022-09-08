package com.tanjiali.blogadmin.pojo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("moment")
@Data
public class Moment {
    @TableId
    private Integer id;
    private String Content;
    @TableField("create_time")
    private Date createTime;
    private Integer likes;
    @TableField("is_published")
    private Boolean published;
}
