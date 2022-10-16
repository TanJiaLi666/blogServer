package com.tanjiali.blogadmin.pojo.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName 操作日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 19:34
 * @Version 1.0
 **/
@Data
@TableName("operation_log")
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {
    private Long id;
    private String username;//操作者用户名
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String description;//操作描述
    private String ip;//ip
    @TableField("ip_source")
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;//操作时间
    @TableField("user_agent")
    private String userAgent;

    public OperationLog(String username, String uri, String method, String param, String description, String ip, String ipSource, String os, String browser, Integer times, String userAgent) {
        this.username = username;
        this.uri = uri;
        this.method = method;
        this.param = param;
        this.description = description;
        this.ip = ip;
        this.ipSource = ipSource;
        this.os = os;
        this.browser = browser;
        this.times = times;
        this.userAgent = userAgent;
    }
}
