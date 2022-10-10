package com.tanjiali.blogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.log.ExceptionLogMapper;
import com.tanjiali.blogadmin.pojo.log.ExceptionLog;
import com.tanjiali.blogadmin.pojo.log.vo.LogVO;
import com.tanjiali.blogadmin.service.log.ExceptionLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/10 21:39
 * @Version 1.0
 **/
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {
    @Override
    public Page<ExceptionLog> getExceptionLogList(LogVO vo) {
        Page<ExceptionLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<ExceptionLog> wrapper = new QueryWrapper<>();
        List<String> date = vo.getDate();
        if (date != null) {
            wrapper.lambda()
                    .between(ExceptionLog::getCreateTime, date.get(0), date.get(1));
        }
        return page(page, wrapper);
    }

    @Override
    public Boolean deleteExceptionLogById(Long id) {
        return removeById(id);
    }
}
