package com.tanjiali.blogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.log.LoginLogMapper;
import com.tanjiali.blogadmin.pojo.log.LoginLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.LoginLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 19:20
 * @Version 1.0
 **/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Override
    public Page<LoginLog> getLoginLogList(LogVO vo) {
        Page<LoginLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(LoginLog::getCreateTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    public Boolean deleteLoginLogById(Long id) {
        return removeById(id);
    }
}
