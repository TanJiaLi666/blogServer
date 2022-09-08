package com.tanjiali.blogadmin.pojo.admin;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class Admin {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String email;
    private Date createTime;
    private Date updateTime;
    private String role;
}