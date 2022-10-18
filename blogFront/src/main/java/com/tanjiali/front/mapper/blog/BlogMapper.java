package com.tanjiali.front.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanjiali.front.pojo.blog.Blog;


import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> blogs(IPage<Blog> page, String title, Integer categoryId);

    void saveTag(Long blogId, List<Integer> tagList);

    List<Integer> getTagById(Long id);

    List<Integer> getBlogTagByid(Integer id);
}
