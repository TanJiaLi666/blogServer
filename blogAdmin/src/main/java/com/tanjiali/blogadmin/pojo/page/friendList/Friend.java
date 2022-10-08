package com.tanjiali.blogadmin.pojo.page.friendList;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Friend
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/9/29 18:20
 * @Version 1.0
 **/
@Data
@TableName("friend")
public class Friend {
    private Long id;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
    @TableField("is_published")
    private Boolean published;//公开或隐藏
    private Integer views;//浏览次数
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;//创建时间
}
