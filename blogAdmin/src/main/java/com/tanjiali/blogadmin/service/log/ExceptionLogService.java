package com.tanjiali.blogadmin.service.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.log.ExceptionLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:39
 * @Version 1.0
 **/
public interface ExceptionLogService extends IService<ExceptionLog> {
    Page<ExceptionLog> getExceptionLogList(LogVO vo);

    Boolean deleteExceptionLogById(Long id);
}
