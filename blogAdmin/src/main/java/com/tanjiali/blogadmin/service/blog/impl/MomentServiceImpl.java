package com.tanjiali.blogadmin.service.blog.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.MomentMapper;
import com.tanjiali.blogadmin.pojo.blog.Moment;
import com.tanjiali.blogadmin.service.blog.MomentService;
import org.springframework.stereotype.Service;


@Service
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment> implements MomentService {
    @Override
    public Page<Moment> moments(Integer pageNum, Integer pageSize) {
        Page<Moment> page = new Page<>(pageNum, pageSize);
        Page<Moment> momentPage = this.page(page);
        return momentPage;
    }

    @Override
    public Boolean addMoment(Moment moment) {
        boolean save = this.save(moment);
        return save;
    }

    @Override
    public Boolean editMoment(Moment moment) {
        boolean update = this.updateById(moment);
        return update;
    }

    @Override
    public Boolean deleteMoment(Integer id) {
        return this.removeById(id);
    }

    @Override
    public Boolean publishedMoment(Integer id, Boolean published) {
        Moment moment = new Moment();
        moment.setId(id);
        //1为不公开，0为公开
        if (published) {
            moment.setPublished(true);
        }else {
            moment.setPublished(false);
        }
        boolean update = this.updateById(moment);
        return update;
    }

    @Override
    public Moment getByIdMoment(Integer id) {
        return this.getById(id);
    }
}
