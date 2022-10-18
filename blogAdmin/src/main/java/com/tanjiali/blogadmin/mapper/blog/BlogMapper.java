package com.tanjiali.blogadmin.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Tag;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> blogs(IPage<Blog> page, String title, Integer categoryId);

    void saveTag(Long blogId, List<Integer> tagList);

    List<Integer> getTagById(Long id);

    List<DashBoardDTO> getCategoryBlogCountList();

    List<Integer> getBlogTagByid(Integer id);
}
