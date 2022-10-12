package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {

    Page<Tag> tags(Integer pageNum, Integer pageSize);

    Boolean addTag(Tag tag);

    Boolean editTag(Tag tag);

    Boolean deleteTag(Integer id);

    List<DashBoardDTO> getTagBlogCountList();
}
