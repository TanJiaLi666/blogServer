package com.tanjiali.front.pojo.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 定时任务信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/9 18:22
 * @Version 1.0
 **/
@Data
@TableName("schedule_job")
public class ScheduleJob {
    @ApiModelProperty("任务id")
    @TableField("job_id")
    @TableId
    private Long jobId;

    @ApiModelProperty("spring bean名称")
    @TableField("bean_name")
    private String beanName;

    @ApiModelProperty("方法名")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("cron表达式")
    private String cron;

    @ApiModelProperty("任务状态")
    private Boolean status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time" , fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("任务调度参数key")
    @TableField(exist = false)
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @TableField(exist = false)
    private Integer pageSize;
    @TableField(exist = false)
    private Integer pageNum;

}
