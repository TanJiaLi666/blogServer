package com.tanjiali.blogadmin.service.blog.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.TagMapper;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import com.tanjiali.blogadmin.service.blog.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper,Tag> implements TagService {
    @Override
    public Page<Tag> tags(Integer pageNum, Integer pageSize) {
        Page<Tag> page = new Page<>(pageNum, pageSize);
        Page<Tag> tagPage = this.page(page);
        return tagPage;
    }

    @Override
    public Boolean addTag(Tag tag) {
        boolean save = this.save(tag);
        return save;
    }

    @Override
    public Boolean editTag(Tag tag) {
        boolean update = this.updateById(tag);
        return update;
    }

    @Override
    public Boolean deleteTag(Integer id) {
        return this.removeById(id);
    }
}
