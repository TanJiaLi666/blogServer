package com.tanjiali.front.pojo.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 访问日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:47
 * @Version 1.0
 **/
@TableName("visit_log")
@Data
public class VisitLog {
    private Long id;
    private String uuid;//访客标识码
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String behavior;//访问行为
    private String content;//访问内容
    private String remark;//备注
    private String ip;//ip
    @TableField("ip_source")
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;//访问时间
    @TableField("user_agent")
    private String userAgent;


}
