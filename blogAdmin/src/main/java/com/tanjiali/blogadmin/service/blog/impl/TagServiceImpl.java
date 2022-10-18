package com.tanjiali.blogadmin.service.blog.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.TagMapper;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Tag;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper,Tag> implements TagService {
    @Autowired
    private BlogService blogService;
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
        //判断是否有文章正在使用
        List<Integer> list = blogService.getBlogTagByid(id);
        if (!CollUtil.isEmpty(list)) {
            return false;
        }
        return this.removeById(id);
    }

    @Override
    public List<DashBoardDTO> getTagBlogCountList() {
        return baseMapper.getTagBlogCountList();
    }
}
