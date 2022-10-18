package com.tanjiali.front.service.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanjiali.front.pojo.blog.Blog;

import java.util.List;

/**
 * @ClassName 博客查询
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/18 16:03
 * @Version 1.0
 **/
public interface BlogService extends IService<Blog> {
    /**
     * 按置顶、创建时间排序 分页查询博客简要信息列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Blog> getBlogList(Integer pageNum, Integer pageSize);
}
