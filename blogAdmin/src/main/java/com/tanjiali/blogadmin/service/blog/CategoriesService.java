package com.tanjiali.blogadmin.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.blogadmin.pojo.blog.Category;

public interface CategoriesService extends IService<Category> {
    /**
     * 分类查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Category> categories(Integer pageNum, Integer pageSize);

    /**
     * 分类插入
     * @param category
     * @return
     */
    Boolean addCategory(Category category);
    /**
     * 分类编辑
     * @param category
     * @return
     */
    Boolean editCategory(Category category);

    Boolean deleteCategory(Integer id);
}
