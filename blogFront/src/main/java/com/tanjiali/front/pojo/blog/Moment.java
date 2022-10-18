package com.tanjiali.front.pojo.blog;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    private Integer likes;
    @TableField("is_published")
    private Boolean published;
}
