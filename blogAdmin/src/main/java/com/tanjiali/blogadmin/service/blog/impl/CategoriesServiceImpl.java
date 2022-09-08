package com.tanjiali.blogadmin.service.blog.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanjiali.blogadmin.mapper.blog.CategoryMapper;
import com.tanjiali.blogadmin.pojo.blog.Category;
import com.tanjiali.blogadmin.service.blog.CategoriesService;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoriesService {
    @Override
    public Page<Category> categories(Integer pageNum, Integer pageSize) {
        Page<Category> page = new Page<>(pageNum, pageSize);
        Page<Category> categoryPage = this.page(page);
        long total = categoryPage.getTotal();
        long current = categoryPage.getCurrent();
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
        return this.removeById(id);
    }
}
