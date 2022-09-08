package com.tanjiali.blogadmin.service.blog.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.BlogMapper;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.pojo.blog.VO.BlogVO;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {
    @Autowired
    private CategoriesService categoriesService;
    @Override
    public BlogVO blogs(Integer pageNum, Integer pageSize, String title, Integer categoryId) {
        Page<Blog> page = new Page<>(pageNum,pageSize);
        List<Category> categories = categoriesService.list();
        IPage<Blog> blogIPage = this.getBaseMapper().blogs(page, title, categoryId);
        BlogVO blogVO = BlogVO.restPage((Page) blogIPage, categories);
        return blogVO;
    }
}
