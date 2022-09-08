package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;

public interface BlogService extends IService<Blog> {
    BlogVO blogs(Integer pageNum, Integer pageSize, String title, Integer categoryId);
}
