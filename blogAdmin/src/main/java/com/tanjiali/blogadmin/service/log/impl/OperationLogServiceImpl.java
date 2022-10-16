package com.tanjiali.blogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.log.OperationLogMapper;
import com.tanjiali.blogadmin.pojo.log.LoginLog;
import com.tanjiali.blogadmin.pojo.log.OperationLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.OperationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:11
 * @Version 1.0
 **/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    @Override
    public Boolean saveOperationLog(OperationLog log) {
        return save(log);
    }

    @Override
    public Page<OperationLog> getOperationLogList(LogVO vo) {
        Page<OperationLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(OperationLog::getCreateTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    public Boolean deleteOperationLogById(Long id) {
        return removeById(id);
    }
}
