package com.tanjiali.blogadmin.mapper.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanjiali.blogadmin.pojo.admin.dto.DashBoardDTO;
import com.tanjiali.blogadmin.pojo.blog.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    List<DashBoardDTO> getTagBlogCountList();
}
