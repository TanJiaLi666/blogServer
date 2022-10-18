package com.tanjiali.front.pojo.statistics;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 访客统计信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/11 17:00
 * @Version 1.0
 **/
@Data
@TableName("visitor")
public class Visitor {
    private Long id;
    private String uuid;//访客标识码
    private String ip;//ip
    @TableField("ip_source")
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;//首次访问时间
    @TableField(value = "last_time", fill = FieldFill.INSERT)
    private Date lastTime;//最后访问时间
    private Integer pv;//访问页数统计
    @TableField("user_agent")
    private String userAgent;
}
