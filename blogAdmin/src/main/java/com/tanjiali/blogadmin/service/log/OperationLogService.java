package com.tanjiali.blogadmin.service.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.log.OperationLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;

/**
 * @ClassName 操作日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:11
 * @Version 1.0
 **/
public interface OperationLogService extends IService<OperationLog> {
    Boolean saveOperationLog(OperationLog log);
    Page<OperationLog> getOperationLogList(LogVO vo);

    Boolean deleteOperationLogById(Long id);
}
