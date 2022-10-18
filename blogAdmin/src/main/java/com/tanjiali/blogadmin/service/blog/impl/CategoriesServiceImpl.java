package com.tanjiali.blogadmin.service.blog.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.CategoryMapper;
import com.tanjiali.blogadmin.pojo.blog.Blog;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.service.blog.BlogService;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoriesService {
    @Autowired
    private BlogService blogService;
    @Override
    public Page<Category> categories(Integer pageNum, Integer pageSize) {
        Page<Category> page = new Page<>(pageNum, pageSize);
        Page<Category> categoryPage = this.page(page);
        return categoryPage;
    }

    @Override
    public Boolean addCategory(Category category) {
        boolean save = this.save(category);
        return save;
    }

    @Override
    public Boolean editCategory(Category category) {
        boolean update = this.updateById(category);
        return update;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        //判断是否有文章正在使用
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getCategoryId, id);
        List<Blog> list = blogService.list(wrapper);
        if (!CollUtil.isEmpty(list)) {
            return false;
        }
        return this.removeById(id);
    }
}
