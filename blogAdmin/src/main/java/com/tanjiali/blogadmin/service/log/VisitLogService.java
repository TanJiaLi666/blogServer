package com.tanjiali.blogadmin.service.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.log.VisitLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;

/**
 * @ClassName 访问日志信息
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:51
 * @Version 1.0
 **/
public interface VisitLogService extends IService<VisitLog> {
    Page<VisitLog> getVisitLogList(LogVO vo);

    Boolean deleteVisitLogById(Long id);

    Boolean deleteVisitLogByUuid(String uuid);

    void saveVisitLog(VisitLog visitLogObject);
}
