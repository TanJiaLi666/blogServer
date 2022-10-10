package com.tanjiali.blogadmin.pojo.log;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 定时任务日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 12:15
 * @Version 1.0
 **/
@Data
@TableName("schedule_job_log")
public class ScheduleJobLog {
    @TableId
    @TableField("log_id")
    @ApiModelProperty("日志id")
    private Long logId;

    @TableField("job_id")
    @ApiModelProperty("任务id")
    private Long jobId;

    @TableField("bean_name")
    @ApiModelProperty("spring bean名称")
    private String beanName;

    @TableField("method_name")
    @ApiModelProperty("方法名")
    private String methodName;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("任务执行结果")
    private Boolean status;

    @ApiModelProperty("异常信息")
    private String error;

    @ApiModelProperty("耗时(单位：毫秒)")
    private Integer times;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;
}
