package com.tanjiali.front.pojo.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 登录日志
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 19:15
 * @Version 1.0
 **/
@Data
@TableName("login_log")
public class LoginLog {

    private Long id;
    private String username;//用户名称
    private String ip;//ip
    @TableField("ip_source")
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Boolean status;//登录状态
    private String description;//操作信息
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;//操作时间
    @TableField("user_agent")
    private String userAgent;
}
