package com.tanjiali.blogadmin.service.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.log.LoginLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;

/**
 * @ClassName 登录日志
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 19:19
 * @Version 1.0
 **/
public interface LoginLogService extends IService<LoginLog> {
    Page<LoginLog> getLoginLogList(LogVO vo);

    Boolean deleteLoginLogById(Long id);
}
