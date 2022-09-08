package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.blog.Moment;

public interface MomentService extends IService<Moment> {
    Page<Moment> moments(Integer pageNum, Integer pageSize);

    Boolean addMoment(Moment moment);

    Boolean editMoment(Moment moment);

    Boolean deleteMoment(Integer id);

    Boolean publishedMoment(Integer id, Boolean published);

    Moment getByIdMoment(Integer id);
}
