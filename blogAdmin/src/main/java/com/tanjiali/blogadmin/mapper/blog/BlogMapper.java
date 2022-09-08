package com.tanjiali.blogadmin.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanjiali.blogadmin.pojo.blog.Blog;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> blogs(IPage<Blog> page, String title, Integer categoryId);
}
