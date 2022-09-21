package com.tanjiali.blogadmin.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Tag;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> blogs(IPage<Blog> page, String title, Integer categoryId);

    void saveTag(int blogId, List<Integer> tagList);

    List<Integer> getTagById(Integer id);
}
