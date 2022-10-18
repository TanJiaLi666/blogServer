package com.tanjiali.front.pojo.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 异常日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:17
 * @Version 1.0
 **/
@Data
@TableName("exception_log")
public class ExceptionLog {
    private Long id;
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String description;//操作描述
    private String error;//异常信息
    private String ip;//ip
    @TableField("ip_source")
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;//操作时间
    @TableField("user_agent")
    private String userAgent;

}
